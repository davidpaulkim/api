package com.rest.api.entity;

import com.rest.api.common.entity.CommonDateEntity;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import com.rest.api.entity.RoleName;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Role extends CommonDateEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;

    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return roleId;
    }

    public void setId(Long id) {
        this.roleId = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }
}

/*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Dept dept; // 롤과 부서 관계 - N:1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msrl")
    private User user;  // 롤과 - 회원의 관계 - N:1
*//*


    // Join 테이블이 Json결과에 표시되지 않도록 처리.
*/
/*
    @JsonIgnore
    public Dept getDept() {
        return dept;
    }


*/

/*

    // 생성자
    public Role(User user, Dept dept, List<String> name) {
        this.user = user;
        this.dept = dept;
        this.roleName = name;
    }


    // 수정시 데이터 처리
    public Role setUpdate(List<String> name) {
        this.roleName = name;
        return this;
    }

*/

