package com.ilkiz.service;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.NewUserCreateRequestDto;
import com.ilkiz.dto.request.UpdateRequestDto;
import com.ilkiz.dto.response.RoleResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDto;
import com.ilkiz.exception.ErrorType;
import com.ilkiz.exception.UserServiceException;
import com.ilkiz.manager.IAuthManager;
import com.ilkiz.manager.IElasticManager;
import com.ilkiz.mapper.IUserMapper;
import com.ilkiz.rabbitmq.model.UpdateUsernameEmailModel;
import com.ilkiz.rabbitmq.producer.UpdateUserProducer;
import com.ilkiz.repository.IUserRepository;
import com.ilkiz.repository.enums.Status;
import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.utility.JwtTokenManager;
import com.ilkiz.utility.ServiceManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {

    private final IUserRepository userRepository;
    private final JwtTokenManager tokenManager;
    private final CacheManager cacheManager;
    private final IAuthManager authManager;
    private final UpdateUserProducer updateUserProducer;
    private final IElasticManager elasticManager;

    public UserProfileService(IUserRepository userRepository, JwtTokenManager tokenManager, CacheManager cacheManager, IAuthManager authManager, UpdateUserProducer updateUserProducer, IElasticManager elasticManager) {
        super(userRepository);
        this.userRepository = userRepository;
        this.tokenManager = tokenManager;
        this.cacheManager = cacheManager;
        this.authManager = authManager;
        this.updateUserProducer = updateUserProducer;
        this.elasticManager = elasticManager;
    }

    @Transactional
    public UserProfile create(NewUserCreateRequestDto dto) {
        try {
            UserProfile userProfile = userRepository.save(IUserMapper.INSTANCE.toUserProfile(dto));
            elasticManager.createUser(IUserMapper.INSTANCE.toUserProfileResponseDtoNew(userProfile));
            System.out.println(dto);
            return userProfile;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }

    public Boolean update(UpdateRequestDto dto) {

        try {
            Optional<Long> authid = tokenManager.getByIdFromToken(dto.getToken());
            if (authid.isPresent()) {
                Optional<UserProfile> userProfile = userRepository.findOptionalByAuthid(authid.get());
                if (userProfile.isPresent()) {
                    cacheManager.getCache("findbyusername").evict(userProfile.get().getUsername().toUpperCase());
                    userProfile.get().setUsername(dto.getUsername());
                    userProfile.get().setName(dto.getName());
                    userProfile.get().setEmail(dto.getEmail());
                    userProfile.get().setPhone(dto.getPhone());
                    userProfile.get().setPhoto(dto.getPhoto());
                    userProfile.get().setAddress(dto.getAddress());
                    userProfile.get().setAbout(dto.getAbout());
                    userProfile.get().setUpdated(System.currentTimeMillis());
                    save(userProfile.get());
                    elasticManager.update(IUserMapper.INSTANCE.toUserProfileResponseDtoNew(userProfile.get()));
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }

        return false;
    }

    public boolean updateUserWithRabbitMq(UpdateRequestDto dto) {

        try {
            Optional<Long> authid = tokenManager.getUserId(dto.getToken());
            if (authid.isPresent()) {
                Optional<UserProfile> userProfileDb = userRepository.findOptionalByAuthid(authid.get());
                try {
                    if (userProfileDb.isPresent()) {
                        boolean check = checkingUsernameAndEmail(dto, userProfileDb.get());
                        cacheManager.getCache("findbyusername").evict(userProfileDb.get().getUsername().toUpperCase());
                        userProfileDb.get().setEmail(dto.getEmail());
                        userProfileDb.get().setAddress(dto.getAddress());
                        userProfileDb.get().setAbout(dto.getAbout());
                        userProfileDb.get().setName(dto.getName());
                        userProfileDb.get().setUsername(dto.getUsername());
                        userProfileDb.get().setPhone(dto.getPhone());
                        userProfileDb.get().setPhone(dto.getPhoto());
                        save(userProfileDb.get());
                        if (check) {
                            updateUserProducer.sendUpdateUser(UpdateUsernameEmailModel.builder()
                                    .email(userProfileDb.get().getEmail())
                                    .username(userProfileDb.get().getUsername()).
                                    id(userProfileDb.get().getAuthid()).build());
                        }
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UserServiceException(ErrorType.GECERSIZ_TOKEN);
        }
        return false;
    }

    public Boolean checkingUsernameAndEmail(UpdateRequestDto dto, UserProfile userProfile) {


        if (!dto.getUsername().equals(userProfile.getUsername()) || !dto.getEmail().equals(userProfile.getEmail())) {
            return true;
        }

        return false;
    }

    public Boolean activate(ActivatedRequestDto dto) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthid(dto.getId());
        if (userProfile.isEmpty()) {
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return true;
    }

    public Boolean activate(Long authid) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthid(authid);
        if (userProfile.isEmpty()) {
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
        userProfile.get().setStatus(Status.ACTIVE);
        save(userProfile.get());
        return true;
    }


    @Cacheable(value = "findbyusername", key = "#username.toUpperCase()")
    public UserProfileResponseDto findByUsername(String username) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByUsername(username);
        if (userProfile.isPresent()) {
            UserProfileResponseDto dto = IUserMapper.INSTANCE.toUserProfileResponseDto(userProfile.get());
            return dto;
        } else {
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }

    @Cacheable(value = "findactiveprofile")
    public List<UserProfile> findAllActiveProfile() {
        return userRepository.getActiveProfile();
    }

    public List<RoleResponseDto> findByRole(String role) {
        return authManager.findAllByRole(role).getBody();
    }

    public Optional<UserProfile> findByAuthid(Long authid) {
        return userRepository.findOptionalByAuthid(authid);
    }
/*
    @Transactional
    public Boolean deleteUser(Long id) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthid(id);
        if (userProfile.isPresent()){
            userProfile.get().setStatus(Status.DELETED);
            save(userProfile.get());
            elasticManager.delete(id);
            return true;
        }else {
            throw new UserServiceException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }*/
}
