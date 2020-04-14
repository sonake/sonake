package com.hc.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author ：xzyuan
 * @date ：Created in 2020/4/14 18:09
 * @description：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentUser implements Serializable{
    private static long serialVersionUID = 761748087824726463L;

    @JsonIgnore
    private String password;
    private String username;
    @JsonIgnore
    private Set<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Long id;
    private String avatar;
    private String email;
    private String phone;
    private String sex;
    private Long deptId;
    private String deptName;
    private String roleId;
    private String roleName;
    private String remark;
    @JsonIgnore
    private Date lastLoginTime;
    private String description;
    private String status;
}
