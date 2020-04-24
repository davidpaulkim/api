package com.rest.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) // Post Entity에서 User와의 관계를 Json으로 변환시 오류 방지를 위한 코드
public class Profile extends CommonDateEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long profileID;

    @Column(name = "profname", nullable = false, length = 100)
    private String profname;

    @Column(name = "deptname", nullable = false, length = 100)
    private String deptname;

    private boolean current;

    @ManyToOne
    @JoinColumn(name = "userid")
    private User user;


    @ManyToOne
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
