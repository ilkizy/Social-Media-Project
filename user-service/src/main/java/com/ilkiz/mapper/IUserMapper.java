package com.ilkiz.mapper;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.NewUserCreateRequestDto;
import com.ilkiz.dto.request.UpdateRequestDto;
import com.ilkiz.dto.response.UserProfileResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    UserProfile toUserProfile(final NewUserCreateRequestDto dto);
    UserProfile toUserProfile(final ActivatedRequestDto dto);
    UserProfile toUserProfile(final UpdateRequestDto dto);

    UserProfileResponseDto toUserProfileResponseDto (final UserProfile userProfile);
    UserProfileResponseDtoNew toUserProfileResponseDtoNew(final UserProfile userProfile);
    List<UserProfileResponseDtoNew> toUserProfileResponseDtoNew(final List<UserProfile> userProfiles);

}
