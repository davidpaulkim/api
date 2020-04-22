package com.rest.api.controller;

import com.rest.api.entity.Dept;
import com.rest.api.entity.User;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.repo.DeptJpaRepo;
import com.rest.api.repo.UserJpaRepo;
import com.rest.api.service.DeptService;
import com.rest.api.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*import com.rest.api.entity.Role;*/

@Api(tags = {"3. Dept"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/dept")
public class DeptController {

    private final DeptService deptService;
    private final DeptJpaRepo deptJpaRepo;
    private final UserJpaRepo userJpaRdpo;
    private final ResponseService responseService;

   //-------------1
    // 1-1
   @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
   //     1-2
    @ApiOperation(value = "부서 생성", notes = "신규 부서를 생성한다.")
    @PostMapping(value = "/{deptName}")
             // 1-3
    public SingleResult<Dept> deptCreate(@PathVariable String deptName) {
        return responseService.getSingleResult(deptService.insertDept(deptName));
    }

//------------2
    // 2-1
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
          // 2-2
    @ApiOperation(value = "부서 리스트 조회", notes = "부서 리스트를 조회한다.")
    @GetMapping(value = "/depts")
            //2-3
    public ListResult<Dept> findAllDept() {
        return responseService.getListResult(deptJpaRepo.findAll());
    }


// -------------3
    // 3-1
   @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })

   // 3-2
    @ApiOperation(value = "부서 정보 조회", notes = "부서 정보를 조회한다.")
    @GetMapping(value = "/{deptName}")

   // 3-3
    public SingleResult<Dept> deptGet(@PathVariable String deptName) {
        return responseService.getSingleResult(deptService.findDept(deptName));
    }


   @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })

    @ApiOperation(value = "부서에 속한 사용자 리스트", notes = "부서 사용자 리스트를 조회한다.")
    @GetMapping(value = "/{deptName}/users")
    public ListResult<User> usersGet(@PathVariable String deptName) {
       System.out.println("dept:" + deptJpaRepo.findByName(deptName));
       return responseService.getListResult(deptJpaRepo.findByName(deptName).getHavingUsers());
    }



   /* @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 등록", notes = "역할 등록.")
    @PostMapping(value = "/{deptName}/role")
    public SingleResult<Role> roleInsert(@PathVariable String deptName, @Valid @ModelAttribute ParamsRole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(deptService.writeRole(uid, deptName, role));
    }
*/
  /*  @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })

    @ApiOperation(value = "역할 조회", notes = "역할을 조회한다.")
    @GetMapping(value = "/role/{roleId}")
    public SingleResult<Role> roleGet(@PathVariable long roleId) {
        return responseService.getSingleResult(deptService.getRole(roleId));
    }   *//**//*
    @ApiImplicitParams({

            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 수정", notes = "부서 역할을 수정한다.")
    @PutMapping(value = "/role/{roleId}")
    public SingleResult<Role> roleUpdate(@PathVariable long roleId, @Valid @ModelAttribute ParamsRole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(deptService.updateRole(roleId, uid, role));
    }*/

  /*  @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 삭제", notes = "역할을 삭제한다.")
    @DeleteMapping(value = "/role/{roleId}")
    public CommonResult roleDelete(@PathVariable long roleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        deptService.deleteRole(roleId, uid);
        return responseService.getSuccessResult();
    }*/
}
