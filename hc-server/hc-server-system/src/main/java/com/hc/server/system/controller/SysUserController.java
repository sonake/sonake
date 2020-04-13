package com.hc.server.system.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysUser;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.server.system.service.ISysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 12:13
 * @description：用户管理接口
 * @version: 1.0
 */
@Slf4j
@Validated
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class SysUserController {
    private ISysUserService userService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('user:view')")
    public Object userList(QueryPage queryRequest, SysUser user) {
        return Rs.success(userService.findUserDetail(user,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('user:add')")
    public Object addUser(@Valid SysUser user) throws HcException {
        try {
            this.userService.createUser(user);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增用户失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('user:update')")
    public Object updateUser(@Valid SysUser user) throws HcException {
        try {
            this.userService.updateUser(user);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改用户失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('user:delete')")
    public Object deleteUsers(@NotBlank(message = "{required}") String userIds) throws HcException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.userService.deleteUsers(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除用户失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
