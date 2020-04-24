package com.rest.api.repo;

import com.rest.api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileJpaRepo extends JpaRepository<Profile, Long> {
    /* List<Dept> findByUidOrderByDeptIdDesc(String uid);*/
    Profile findByProfname(String profname);

    Profile findByProfileID(long profileID);

    List<Profile> findAllBy();

}
