package com.hc.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysArea;
import com.hc.common.result.PageUtils;

public interface ISysAreaService extends IService<SysArea> {
    PageUtils findAll(SysArea area, QueryPage queryRequest);
}
