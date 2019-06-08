package com.hc.admin.service.impl;

import com.hc.admin.bean.Dept;
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

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //构造自定义查询条件(自定义添加条件)
//        LambdaQueryWrapper<DeptEntity> queryWrapper=new LambdaQueryWrapper<>();
//        long total=this.count();
//        IPage<DeptEntity> page = this.page(
//                ToolUtil.getPage(total),
//                queryWrapper
//        );
//
//        return new PageUtils(page);
//    }

}