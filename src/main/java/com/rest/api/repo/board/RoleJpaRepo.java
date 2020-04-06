package com.rest.api.repo.board;

import com.rest.api.entity.board.Dept;
import com.rest.api.entity.board.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleJpaRepo extends JpaRepository<Role, Long> {
    List<Role> findByDeptOrderByRoleIdDesc(Dept dept);
}