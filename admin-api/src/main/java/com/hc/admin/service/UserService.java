package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.User;
import com.hc.admin.common.PageUtils;

/**
 * 用户信息表
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
public interface UserService extends IService<User> {

    PageUtils queryPage(User user);
}

