package com.hc.server.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.DataAccess;
import com.hc.common.result.PageUtils;

public interface IDataAccessService extends IService<DataAccess> {

    PageUtils findPage(DataAccess dataAccess, QueryPage queryRequest);


    public void saveDataAccess(DataAccess dataAccess);



    public void updateDataAccess(DataAccess dataAccess);
}
