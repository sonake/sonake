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
        Map<String, Object> dataTable = ToolUtil.getDataTable(roleService.findPage(role, queryRequest));
        return Rs.success(dataTable);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('role:add')")
    public void addUser(@Valid SysRole role) throws HcException {
        try {
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            role.setCreateBy(1L);
            role.setUpdateBy(1L);
            this.roleService.save(role);
        } catch (Exception e) {
            String message = "新增角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('role:update')")
    public void updateUser(@Valid SysRole role) throws HcException {
        try {
            this.roleService.updateById(role);
        } catch (Exception e) {
            String message = "修改角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('role:delete')")
    public void deleteUsers(@NotBlank(message = "{required}") String roleIds) throws HcException {
        try {
            String[] ids = roleIds.split(StringPool.COMMA);
            List list  = new ArrayList(Arrays.asList(ids));
            this.roleService.removeByIds(list);
        } catch (Exception e) {
            String message = "删除角色失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
