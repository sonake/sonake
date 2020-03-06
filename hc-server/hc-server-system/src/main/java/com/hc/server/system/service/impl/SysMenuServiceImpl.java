package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.router.RouterMeta;
import com.hc.common.bean.system.SysMenu;
import com.hc.common.utils.ToolUtil;
import com.hc.common.bean.router.VueRouter;
import com.hc.common.utils.TreeUtil;
import com.hc.server.system.mapper.SysMenuMapper;
import com.hc.server.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-12-31 17:58:07
 */
@Service("menuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public IPage<SysMenu> findAll(SysMenu entity, QueryPage request) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        Page<SysMenu> page = new Page<>(request.getPageNo(),request.getPageSize());
        IPage<SysMenu> iPage = this.page(page,queryWrapper);
        return iPage;
    }

    @Override
    public void createOrUpdateMenu(SysMenu entity) {

    }

    @Override
    public void delete(String[] ids) {

    }


    /**
     * 根据用户id查询用户的权限
     * @param userId
     * @return
     */
    @Override
    public List<VueRouter<SysMenu>> findPermsByUserId(Long userId) {
        if(ToolUtil.isEmpty(userId)){
            return null;
        }
        List<SysMenu> menuList = baseMapper.findPermsByUserId(userId);
        List<VueRouter<SysMenu>> routers = new ArrayList<>();

        if(ToolUtil.isNotEmpty(menuList)){
            menuList.forEach(menu -> {
                VueRouter<SysMenu> router =new VueRouter<>();
                router.setId(menu.getId().toString());
                router.setParentId(menu.getPId().toString());
                router.setPath(menu.getPath());
                router.setComponent(menu.getComponent());
                router.setName(menu.getMenuName());
                router.setIcon(menu.getIcon());
                router.setMeta(new RouterMeta(menu.getMenuName(), menu.getIcon(), true));
                routers.add(router);
            });
        }
        return TreeUtil.buildVueRouter(routers);
    }


}
