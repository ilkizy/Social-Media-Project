package com.ilkiz.manager;

import com.ilkiz.dto.request.UpdateRequestDto;
import com.ilkiz.dto.response.UserProfileResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ilkiz.constants.ApiUrls.*;

@FeignClient(name = "elastic-service", url ="http://localhost:8099/api/v1/elastic" , decode404 = true)
public interface IElasticManager {

    @PostMapping(CREATE)
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfileResponseDtoNew userProfileResponseDtoNew);

    @PostMapping(UPDATE)
    public ResponseEntity<UserProfile> update(@RequestBody UserProfileResponseDtoNew userProfileResponseDtoNew);

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id);
}

