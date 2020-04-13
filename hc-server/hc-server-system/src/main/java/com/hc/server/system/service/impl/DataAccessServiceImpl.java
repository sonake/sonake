package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.DataAccess;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.CommonTools;
import com.hc.server.system.mapper.DataAccessMapper;
import com.hc.server.system.service.IDataAccessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DataAccessServiceImpl extends ServiceImpl<DataAccessMapper, DataAccess> implements IDataAccessService{


    @Override
    public PageUtils findPage(DataAccess dataAccess, QueryPage queryRequest) {
        LambdaQueryWrapper<DataAccess> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(CommonTools.isNotEmpty(dataAccess.getAccessName()),DataAccess::getAccessName,dataAccess.getAccessName())
                .orderByDesc(DataAccess::getId);
        IPage<DataAccess> page = this.page(CommonTools.getPage(queryRequest),wrapper);
        return new PageUtils(page);
    }

    @Override
    public void saveDataAccess(DataAccess dataAccess){
        this.save(dataAccess);
    }


    @Override
    public void updateDataAccess(DataAccess dataAccess){
        this.updateById(dataAccess);
    }


}
