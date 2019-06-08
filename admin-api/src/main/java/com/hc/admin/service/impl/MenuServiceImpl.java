package com.hc.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.admin.bean.Menu;
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

//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        //构造自定义查询条件(自定义添加条件)
//        LambdaQueryWrapper<MenuEntity> queryWrapper=new LambdaQueryWrapper<>();
//        long total=this.count();
//        IPage<MenuEntity> page = this.page(
//                ToolUtil.getPage(total),
//                queryWrapper
//        );
//
//        return new PageUtils(page);
//    }

}