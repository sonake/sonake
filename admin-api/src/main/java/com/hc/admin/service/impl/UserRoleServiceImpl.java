package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.UserRole;
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

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //构造自定义查询条件(自定义添加条件)
//        LambdaQueryWrapper<UserRoleEntity> queryWrapper=new LambdaQueryWrapper<>();
//        long total=this.count();
//        IPage<UserRoleEntity> page = this.page(
//                ToolUtil.getPage(total),
//                queryWrapper
//        );
//
//        return new PageUtils(page);
//    }

}