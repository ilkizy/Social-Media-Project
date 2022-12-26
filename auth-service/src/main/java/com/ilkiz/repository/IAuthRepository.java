package com.ilkiz.repository;

import com.ilkiz.repository.entity.Auth;
import com.ilkiz.repository.enums.Role;
import com.ilkiz.repository.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAuthRepository extends JpaRepository<Auth,Long> {

    @Query("select count(a.username)>0 from Auth as a where a.username=?1")
    Boolean existUsername(String username);

    Optional<Auth> findOptionalByUsernameAndPassword(String username, String password);
    List<Auth> findAllByRole(Role roles);

    @Query("select a from Auth as a where a.status='ACTIVE' or a.status='PENDING'")
    Optional<List<Auth>> findAllByActiveAndPendingAuth();

    List<Auth> findAllByStatusIn(List<Status> list);
}
