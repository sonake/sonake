package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.UserArea;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:40
 */
public interface UserAreaService extends IService<UserArea> {

    PageUtils queryPage(UserArea userArea);
}

