package com.ilkiz.dto.response;

import com.ilkiz.repository.enums.Role;
import com.ilkiz.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AuthListResponseDto {

    private Long id;
    private String username;
    private String email;
    private Role role;
    private Status status;
}
