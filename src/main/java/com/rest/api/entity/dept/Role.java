package com.rest.api.entity.dept;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest.api.entity.User;
import com.rest.api.entity.common.CommonDateEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Role extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(nullable = false, length = 50)
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Dept dept; // 롤과 부서 관계 - N:1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user;  // 롤과 - 회원의 관계 - N:1

    // Join 테이블이 Json결과에 표시되지 않도록 처리.
    @JsonIgnore
    public Dept getDept() {
        return dept;
    }

    // 생성자
    public Role(User user, Dept dept, String name) {
        this.user = user;
        this.dept = dept;
        this.roleName = name;
    }

    // 수정시 데이터 처리
    public Role setUpdate(String name) {
        this.roleName = name;
        return this;
    }
}
