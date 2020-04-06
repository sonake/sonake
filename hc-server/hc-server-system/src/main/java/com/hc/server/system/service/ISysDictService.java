package com.hc.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysDict;
import com.hc.common.result.PageUtils;

public interface ISysDictService extends IService<SysDict> {
    PageUtils findPage(SysDict dict, QueryPage queryRequest);
}
