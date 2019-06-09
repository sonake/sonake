package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.Role;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:44
 */
public interface RoleService extends IService<Role> {

    PageUtils queryPage(Role role);
}

