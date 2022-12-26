package com.ilkiz.repository;

import com.ilkiz.repository.entity.UserProfile;

import com.ilkiz.repository.enums.Status;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findOptionalByAuthid(Long authid);
    Optional<UserProfile> findOptionalByUsername(String username);
    List<UserProfile> findAllByStatus(Status status);
    List<UserProfile> findByUsernameContainingIgnoreCase(String username);
    List<UserProfile> findByEmailContainingIgnoreCase(String email);

    @Query("select u from UserProfile as u  where u.status='ACTIVE'")
    List<UserProfile>  getActiveProfile();
}
