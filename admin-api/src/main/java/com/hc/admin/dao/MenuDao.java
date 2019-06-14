package com.hc.admin.dao;

import com.hc.admin.bean.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * 
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
public interface MenuDao extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String userName);
    List<Menu> findUserMenus(String userName);
	
}
