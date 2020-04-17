package com.hc.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.common.bean.system.SysUser;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 11:32
 * @description：用户mapper
 * @version: 1.0
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 通过用户名称查询用户信息
     * @param username
     * @return
     */
    SysUser findUserByUsername(@RequestParam("username") String username);


    String selectSubDept(@RequestParam("deptId") Long deptId);

    String selectSubArea(@RequestParam("areaId") Long areaId);

}
