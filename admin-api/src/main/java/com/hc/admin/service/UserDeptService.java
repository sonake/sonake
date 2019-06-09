package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.User;
import com.hc.admin.bean.UserDept;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:40
 */
public interface UserDeptService extends IService<UserDept> {

    PageUtils queryPage(UserDept userDepts);
}

