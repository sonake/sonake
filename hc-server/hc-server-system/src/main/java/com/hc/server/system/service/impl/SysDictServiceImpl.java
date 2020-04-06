package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysDict;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.ToolUtil;
import com.hc.server.system.mapper.SysDictMapper;
import com.hc.server.system.service.ISysDictService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements ISysDictService {
    @Override
    public PageUtils findPage(SysDict dict, QueryPage queryRequest) {
        LambdaQueryWrapper<SysDict> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ToolUtil.isNotEmpty(dict.getFieldName()),SysDict::getFieldName,dict.getFieldName())
                .orderByDesc(SysDict::getId);
        IPage<SysDict> page = this.page(ToolUtil.getPage(queryRequest),wrapper);
        return new PageUtils(page);
    }
}
