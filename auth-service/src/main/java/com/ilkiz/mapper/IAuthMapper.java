package com.ilkiz.mapper;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.LoginRequestDto;
import com.ilkiz.dto.response.*;
import com.ilkiz.dto.request.RegisterRequestDto;
import com.ilkiz.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INSTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final RegisterRequestDto dto);

    Auth toAuth(final LoginRequestDto dto);

    LoginResponseDto toLoginResponseDto (final Auth auth);

    RegisterResponseDto toRegisterResponseDto(final Auth auth);

    Auth toAuth(final ActivatedRequestDto dto);

    NewUserCreateRequestDto toNewUserCreateRequestDto(final Auth auth);

    RoleResponseDto toRoleResponseDto(final  Auth auth);

    List<AuthListResponseDto> toAuthListResponseDto(final List<Auth> list);
}
