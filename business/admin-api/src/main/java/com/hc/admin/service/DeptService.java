package com.hc.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.admin.bean.Dept;
import com.hc.admin.common.PageUtils;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
public interface DeptService extends IService<Dept> {

    PageUtils queryPage(Dept dept);
}

