package com.rest.api.controller.api.dept;

import com.rest.api.entity.dept.Dept;
import com.rest.api.entity.dept.Role;
import com.rest.api.model.dept.ParamsRole;
import com.rest.api.model.response.CommonResult;
import com.rest.api.model.response.ListResult;
import com.rest.api.model.response.SingleResult;
import com.rest.api.service.ResponseService;
import com.rest.api.service.dept.DeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"3. Dept"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/dept")
public class DeptController {

    private final DeptService deptService;
    private final ResponseService responseService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "부서 생성", notes = "신규 부서을 생성한다.")
    @PostMapping(value = "/{deptName}")
    public SingleResult<Dept> createDept(@PathVariable String deptName) {
        return responseService.getSingleResult(deptService.insertDept(deptName));
    }

    @ApiOperation(value = "부서 정보 조회", notes = "부서 정보를 조회한다.")
    @GetMapping(value = "/{deptName}")
    public SingleResult<Dept> deptInfo(@PathVariable String deptName) {
        return responseService.getSingleResult(deptService.findDept(deptName));
    }

    @ApiOperation(value = "역할 리스트", notes = "역할 리스트를 조회한다.")
    @GetMapping(value = "/{deptName}/roles")
    public ListResult<Role> roles(@PathVariable String deptName) {
        return responseService.getListResult(deptService.findRoles(deptName));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 등록", notes = "역할 등록.")
    @PostMapping(value = "/{deptName}/role")
    public SingleResult<Role> role(@PathVariable String deptName, @Valid @ModelAttribute ParamsRole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(deptService.writeRole(uid, deptName, role));
    }


    @ApiOperation(value = "역할 조회", notes = "역할을 조회한다.")
    @GetMapping(value = "/role/{roleId}")
    public SingleResult<Role> role(@PathVariable long roleId) {
        return responseService.getSingleResult(deptService.getRole(roleId));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 수정", notes = "부서 역할을 수정한다.")
    @PutMapping(value = "/role/{roleId}")
    public SingleResult<Role> role(@PathVariable long roleId, @Valid @ModelAttribute ParamsRole role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(deptService.updateRole(roleId, uid, role));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @ApiOperation(value = "역할 삭제", notes = "역할을 삭제한다.")
    @DeleteMapping(value = "/role/{roleId}")
    public CommonResult deleteRole(@PathVariable long roleId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        deptService.deleteRole(roleId, uid);
        return responseService.getSuccessResult();
    }
}
