package com.hc.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysRole;
import com.hc.common.bean.system.SysUser;
import com.hc.common.result.PageUtils;

public interface ISysRoleService extends IService<SysRole> {
    PageUtils findPageDetail(SysRole role, QueryPage queryRequest);

    void saveRoleAndPerms(SysRole role);

}
