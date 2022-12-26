package com.ilkiz.dto.response;

import com.ilkiz.repository.enums.Role;
import com.ilkiz.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponseDto {

    Long id;
    String username;
    String email;
    Role role;
    Status status;
    String token;
}
