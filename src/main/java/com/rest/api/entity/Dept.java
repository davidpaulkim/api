package com.rest.api.entity;

import com.rest.api.common.entity.CommonDateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Dept extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptID;
    @Column(nullable = false, length = 100)
    private String name;

   /* @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user;  // 부서와 - 회원의 관계 - N:1

    // 생성자
    public Dept(User user, String name) {
        this.user = user;
        this.name = name;
        }
    // 수정시 데이터 처리
    public Dept setUpdate(String name) {
        this.name = name;
        return this;
    }*/
}
