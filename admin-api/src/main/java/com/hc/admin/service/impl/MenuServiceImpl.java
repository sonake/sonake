package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.Menu;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.MenuDao;
import com.hc.admin.service.MenuService;
import org.springframework.stereotype.Service;
import java.util.Map;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
@Service("menuService")
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    @Override
    public PageUtils queryPage(Menu menu) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<Menu> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<Menu> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}