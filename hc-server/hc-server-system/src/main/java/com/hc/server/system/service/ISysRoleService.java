package com.hc.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysRole;
import com.hc.common.bean.system.SysUser;

public interface ISysRoleService extends IService<SysRole> {
    IPage<SysRole> findPage(SysRole role, QueryPage queryRequest);
}
