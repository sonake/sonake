package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.Tree;
import com.hc.common.bean.router.RouterMeta;
import com.hc.common.bean.system.MenuTree;
import com.hc.common.bean.system.SysMenu;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.ToolUtil;
import com.hc.common.bean.router.VueRouter;
import com.hc.common.utils.TreeUtil;
import com.hc.server.system.mapper.SysMenuMapper;
import com.hc.server.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-12-31 17:58:07
 */
@Service("menuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {


    @Override
    public PageUtils findAll(SysMenu menu, QueryPage request) {
        int total=0;
        Map<String, List> result = new HashMap<>(1);
        try {
            LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.orderByAsc(SysMenu::getOrderNum);
            List<SysMenu> menuList = this.baseMapper.selectList(queryWrapper);
            List<MenuTree> menuTrees = new ArrayList<>();
            buildTrees(menuTrees, menuList);

            if (ToolUtil.equals(menu.getType(), SysMenu.TYPE_BUTTON)) {
                result.put("rows", menuTrees);
            } else {
                List<? extends Tree> menuTree = TreeUtil.build(menuTrees);
                result.put("rows", menuTree);
            }
            total = menuList.size();
        } catch (NumberFormatException e) {
            log.error("查询菜单失败", e);
        }
        return new PageUtils(result.get("rows"),total);
    }

    @Override
    public void createOrUpdateMenu(SysMenu entity) {

    }

    @Override
    public void delete(String[] ids) {

    }


    private void buildTrees(List<MenuTree> trees, List<SysMenu> menus) {
        menus.forEach(menu -> {
            MenuTree tree = new MenuTree();
            tree.setId(menu.getId().toString());
            tree.setPId(menu.getPId().toString());
            tree.setLabel(menu.getMenuName());
            tree.setComponent(menu.getComponent());
            tree.setIcon(menu.getIcon());
            tree.setOrderNum(menu.getOrderNum());
            tree.setPath(menu.getPath());
            tree.setType(menu.getType());
            tree.setPerms(menu.getPerms());
            trees.add(tree);
        });
    }

    /**
     * 根据用户id查询用户的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<VueRouter<SysMenu>> findPermsByUserId(Long userId) {
        if (ToolUtil.isEmpty(userId)) {
            return null;
        }
        List<SysMenu> menuList = baseMapper.findPermsByUserId(userId);
        List<VueRouter<SysMenu>> routers = new ArrayList<>();

        if (ToolUtil.isNotEmpty(menuList)) {
            menuList.forEach(menu -> {
                VueRouter<SysMenu> router = new VueRouter<>();
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
