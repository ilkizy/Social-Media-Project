package com.ilkiz.dto.response;

import com.ilkiz.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserProfileResponseDto implements Serializable {


    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Status status;
}
