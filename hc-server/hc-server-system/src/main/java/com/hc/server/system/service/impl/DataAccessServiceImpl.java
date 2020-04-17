package com.hc.server.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.DataAccess;
import com.hc.common.bean.system.SysUser;
import com.hc.common.result.PageUtils;
import com.hc.common.service.RedisService;
import com.hc.common.utils.CommonTools;
import com.hc.common.utils.HcUtils;
import com.hc.server.system.mapper.DataAccessMapper;
import com.hc.server.system.service.IDataAccessService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class DataAccessServiceImpl extends ServiceImpl<DataAccessMapper, DataAccess> implements IDataAccessService{

    @Autowired
    RedisService redisService;
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
        this.baseMapper.updateTable(dataAccess.getAccessResource(),dataAccess.getAccessResourceField());
        Long userId = HcUtils.getCurrentUser().getId();
        // 存储当前用户针对某个资源控制主体开启了对某个资源的数据权限,用户退出时/token失效时删除
        redisService.set(userId+"accessSubject", JSON.toJSONString(dataAccess));
    }


    @Override
    public void updateDataAccess(DataAccess dataAccess){

        this.updateById(dataAccess);
    }


}
