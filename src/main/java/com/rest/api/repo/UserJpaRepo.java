package com.rest.api.repo;

import com.rest.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid);

    List<User> findByDeptNameOrderByMsrlDesc(String deptName);

    Optional<User> findByUidAndProvider(String uid, String provider);
}
