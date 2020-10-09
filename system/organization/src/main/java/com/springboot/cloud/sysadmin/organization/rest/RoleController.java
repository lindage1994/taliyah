package com.springboot.cloud.sysadmin.organization.rest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleForm;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleQueryForm;
import com.springboot.cloud.sysadmin.organization.entity.form.RoleUpdateForm;
import com.springboot.cloud.sysadmin.organization.entity.param.RoleQueryParam;
import com.springboot.cloud.sysadmin.organization.entity.po.Role;
import com.springboot.cloud.sysadmin.organization.service.IRoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.iahsnil.common.response.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/role")
@Api("role")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增一个角色")
    @ApiImplicitParam(name = "roleForm", value = "新增角色form表单", required = true, dataType = "RoleForm")
    @PostMapping
    public ResponseBean<Boolean> add(@Valid @RequestBody RoleForm roleForm) {
        log.debug("name:{}", roleForm);
        Role role = roleForm.toPo(Role.class);
        return ResponseBean.success(roleService.add(role));
    }

    @ApiOperation(value = "删除角色", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "string")
    @DeleteMapping(value = "/{id}")
    public ResponseBean<Boolean> delete(@PathVariable String id) {
        return ResponseBean.success(roleService.delete(id));
    }

    @ApiOperation(value = "修改角色", notes = "修改指定角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "roleForm", value = "角色实体", required = true, dataType = "RoleUpdateForm")
    })
    @PutMapping(value = "/{id}")
    public ResponseBean<Boolean> update(@PathVariable String id, @Valid @RequestBody RoleUpdateForm roleUpdateForm) {
        Role role = roleUpdateForm.toPo(id, Role.class);
        return ResponseBean.success(roleService.update(role));
    }

    @ApiOperation(value = "获取角色", notes = "获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "角色ID", required = true, dataType = "string")
    @GetMapping(value = "/{id}")
    public ResponseBean<Role> get(@PathVariable String id) {
        log.debug("get with id:{}", id);
        return ResponseBean.success(roleService.get(id));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取所有角色")
    @GetMapping(value = "/all")
    public ResponseBean<List<Role>> get() {
        return ResponseBean.success(roleService.getAll());
    }

    @ApiOperation(value = "查询角色", notes = "根据用户id查询用户所拥有的角色信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @GetMapping(value = "/user/{userId}")
    public ResponseBean<List<Role>> query(@PathVariable String userId) {
        log.debug("query with userId:{}", userId);
        return ResponseBean.success(roleService.query(userId));
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件搜索角色信息")
    @ApiImplicitParam(name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = ResponseBean.class)
    )
    @PostMapping(value = "/conditions")
    public ResponseBean<IPage<Role>> query(@Valid @RequestBody RoleQueryForm roleQueryForm) {
        log.debug("query with name:{}", roleQueryForm);
        return ResponseBean.success(roleService.query(roleQueryForm.getPage(), roleQueryForm.toParam(RoleQueryParam.class)));
    }
}