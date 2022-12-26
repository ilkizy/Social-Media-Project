package com.ilkiz.mapper;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.LoginRequestDto;
import com.ilkiz.dto.request.RegisterRequestDto;
import com.ilkiz.dto.response.AuthListResponseDto;
import com.ilkiz.dto.response.LoginResponseDto;
import com.ilkiz.dto.response.NewUserCreateRequestDto;
import com.ilkiz.dto.response.RegisterResponseDto;
import com.ilkiz.dto.response.RoleResponseDto;
import com.ilkiz.repository.entity.Auth;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-22T22:41:12+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IAuthMapperImpl implements IAuthMapper {

    @Override
    public Auth toAuth(RegisterRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );
        auth.email( dto.getEmail() );

        return auth.build();
    }

    @Override
    public Auth toAuth(LoginRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.username( dto.getUsername() );
        auth.password( dto.getPassword() );

        return auth.build();
    }

    @Override
    public LoginResponseDto toLoginResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        LoginResponseDto.LoginResponseDtoBuilder loginResponseDto = LoginResponseDto.builder();

        loginResponseDto.id( auth.getId() );
        loginResponseDto.username( auth.getUsername() );
        loginResponseDto.email( auth.getEmail() );
        loginResponseDto.role( auth.getRole() );
        loginResponseDto.status( auth.getStatus() );

        return loginResponseDto.build();
    }

    @Override
    public RegisterResponseDto toRegisterResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RegisterResponseDto.RegisterResponseDtoBuilder registerResponseDto = RegisterResponseDto.builder();

        registerResponseDto.id( auth.getId() );
        registerResponseDto.username( auth.getUsername() );
        registerResponseDto.activatedCode( auth.getActivatedCode() );

        return registerResponseDto.build();
    }

    @Override
    public Auth toAuth(ActivatedRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        Auth.AuthBuilder auth = Auth.builder();

        auth.id( dto.getId() );
        auth.email( dto.getEmail() );
        auth.activatedCode( dto.getActivatedCode() );

        return auth.build();
    }

    @Override
    public NewUserCreateRequestDto toNewUserCreateRequestDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        NewUserCreateRequestDto.NewUserCreateRequestDtoBuilder newUserCreateRequestDto = NewUserCreateRequestDto.builder();

        newUserCreateRequestDto.username( auth.getUsername() );
        newUserCreateRequestDto.email( auth.getEmail() );

        return newUserCreateRequestDto.build();
    }

    @Override
    public RoleResponseDto toRoleResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        RoleResponseDto.RoleResponseDtoBuilder roleResponseDto = RoleResponseDto.builder();

        roleResponseDto.id( auth.getId() );
        roleResponseDto.username( auth.getUsername() );
        roleResponseDto.role( auth.getRole() );

        return roleResponseDto.build();
    }

    @Override
    public List<AuthListResponseDto> toAuthListResponseDto(List<Auth> list) {
        if ( list == null ) {
            return null;
        }

        List<AuthListResponseDto> list1 = new ArrayList<AuthListResponseDto>( list.size() );
        for ( Auth auth : list ) {
            list1.add( authToAuthListResponseDto( auth ) );
        }

        return list1;
    }

    protected AuthListResponseDto authToAuthListResponseDto(Auth auth) {
        if ( auth == null ) {
            return null;
        }

        AuthListResponseDto.AuthListResponseDtoBuilder authListResponseDto = AuthListResponseDto.builder();

        authListResponseDto.id( auth.getId() );
        authListResponseDto.username( auth.getUsername() );
        authListResponseDto.email( auth.getEmail() );
        authListResponseDto.role( auth.getRole() );
        authListResponseDto.status( auth.getStatus() );

        return authListResponseDto.build();
    }
}
