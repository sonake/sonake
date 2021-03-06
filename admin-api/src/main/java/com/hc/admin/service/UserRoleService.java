package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.UserRole;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:43
 */
public interface UserRoleService extends IService<UserRole> {

    PageUtils queryPage(UserRole userRole);
}

