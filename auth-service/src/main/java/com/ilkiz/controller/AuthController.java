package com.ilkiz.controller;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.LoginRequestDto;
import com.ilkiz.dto.request.RegisterRequestDto;
import com.ilkiz.dto.response.AuthListResponseDto;
import com.ilkiz.dto.response.LoginResponseDto;
import com.ilkiz.dto.response.RegisterResponseDto;
import com.ilkiz.dto.response.RoleResponseDto;
import com.ilkiz.repository.entity.Auth;
import com.ilkiz.service.AuthService;
import com.ilkiz.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.ilkiz.constants.ApiUrls.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenManager tokenManager;

    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){

        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping(LOGIN)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activateUser(@RequestBody ActivatedRequestDto dto){
        return ResponseEntity.ok(authService.activateUser(dto));
    }
    @GetMapping(TOKEN)
    public String getToken(Long id){
        return tokenManager.createToken(id);
    }

    @GetMapping(GETID)
    public Long getId(String token){
        return tokenManager.getByIdFromToken(token).get();
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<Auth>> findAll(){
        return ResponseEntity.ok(authService.findAll());
    }

/*    @GetMapping("/findallbyactiveandpending")
    public ResponseEntity<List<AuthListResponseDto>> findAllByActiveAndPending(){
        return ResponseEntity.ok(authService.findAllByActiveAndPending());
    }

    @GetMapping("/findallbystatusin")
    public ResponseEntity<List<AuthListResponseDto>> findAllByStatusIn(){
        return ResponseEntity.ok(authService.findAllByActiveAndPending2());
    }*/

    @GetMapping("/redis/{value}")
    @Cacheable(value = "example")
    public String redisExample(String value){
        return value;
    }

    @GetMapping("/findbyrole/{roles}")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(@PathVariable String roles) {

        return ResponseEntity.ok(authService.findByRole(roles));
    }

    @GetMapping("/findbyid/{id}")
    public ResponseEntity<RoleResponseDto> findAllByRoleId(@PathVariable Long id) {

        return ResponseEntity.ok(authService.findByid(id));
    }
/*    @DeleteMapping(DELETE)
    public  ResponseEntity<Boolean> deleteAuth(@RequestBody String token){
        return ResponseEntity.ok(authService.deleteAuth(token));
    }*/
}
