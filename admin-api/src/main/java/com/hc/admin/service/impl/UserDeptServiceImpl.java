package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.UserDept;
import com.hc.admin.dao.UserDeptDao;
import com.hc.admin.service.UserDeptService;
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
@Service("userDeptService")
public class UserDeptServiceImpl extends ServiceImpl<UserDeptDao, UserDept> implements UserDeptService {

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //构造自定义查询条件(自定义添加条件)
//        LambdaQueryWrapper<UserDeptEntity> queryWrapper=new LambdaQueryWrapper<>();
//        long total=this.count();
//        IPage<UserDeptEntity> page = this.page(
//                ToolUtil.getPage(total),
//                queryWrapper
//        );
//
//        return new PageUtils(page);
//    }

}