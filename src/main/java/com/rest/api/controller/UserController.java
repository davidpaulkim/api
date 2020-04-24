package com.rest.api.controller;

import com.rest.api.common.CNotOwnerException;
import com.rest.api.common.CUserNotFoundException;
import com.rest.api.entity.Dept;
import com.rest.api.entity.User;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repo.DeptJpaRepo;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.ResponseService;
import com.rest.api.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api(tags = {"2. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
// @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//@PreAuthorize("hasRole('ROLE_USER')")

public class UserController {

    private final UserJpaRepo userJpaRepo;
    private final DeptJpaRepo deptJpaRepo;
    private final UserService userservice;
    private final ResponseService responseService; // 결과를 처리할 Service

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다")
    @GetMapping(value = "/users")
    public ListResult<User> findAllUser() {
        // 결과데이터가 여러건인경우 getListResult를 이용해서 결과를 출력한다.
        return responseService.getListResult(userJpaRepo.findAll());
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다")
    @GetMapping(value = "/user")
    public SingleResult<User> findUser() {
        // SecurityContext에서 인증받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        // 결과데이터가 단일건인경우 getSingleResult를 이용해서 결과를 출력한다.
        return responseService.getSingleResult(userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 이름/메일 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public SingleResult<User> modify(
            @ApiParam(value = "회원이름", required = true) @RequestParam String name,
            @ApiParam(value = "회원이메일", required = true) @RequestParam String email
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        System.out.println("id:" + id);
        User user = userJpaRepo.findByUid(id).orElseThrow(CUserNotFoundException::new);
        user.setName(name);

        return responseService.getSingleResult(userJpaRepo.save(user));
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 부서/역할 수정", notes = "회원부서/역할을 수정한다")
    @PutMapping(value = "/user/dept/roles")
    public SingleResult updateDeptRoles(
            @ApiParam(value = "이메일", required = true) @RequestParam String uid,
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "부서", required = true) @RequestParam String deptName,
            @ApiParam(value = "역할", required = true) @RequestParam List<String> rolelist
    ) {

        Optional<Dept> dept = Optional.ofNullable(deptJpaRepo.findByDeptname(deptName));
        if (dept.isPresent()) {
            System.out.println("기존 부서가 있음");
        } else {
            System.out.println("기존 부서가 없음");
            deptJpaRepo.save(Dept.builder()
                    .deptname(deptName).build());
        }

        Optional<Optional<User>> user = Optional.ofNullable(userJpaRepo.findByUid(uid));
        if (user.isPresent()) {
            System.out.println("기존 사용자가 있음");
            /*userJpaRepo.save(User.builder()
                    .uid(uid)
                    .name(name)
                    .dept(deptJpaRepo.findByName(deptName))
                    .roles(rolelist)
                    .build());*/
            return responseService.getSingleResult(userservice.updateUser(uid, name, deptName, rolelist));
        } else {
            throw new CNotOwnerException();
        }


    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "회원 삭제", notes = "회원번호(msrl)로 회원정보를 삭제한다")
    @DeleteMapping(value = "/user/{msrl}")
    public CommonResult delete(
            @ApiParam(value = "회원번호", required = true) @PathVariable Long msrl) {
        userJpaRepo.deleteById(msrl);
        // 성공 결과 정보만 필요한경우 getSuccessResult()를 이용하여 결과를 출력한다.
        return responseService.getSuccessResult();
    }
}

