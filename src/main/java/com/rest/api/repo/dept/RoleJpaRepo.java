package com.rest.api.repo.dept;

import com.rest.api.entity.dept.Dept;
import com.rest.api.entity.dept.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleJpaRepo extends JpaRepository<Role, Long> {
    List<Role> findByDeptOrderByRoleIdDesc(Dept dept);
}