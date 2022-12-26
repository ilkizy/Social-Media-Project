package com.ilkiz.manager;

import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static com.ilkiz.constants.ApiUrl.FINDALL;


@FeignClient(name = "user-service", url = "http://localhost:8091/api/v1/user", decode404 = true)
public interface IUserManager {

    @GetMapping(FINDALL)
    public ResponseEntity<List<UserProfileResponseDtoNew>> findAll();
}
