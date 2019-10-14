package com.hc.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.common.bean.system.SysMenu;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 11:35
 * @description：系统菜单mapper
 * @version: 1.0
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 通过用户名查询用户权限
     * @param username
     * @return
     */
    List<SysMenu> findPermsByUsername(@RequestParam("username") String username);
}
