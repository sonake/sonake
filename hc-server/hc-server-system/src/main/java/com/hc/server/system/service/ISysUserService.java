package com.hc.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysUser;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:00
 * @description：用户管理接口
 * @version: 1.0
 */
public interface ISysUserService extends IService<SysUser> {
    /**
     * 查找用户详细信息
     *
     * @param queryPage queryPage
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     */
    IPage<SysUser> findUserDetail(SysUser user, QueryPage queryPage);

    /**
     * 新增用户
     *
     * @param user user
     */
    void createUser(SysUser user);

    /**
     * 修改用户
     *
     * @param user user
     */
    void updateUser(SysUser user);

    /**
     * 删除用户
     *
     * @param ids 用户 id数组
     */
    void deleteUsers(String[] ids);
}
