package com.rest.api.service;

import com.rest.api.common.CResourceNotExistException;
import com.rest.api.entity.Dept;
import com.rest.api.entity.User;
import com.rest.api.repo.DeptJpaRepo;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.cache.CacheSevice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/*import com.rest.api.repo.RoleJpaRepo;*/

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeptService {

    private final DeptJpaRepo deptJpaRepo;
    private final UserJpaRepo userJpaRepo;
    /*private final RoleJpaRepo roleJpaRepo;*/

    private final CacheSevice cacheSevice;

    public List<User> findDeptUsers(String deptName) {
        System.out.println("---------------3:findusers:" + userJpaRepo.findByDeptNameOrderByMsrlDesc(deptName));
        return userJpaRepo.findByDeptNameOrderByMsrlDesc(deptName);
    }

    public List<Dept> findDepts() {
        return deptJpaRepo.findAll();
    }


    public Dept insertDept(String deptName) {
        return deptJpaRepo.save(Dept.builder().deptname(deptName).build());
    }

    // 부서 이름으로 부서을 조회. 없을경우 CResourceNotExistException 처리
//    @Cacheable(value = CacheKey.Dept, key = "#deptName", unless = "#result == null")

    public Dept findDept(String deptName) {
        System.out.println("1-----dept:" + deptJpaRepo.findByDeptname(deptName));
        return Optional.ofNullable(deptJpaRepo.findByDeptname(deptName)).orElseThrow(CResourceNotExistException::new);
    }

    // 부서 이름으로 부서에 속한 User들을 조회. 없을경우 CResourceNotExistException 처리

//    @Cacheable(value = CacheKey.Dept, key = "#deptName", unless = "#result == null")
    /*public List<User> findUsers(String deptName) {

        return userJpaRepo.findByUidOrderByDeptIdDesc(deptName);
    }*/
/*
    // @Cacheable(value = CacheKey.ROLES, key = "#deptName", unless = "#result == null")
    public List<Role> findRoles(String deptName) {
        return roleJpaRepo.findByDeptNameOrderByRoleIdDesc(findDept(deptName));
    }
    // 부서 이름으로 역할 리스트 조회.

    // 역할ID로 역할 단건 조회. 없을경우 CResourceNotExistException 처리

    // @Cacheable(value = CacheKey.ROLE, key = "#roleId", unless = "#result == null")
    public Role getRole(long roleId) {
        return roleJpaRepo.findById(roleId).orElseThrow(CResourceNotExistException::new);
    }

    // 역할을 등록합니다. 역할의 회원UID가 조회되지 않으면 CUserNotFoundException 처리합니다.
 //   @CacheEvict(value = CacheKey.ROLES, key = "#deptName")
    public Role writeRole(String uid, String deptName, ParamsRole paramsRole) {
        Dept dept = findDept(deptName);
        Role role = new Role(uid, deptName, paramsRole.getRoleName());
        return roleJpaRepo.save(role);
    }

    // 역할을 수정합니다. 역할 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    //@CachePut(value = CacheKey.POST, key = "#roleId") 갱신된 정보만 캐시할경우에만 사용!
    public Role updateRole(long roleId, String uid, ParamsRole paramsRole) {
        Role role = getRole(roleId);
        User user = role.getUser();
        if (!uid.equals(user.getUid()))
            throw new CNotOwnerException();

        // 영속성 컨텍스트의 변경감지(dirty checking) 기능에 의해 조회한 Role내용을 변경만 해도 Update쿼리가 실행됩니다.
        role.setUpdate(paramsRole.getRoleName());
         //       cacheSevice.deleteDeptCache(role.getRoleId(), role.getDept().getName());
        return role;
    }

    // 역할을 삭제합니다. 역할 등록자와 로그인 회원정보가 틀리면 CNotOwnerException 처리합니다.
    public boolean deleteRole(long roleId, String uid) {
        Role role = getRole(roleId);
        User user = role.getUser();
        if (!uid.equals(user.getUid()))
            throw new CNotOwnerException();
        roleJpaRepo.delete(role);
 //       cacheSevice.deleteDeptCache(role.getRoleId(), role.getDept().getName());
        return true;
    }*/
}
