package com.hc.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysDept;
import com.hc.common.result.PageUtils;

import java.util.List;

public interface ISysDeptService extends IService<SysDept> {
    PageUtils findAll(SysDept dept, QueryPage queryRequest);
}
