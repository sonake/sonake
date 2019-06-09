package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.UserRole;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.UserRoleDao;
import com.hc.admin.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:43
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleDao, UserRole> implements UserRoleService {

    @Override
    public PageUtils queryPage(UserRole userRole) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<UserRole> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<UserRole> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}