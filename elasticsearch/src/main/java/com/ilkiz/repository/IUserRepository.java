package com.ilkiz.repository;

import com.ilkiz.repository.entity.UserProfile;
import com.ilkiz.repository.enums.Status;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends ElasticsearchRepository<UserProfile, String > {

    Optional<UserProfile> findOptionalByAuthid(Long authid);
    Optional<UserProfile> findOptionalByUsername(String username);
    List<UserProfile> findAllByStatus(Status status);
    List<UserProfile> findByUsernameContainingIgnoreCase(String username);
    List<UserProfile> findByEmailContainingIgnoreCase(String email);

}
