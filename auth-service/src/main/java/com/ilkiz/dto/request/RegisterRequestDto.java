package com.ilkiz.dto.request;

import jdk.jfr.Unsigned;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RegisterRequestDto {

    @NotNull(message = "Kullanıcı adı girilmesi zorunludur.")
    @Size(min = 3, max = 16, message = "Kullanıcı adı en az 3 en çok 16 karakter olabilir")
    String username;
    @NotNull(message = "Şifre girilmesi zorunludur.")
    @Size(min = 8, message = "Kullanıcı adı en az 8 karakter olabilir")
    String password;
    @NotNull(message = "Email girilmesi zorunludur.")
    @Email
    String email;
    String admincode;
}
