package com.ilkiz.mapper;

import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.graphql.model.UserProfileInput;
import com.ilkiz.repository.entity.UserProfile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-26T16:41:51+0300",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 17.0.4.1 (JetBrains s.r.o.)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public UserProfile toUserProfile(UserProfileResponseDtoNew userProfileResponseDtoNew) {
        if ( userProfileResponseDtoNew == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.authid( userProfileResponseDtoNew.getAuthid() );
        userProfile.username( userProfileResponseDtoNew.getUsername() );
        userProfile.name( userProfileResponseDtoNew.getName() );
        userProfile.email( userProfileResponseDtoNew.getEmail() );
        userProfile.phone( userProfileResponseDtoNew.getPhone() );
        userProfile.photo( userProfileResponseDtoNew.getPhoto() );
        userProfile.address( userProfileResponseDtoNew.getAddress() );
        userProfile.about( userProfileResponseDtoNew.getAbout() );
        userProfile.created( userProfileResponseDtoNew.getCreated() );
        userProfile.updated( userProfileResponseDtoNew.getUpdated() );
        userProfile.status( userProfileResponseDtoNew.getStatus() );

        return userProfile.build();
    }

    @Override
    public List<UserProfileResponseDtoNew> toUserProfileResponseDtoNewList(List<UserProfile> userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        List<UserProfileResponseDtoNew> list = new ArrayList<UserProfileResponseDtoNew>( userProfile.size() );
        for ( UserProfile userProfile1 : userProfile ) {
            list.add( userProfileToUserProfileResponseDtoNew( userProfile1 ) );
        }

        return list;
    }

    @Override
    public UserProfile toUserProfile(UserProfileInput userProfileInput) {
        if ( userProfileInput == null ) {
            return null;
        }

        UserProfile.UserProfileBuilder userProfile = UserProfile.builder();

        userProfile.username( userProfileInput.getUsername() );
        userProfile.email( userProfileInput.getEmail() );

        return userProfile.build();
    }

    protected UserProfileResponseDtoNew userProfileToUserProfileResponseDtoNew(UserProfile userProfile) {
        if ( userProfile == null ) {
            return null;
        }

        UserProfileResponseDtoNew.UserProfileResponseDtoNewBuilder userProfileResponseDtoNew = UserProfileResponseDtoNew.builder();

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
}
