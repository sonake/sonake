package com.hc.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysRole;
import com.hc.common.bean.system.SysUser;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.common.utils.ToolUtil;
import com.hc.server.system.service.ISysRoleService;
import com.hc.server.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/3/6 12:13
 * @description：角色管理接口
 * @version: 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("role")
public class SysRoleController {
    @Autowired
    private ISysRoleService roleService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('role:view')")
    public Object userList(QueryPage queryRequest, SysRole role) {
        return Rs.success(roleService.findPage(role,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('role:add')")
    public Object addUser(@Valid SysRole role) throws HcException {
        try {
            this.roleService.saveRoleAndPerms(role);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('role:update')")
    public Object updateUser(@Valid SysRole role) throws HcException {
        try {
            this.roleService.updateById(role);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('role:delete')")
    public Object deleteUsers(@NotBlank(message = "{required}") String roleIds) throws HcException {
        try {
            String[] ids = roleIds.split(StringPool.COMMA);
            List list  = new ArrayList(Arrays.asList(ids));
            this.roleService.removeByIds(list);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
