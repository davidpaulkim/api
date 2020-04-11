package com.rest.api.repo;

import com.rest.api.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeptJpaRepo extends JpaRepository<Dept, Long> {
    List<Dept> findByUidOrderByDeptIdDesc(String uid);
    Dept findByName(String name);
    }
