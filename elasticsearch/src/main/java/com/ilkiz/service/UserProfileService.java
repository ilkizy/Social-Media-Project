package com.ilkiz.service;


import com.ilkiz.exceptions.ElasticSearchException;
import com.ilkiz.exceptions.ErrorType;
import com.ilkiz.repository.IUserRepository;
import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.repository.enums.Status;
import com.ilkiz.utility.ServiceManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService extends ServiceManager<UserProfile,String> {

    private final IUserRepository userRepository;

    public UserProfileService(IUserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    public List<UserProfile> findAllContainingUsername(String username) {


        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    public List<UserProfile> findAllByStatus(String status) {

        return userRepository.findAllByStatus(Status.valueOf(status));
    }

    public List<UserProfile> findAllContainingEmail(String email) {

        return userRepository.findByEmailContainingIgnoreCase(email);
    }

    @Transactional
    public Boolean deleteUser(Long id) {
        Optional<UserProfile> userProfile = userRepository.findOptionalByAuthid(id);
        if (userProfile.isPresent()){
            userProfile.get().setStatus(Status.DELETED);
            save(userProfile.get());
            return true;
        }else {
            throw new ElasticSearchException(ErrorType.KULLANICI_BULUNAMADI);
        }
    }

}
