package com.ilkiz.controller;


import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.exceptions.ErrorType;
import com.ilkiz.mapper.IUserMapper;
import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.ElasticsearchCorruptionException;
import org.elasticsearch.ElasticsearchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ilkiz.constants.ApiUrl.*;

@RestController
@RequestMapping(ELASTIC)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @GetMapping(FINDALL)
    public Iterable<UserProfile> findAll(){
        return userProfileService.findAll();
    }

    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDtoNew dto){
        try {
            return ResponseEntity.ok(userProfileService.save(IUserMapper.INSTANCE.toUserProfile(dto)));
        }catch (Exception e) {
            e.printStackTrace();
            throw new ElasticsearchException(String.valueOf(ErrorType.KULLANICI_BULUNAMADI));
        }
    }
    @PostMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody UserProfileResponseDtoNew dto){
        return ResponseEntity.ok(userProfileService.save(IUserMapper.INSTANCE.toUserProfile(dto)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        return ResponseEntity.ok(userProfileService.deleteUser(id));
    }
}
