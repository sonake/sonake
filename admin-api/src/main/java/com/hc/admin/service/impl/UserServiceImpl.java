package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.User;
import com.hc.admin.common.PageUtils;
import com.hc.admin.dao.UserDao;
import com.hc.admin.service.UserService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
/**
 * 用户信息表
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {


    @Override
    public PageUtils queryPage(User user) {
        //构造自定义查询条件(自定义添加条件)
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        IPage<User> page = this.page(
                new Page<>(),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public User findByName(String username) {
        User user=baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName,username));
        return user;
    }

}