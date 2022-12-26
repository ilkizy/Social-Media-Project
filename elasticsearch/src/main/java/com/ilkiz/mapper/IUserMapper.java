package com.ilkiz.mapper;

import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.graphql.model.UserProfileInput;
import com.ilkiz.repository.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    UserProfile toUserProfile(final UserProfileResponseDtoNew userProfileResponseDtoNew);

    List<UserProfileResponseDtoNew> toUserProfileResponseDtoNewList(final  List<UserProfile> userProfile);
    UserProfile toUserProfile(final UserProfileInput userProfileInput);
}
