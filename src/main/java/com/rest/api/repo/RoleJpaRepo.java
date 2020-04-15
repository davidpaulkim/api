package com.rest.api.repo;

import com.rest.api.entity.Dept;
import com.rest.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleJpaRepo extends JpaRepository<Role, Long> {
    List<Role> findByDeptOrderByRoleIdDesc(Dept dept);
}
