package com.hc.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.system.SysUserRole;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:15
 * @description：用户角色信息关联
 * @version: 1.0
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    void deleteUserRolesByRoleId(String[] roleIds);

    void deleteUserRolesByUserId(String[] userIds);
}
