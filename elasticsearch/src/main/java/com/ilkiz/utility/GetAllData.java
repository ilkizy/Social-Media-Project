package com.ilkiz.utility;

import com.ilkiz.dto.response.UserProfileResponseDtoNew;
import com.ilkiz.manager.IUserManager;
import com.ilkiz.mapper.IUserMapper;
import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllData {

    private final UserProfileService userProfileService;
    private final IUserManager userManager;

   // @PostConstruct
    public void init(){
        List<UserProfileResponseDtoNew> userProfiles = userManager.findAll().getBody();
        userProfileService.saveAll(userProfiles.stream()
                .map(dto ->IUserMapper.INSTANCE.toUserProfile(dto))
                .collect(Collectors.toList()));
    }

}
