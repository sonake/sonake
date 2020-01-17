package com.hc.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.router.VueRouter;
import com.hc.common.bean.system.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-12-31 17:58:07
 */
public interface ISysMenuService extends IService<SysMenu> {

    IPage<SysMenu> findAll(SysMenu entity, QueryPage request);

    void createOrUpdateMenu(SysMenu entity);

    void delete(String[] ids);

    List<VueRouter<SysMenu>> findPermsByUserId(Long userId);
}

