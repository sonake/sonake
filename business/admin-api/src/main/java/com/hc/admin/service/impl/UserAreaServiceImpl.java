package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.UserArea;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.UserAreaDao;
import com.hc.admin.service.UserAreaService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:40
 */
@Service("userAreaService")
public class UserAreaServiceImpl extends ServiceImpl<UserAreaDao, UserArea> implements UserAreaService {

    @Override
    public PageUtils queryPage(UserArea userArea) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<UserArea> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<UserArea> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}