package com.ilkiz.controller;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.NewUserCreateRequestDto;
import com.ilkiz.dto.request.UpdateRequestDto;
import com.ilkiz.dto.response.RoleResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.exception.ErrorType;
import com.ilkiz.exception.UserServiceException;
import com.ilkiz.mapper.IUserMapper;
import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.ilkiz.constants.ApiUrls.*;
import static com.ilkiz.constants.ApiUrls.FINDALL;

@RestController
@RequestMapping(USER)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping(CREATE)
    public ResponseEntity<Boolean> create(@RequestBody NewUserCreateRequestDto dto){
        try{
            userProfileService.create(dto);
            return ResponseEntity.ok(true);
        }catch (Exception e){
            throw new UserServiceException(ErrorType.KULLANICI_KAYDEDILEMEDI);
        }
    }
    @PostMapping(ACTIVATE)
    public ResponseEntity<Boolean> activate(@RequestBody ActivatedRequestDto dto){
        return ResponseEntity.ok(userProfileService.activate(dto));
    }

    @PostMapping(ACTIVATEBYID)
    public ResponseEntity<Boolean> activate(@PathVariable Long authid){
        return ResponseEntity.ok(userProfileService.activate(authid));
    }

    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> update(@RequestBody @Valid UpdateRequestDto dto){
        return ResponseEntity.ok(userProfileService.update(dto));
    }
    @PutMapping("/updatewithrabbitmq")
    public ResponseEntity<Boolean> updateProfileWithRabbitmq(@RequestBody @Valid UpdateRequestDto dto) {
        return ResponseEntity.ok(userProfileService.updateUserWithRabbitMq(dto));
    }

    @GetMapping(FINDALL)
    public ResponseEntity<List<UserProfileResponseDtoNew>> findAll(){
        return ResponseEntity.ok(IUserMapper.INSTANCE.toUserProfileResponseDtoNew(userProfileService.findAll()));
    }

    @GetMapping("/findbyusername/{username}")
    public UserProfileResponseDto findByUsername(@PathVariable String username){
        return userProfileService.findByUsername(username);
    }

    @GetMapping("/findallactiveprofile")
    public  ResponseEntity<List<UserProfile>> findAllActiveProfile(){

        return ResponseEntity.ok(userProfileService.findAllActiveProfile());
    }

/*    public ResponseEntity<List<RoleResponseDto>> findAllByRole(String role){
        return ResponseEntity.ok(userProfileService.findByRole(role));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(userProfileService.deleteUser(id));
    }*/
}
