package com.ilkiz.manager;

import com.ilkiz.dto.response.RoleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@FeignClient(name = "auth-service", url ="http://localhost:8091/api/v1/auth" , decode404 = true)
public interface IAuthManager {

    @GetMapping("/findbyrole/{roles}")
    public ResponseEntity<List<RoleResponseDto>> findAllByRole(@PathVariable String roles);
    @GetMapping("/findbyid/{id}")
    public ResponseEntity<RoleResponseDto> findAllByRoleId(@PathVariable Long id);
}
