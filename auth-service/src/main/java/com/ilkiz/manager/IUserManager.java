package com.ilkiz.manager;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.response.NewUserCreateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "http://localhost:8091/api/v1/user", decode404 = true)
public interface IUserManager {

    @PostMapping("/create")
    public ResponseEntity<Boolean> create(@RequestBody NewUserCreateRequestDto dto);
    @PostMapping("/activate")
    public ResponseEntity<Boolean> activate(@RequestBody ActivatedRequestDto dto);
    @PostMapping("/activate/{authid}")
    public ResponseEntity<Boolean> activate(@PathVariable Long authid);
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id);
}
