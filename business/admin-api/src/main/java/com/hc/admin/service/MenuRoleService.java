package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.MenuRole;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
public interface MenuRoleService extends IService<MenuRole> {

    PageUtils queryPage(MenuRole menuRoles);
}

