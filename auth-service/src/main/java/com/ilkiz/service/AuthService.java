package com.ilkiz.service;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.LoginRequestDto;
import com.ilkiz.dto.request.RegisterRequestDto;
import com.ilkiz.dto.response.*;
import com.ilkiz.exception.AuthServiceException;
import com.ilkiz.exception.ErrorType;
import com.ilkiz.manager.IUserManager;
import com.ilkiz.mapper.IAuthMapper;
import com.ilkiz.rabbitmq.model.UpdateUsernameEmailModel;
import com.ilkiz.rabbitmq.producer.ActivatedCodeProducer;
import com.ilkiz.repository.IAuthRepository;
import com.ilkiz.repository.entity.Auth;
import com.ilkiz.repository.enums.Role;
import com.ilkiz.repository.enums.Status;
import com.ilkiz.utility.CodeGenerator;
import com.ilkiz.utility.JwtTokenManager;
import com.ilkiz.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService extends ServiceManager<Auth,Long> {

    private final IAuthRepository authRepository;
    private final JwtTokenManager tokenManager;
    private final IUserManager userManager;
    private final CacheManager cacheManager;

    private final ActivatedCodeProducer activatedCodeProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager tokenManager, IUserManager userManager, CacheManager cacheManager, ActivatedCodeProducer activatedCodeProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.tokenManager = tokenManager;

        this.userManager = userManager;
        this.cacheManager = cacheManager;
        this.activatedCodeProducer = activatedCodeProducer;
    }

    @Transactional
    public RegisterResponseDto register(RegisterRequestDto dto){

    Auth auth = IAuthMapper.INSTANCE.toAuth(dto);

   //     if (existUser(dto.getUsername())) {
   //         throw new AuthServiceException(ErrorType.KULLANICI_ZATEN_KAYITLI);
   //   }else {
   //         if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("admin")){
   //             auth.setRole(Role.ADMIN);
   //         }
        if (dto.getAdmincode()!=null&&dto.getAdmincode().equals("admin")) {
            auth.setRole(Role.ADMIN);
        }try{
            String activatedCode = CodeGenerator.generateCode(UUID.randomUUID().toString());
            auth.setActivatedCode(activatedCode);
            save(auth);
            cacheManager.getCache("findbyrole").evict(auth.getRole());
            userManager.create(NewUserCreateRequestDto.builder()
                    .authid(auth.getId())
                    .email(auth.getEmail())
                    .username(auth.getUsername())
                    .build());

           /* activatedCodeProducer.activatedCode(com.ilkiz.rabbitmq.model.ActivatedRequestDto.builder()
                    .email(auth.getEmail())
                    .activatedCode(auth.getActivatedCode()).build());*/

            return IAuthMapper.INSTANCE.toRegisterResponseDto(auth);
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthServiceException(ErrorType.KULLANICI_KAYDEDILEMEDI);
        }
    }

    public Boolean existUser(String username){
        return authRepository.existUsername(username);
    }

    public LoginResponseDto login(LoginRequestDto dto){
        Optional<Auth> auth = authRepository.findOptionalByUsernameAndPassword(dto.getUsername(), dto.getPassword());

        if (auth.isEmpty()){
            throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);
        }

        LoginResponseDto dto1 = IAuthMapper.INSTANCE.toLoginResponseDto(auth.get());
        String token = tokenManager.createToken(dto1.getId());
        dto1.setToken(token);
        return dto1;
    }

    public Boolean activateUser(ActivatedRequestDto dto){
        Optional<Auth> auth = authRepository.findById(dto.getId());

        if (auth.isPresent()){
            if (dto.getActivatedCode().equals(auth.get().getActivatedCode())){
                auth.get().setStatus(Status.ACTIVE);
                save(auth.get());
                cacheManager.getCache("findactiveprofile").clear();
                userManager.activate(dto.getId());
                return true;
            }else {
                throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
            }
        }else {
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }
    @Cacheable(value = "redis_example")
    public String  redisExample(String value){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value;
    }


    public List<RoleResponseDto> findByRole(String roles){
        Role roles1 = null;
        try {
            roles1=Role.valueOf(roles.toUpperCase());
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.ROLE_NOT_FOUND);
        }
        return authRepository.findAllByRole(roles1).stream().
                map(x->
                        IAuthMapper.INSTANCE.toRoleResponseDto(x)
                ).collect(Collectors.toList());
    }

    @Transactional
    public Boolean deleteAuth(String token){
        Optional<Long> authid = tokenManager.getByIdFromToken(token);
        if (authid.isEmpty()){
            throw new AuthServiceException(ErrorType.GECERSIZ_TOKEN);
        }
        Optional<Auth> auth = authRepository.findById(authid.get());
        if (auth.isEmpty()){
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
        try{
            auth.get().setStatus(Status.DELETED);
            save(auth.get());
            userManager.delete(authid.get());
            return true;
        }catch (Exception e){
            throw new AuthServiceException(ErrorType.KULLANICI_SILINEMEDI);
        }
    }

    public List<AuthListResponseDto> findAllByActiveAndPending() {
        return IAuthMapper.INSTANCE.toAuthListResponseDto(authRepository.findAllByActiveAndPendingAuth().get());
    }

    public List<AuthListResponseDto> findAllByActiveAndPending2() {
        List<Status> statusList =List.of(Status.ACTIVE, Status.PENDING);
        return IAuthMapper.INSTANCE.toAuthListResponseDto(authRepository.findAllByStatusIn(statusList));
    }

    public boolean updateAuth(UpdateUsernameEmailModel model) {

        Optional<Auth> auth = authRepository.findById(model.getId());

        if (auth.isPresent()) {
            auth.get().setEmail(model.getEmail());
            auth.get().setUsername(model.getUsername());
            save(auth.get());
            return true;
        } else {
            throw new AuthServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }
    public RoleResponseDto findByid(Long id){
        return IAuthMapper.INSTANCE.toRoleResponseDto(authRepository.findById(id).get());
    }

}
