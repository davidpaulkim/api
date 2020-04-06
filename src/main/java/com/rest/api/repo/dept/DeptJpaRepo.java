package com.rest.api.repo.dept;

import com.rest.api.entity.dept.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptJpaRepo extends JpaRepository<Dept, Long> {
    Dept findByName(String name);
}
