package com.hc.auth.manager;

import com.hc.auth.mapper.SysMenuMapper;
import com.hc.auth.mapper.SysUserMapper;
import com.hc.common.bean.system.SysMenu;
import com.hc.common.bean.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 14:05
 * @description：统一定义和用户相关的业务方法
 * @version: 1.0
 */
@Service
public class UserManager {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysMenuMapper menuMapper;

    public SysUser findUserByUsername(String username){
        return userMapper.findUserByUsername(username);
    }


    public String selectSubDept(Long deptId){
        return userMapper.selectSubDept(deptId);
    }

    public String findUserPermissions(String username) {
        List<SysMenu> userPermissions = menuMapper.findPermsByUsername(username);
        return userPermissions.stream().map(SysMenu::getPerms).collect(Collectors.joining(","));
    }
}
