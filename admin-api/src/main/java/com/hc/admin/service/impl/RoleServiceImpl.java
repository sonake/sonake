package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.admin.bean.Role;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.RoleDao;
import com.hc.admin.service.RoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:44
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {

    @Override
    public PageUtils queryPage(Role role) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<Role> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<Role> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}