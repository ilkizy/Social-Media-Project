package com.ilkiz.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginRequestDto {

    @NotNull(message = "Kullanıcı adı girilmesi zorunludur.")
    @Size(min = 3, max = 16, message = "Kullanıcı adı en az 3 en çok 16 karakter olabilir")
    String username;
    @NotNull(message = "Şifre girilmesi zorunludur.")
    @Size(min = 8, message = "Kullanıcı adı en az 8 karakter olabilir")
    String password;
}
