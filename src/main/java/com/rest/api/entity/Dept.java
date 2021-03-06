package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest.api.common.entity.CommonDateEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Getter
@NoArgsConstructor
//@AllArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Post Entity에서 User와의 관계를 Json으로 변환시 오류 방지를 위한 코드
public class Dept extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptID;

    @Column(name = "deptname", nullable = false, length = 100)
    private String deptname;


   /* @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "USER_DEPT",
            joinColumns = {
                    @JoinColumn(name = "deptID", referencedColumnName = "deptID")},
            inverseJoinColumns = {
                    @JoinColumn(name = "msrl", referencedColumnName = "msrl")
            }
    )

    private List<User> users = new ArrayList<>();*/


    // @ElementCollection(fetch = FetchType.EAGER) @ElementCollection은 user-depts를 생성시킴

    //@ManyToMany(mappedBy = "detps", cascade = CascadeType.ALL)
    //private List<User> users = new ArrayList<>();

    /*private List<User> user;*/

    public Dept(Long deptID, String name, List<User> users) {
        this.deptID = deptID;
        this.deptname = name;
        this.users = users;
    }

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "DEPT_USER",
            joinColumns = @JoinColumn(name = "dept_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users = new ArrayList<>();

    @JsonIgnore

    public List<User> getUsers() {
        return users;
    }

    public void setusers(List<User> users) {
        this.users = users;
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


}
