package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.Menu;
import com.hc.admin.common.PageUtils;

import java.util.List;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
public interface MenuService extends IService<Menu> {

    PageUtils queryPage(Menu menu);
    List<Menu> findUserPermissions(String username);
    List<Menu> findUserMenus(String username);
}

