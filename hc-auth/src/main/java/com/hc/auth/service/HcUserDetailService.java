package com.hc.auth.service;

import bean.HcAuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HcAuthUser hcAuthUser=new HcAuthUser();
        hcAuthUser.setUsername(username);
        hcAuthUser.setPassword(passwordEncoder.encode("123456"));
        return new User(username,hcAuthUser.getPassword(),hcAuthUser.isEnabled(),
                        hcAuthUser.isAccountNonExpired(),hcAuthUser.isCredentialsNonExpired(),
                        hcAuthUser.isAccountNonLocked(), AuthorityUtils.commaSeparatedStringToAuthorityList("user:add"));
    }
}
