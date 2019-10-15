package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.system.SysUserRole;
import com.hc.server.system.mapper.SysUserRoleMapper;
import com.hc.server.system.service.ISysUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:16
 * @description：
 * @version:
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    /**
     * 根据角色id删除相关联的用户角色信息
     * @param roleIds
     */
    @Override
    public void deleteUserRolesByRoleId(String[] roleIds) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        Arrays.stream(roleIds).forEach(r->{
            queryWrapper.eq(SysUserRole::getRoleId,r);
            remove(queryWrapper);
        });
    }

    /**
     * 根据用户id删除相关的角色关联信息
     * @param userIds
     */
    @Override
    public void deleteUserRolesByUserId(String[] userIds) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        Arrays.stream(userIds).forEach(r->{
            queryWrapper.eq(SysUserRole::getUserId,r);
            remove(queryWrapper);
        });
    }
}
