package com.hc.auth.service;

import com.hc.auth.manager.UserManager;
import com.hc.common.bean.HcAuthUser;
import com.hc.common.bean.system.SysUser;
import com.hc.common.utils.CommonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/8 10:42
 * @description：用于校验用户名密码
 * @version: 1.0
 */
@Service
public class HcUserDetailService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserManager userManager;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user=userManager.findUserByUsername(username);
        if(CommonTools.isNotEmpty(user)){
            String perms = userManager.findUserPermissions(username);
            boolean notBlock = false;
            if(CommonTools.equals(SysUser.STATUS_VALID,user.getStatus())){
                notBlock = true;
            }
            HcAuthUser authUser = new HcAuthUser(user.getUsername(),user.getPassword(),true,true,
                    true,notBlock,AuthorityUtils.commaSeparatedStringToAuthorityList(perms));
            return transSystemUserToAuthUser(authUser,user);
        }else {
            throw new UsernameNotFoundException("");
        }
    }



    private HcAuthUser transSystemUserToAuthUser(HcAuthUser authUser, SysUser sysUser) {
        authUser.setAvatar(sysUser.getAvatar());
        authUser.setDeptId(sysUser.getDeptId());
        authUser.setDeptName(sysUser.getDeptName());
        authUser.setEmail(sysUser.getEmail());
        authUser.setPhone(sysUser.getPhone());
        authUser.setRoleId(sysUser.getRoleId());
        authUser.setRoleName(sysUser.getRoleName());
        authUser.setSex(sysUser.getSex());
        authUser.setId(sysUser.getId());
        authUser.setLastLoginTime(sysUser.getLastLoginTime());
        authUser.setRemark(sysUser.getRemark());
        authUser.setStatus(sysUser.getStatus());
        return authUser;
    }
}
