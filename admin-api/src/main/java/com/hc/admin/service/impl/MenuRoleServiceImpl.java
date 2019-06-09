package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.admin.bean.MenuRole;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.MenuRoleDao;
import com.hc.admin.service.MenuRoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
@Service("menuRoleService")
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleDao, MenuRole> implements MenuRoleService {

    @Override
    public PageUtils queryPage(MenuRole menuRole) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<MenuRole> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<MenuRole> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}