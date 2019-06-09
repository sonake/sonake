package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.admin.bean.Dept;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.DeptDao;
import com.hc.admin.service.DeptService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@Service("deptService")
public class DeptServiceImpl extends ServiceImpl<DeptDao, Dept> implements DeptService {

    @Override
    public PageUtils queryPage(Dept dept) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<Dept> queryWrapper=new LambdaQueryWrapper<>();
        long total=this.count();
        IPage<Dept> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

}