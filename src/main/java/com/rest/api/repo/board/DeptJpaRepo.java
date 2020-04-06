package com.rest.api.repo.board;

import com.rest.api.entity.board.Board;
import com.rest.api.entity.board.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptJpaRepo extends JpaRepository<Dept, Long> {
    Dept findByName(String name);
}
