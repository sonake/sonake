package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysUser;
import com.hc.common.bean.system.SysUserRole;
import com.hc.server.system.mapper.SysUserMapper;
import com.hc.server.system.service.ISysUserRoleService;
import com.hc.server.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:04
 * @description：用户管理实现类
 * @version: 1.0
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IPage<SysUser> findUserDetail(SysUser user, QueryPage request) {
        Page<SysUser> page = new Page<>(request.getPageNo(), request.getPageSize());
        return this.baseMapper.findUserDetailPage(page, user);
    }

    @Override
    @Transactional
    public void createUser(SysUser user) {
        // 创建用户
        user.setCreateTime(new Date());
        user.setAvatar(SysUser.DEFAULT_AVATAR);
        user.setPassword(passwordEncoder.encode(SysUser.DEFAULT_PASSWORD));
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setAreaId(1L);
        user.setDeptId(1L);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        save(user);
        // 保存用户角色
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void updateUser(SysUser user) {
        // 更新用户
//        user.setPassword(null);
//        user.setUsername(null);
        user.setUpdateTime(new Date());
        updateById(user);

        userRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, user.getId()));
        String[] roles = user.getRoleId().split(StringPool.COMMA);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteUsers(String[] userIds) {
        List<String> list = Arrays.asList(userIds);
        removeByIds(list);
        // 删除用户角色
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    private void setUserRoles(SysUser user, String[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(user.getId());
            ur.setRoleId(Long.valueOf(roleId));
            userRoleService.save(ur);
        });
    }
}
