package com.ilkiz.mapper;

import com.ilkiz.dto.request.ActivatedRequestDto;
import com.ilkiz.dto.request.NewUserCreateRequestDto;
import com.ilkiz.dto.request.UpdateRequestDto;
import com.ilkiz.dto.response.UserProfileResponseDto;
import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.repository.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T16:41:19+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile toUserProfile(NewUserCreateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.authid( dto.getAuthid() );
        userProfile.username( dto.getUsername() );
        userProfile.email( dto.getEmail() );

        return userProfile.build();
    }

    @Override
    public UserProfile toUserProfile(ActivatedRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        if ( dto.getId() != null ) {
            userProfile.id( String.valueOf( dto.getId() ) );
        }

        return userProfile.build();
    }

    @Override
    public UserProfile toUserProfile(UpdateRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.username( dto.getUsername() );
        userProfile.name( dto.getName() );
        userProfile.email( dto.getEmail() );
        userProfile.phone( dto.getPhone() );
        userProfile.photo( dto.getPhoto() );
        userProfile.address( dto.getAddress() );
        userProfile.about( dto.getAbout() );

        return userProfile.build();
    }

    @Override
    public UserProfileResponseDto toUserProfileResponseDto(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponseDto.UserProfileResponseDtoBuilder userProfileResponseDto = UserProfileResponseDto.builder();

        userProfileResponseDto.username( userProfile.getUsername() );
        userProfileResponseDto.name( userProfile.getName() );
        userProfileResponseDto.email( userProfile.getEmail() );
        userProfileResponseDto.phone( userProfile.getPhone() );
        userProfileResponseDto.photo( userProfile.getPhoto() );
        userProfileResponseDto.address( userProfile.getAddress() );
        userProfileResponseDto.about( userProfile.getAbout() );
        userProfileResponseDto.status( userProfile.getStatus() );

        return userProfileResponseDto.build();
    }

    @Override
    public UserProfileResponseDtoNew toUserProfileResponseDtoNew(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponseDtoNew.UserProfileResponseDtoNewBuilder userProfileResponseDtoNew = UserProfileResponseDtoNew.builder();

        userProfileResponseDtoNew.id( userProfile.getId() );
        userProfileResponseDtoNew.authid( userProfile.getAuthid() );
        userProfileResponseDtoNew.username( userProfile.getUsername() );
        userProfileResponseDtoNew.name( userProfile.getName() );
        userProfileResponseDtoNew.email( userProfile.getEmail() );
        userProfileResponseDtoNew.phone( userProfile.getPhone() );
        userProfileResponseDtoNew.photo( userProfile.getPhoto() );
        userProfileResponseDtoNew.address( userProfile.getAddress() );
        userProfileResponseDtoNew.about( userProfile.getAbout() );
        userProfileResponseDtoNew.created( userProfile.getCreated() );
        userProfileResponseDtoNew.updated( userProfile.getUpdated() );
        userProfileResponseDtoNew.status( userProfile.getStatus() );

        return userProfileResponseDtoNew.build();
    }

    @Override
    public List<UserProfileResponseDtoNew> toUserProfileResponseDtoNew(List<UserProfile> userProfiles) {
        if ( userProfiles == null ) {
            return null;
        }

        List<UserProfileResponseDtoNew> list = new ArrayList<UserProfileResponseDtoNew>( userProfiles.size() );
        for ( UserProfile userProfile : userProfiles ) {
            list.add( toUserProfileResponseDtoNew( userProfile ) );
        }

        return list;
    }
}
