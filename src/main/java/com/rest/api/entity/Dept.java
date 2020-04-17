package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.api.common.entity.CommonDateEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Post Entity에서 User와의 관계를 Json으로 변환시 오류 방지를 위한 코드
public class Dept extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptID;
    @Column(nullable = false, length = 100)
    private String name;

    // @ElementCollection(fetch = FetchType.EAGER)
    @ManyToMany(mappedBy = "depts",cascade=CascadeType.ALL)
    private List<User> users = new ArrayList<>();
    /*private List<User> user;*/


/*
    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "DEPT_USER",
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();
*/

    }
/*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_dept", joinColumns = {
            @JoinColumn(name = "deptId"},
                    inverseJoinColumns = {
            @JoinColumn(name = "roleId"})
    private List<Permission> permissions;
   */



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
