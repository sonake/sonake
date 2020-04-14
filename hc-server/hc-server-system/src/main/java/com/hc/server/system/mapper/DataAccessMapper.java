package com.hc.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.common.bean.system.DataAccess;
import org.apache.ibatis.annotations.Param;


/**
 * @author xzyuan
 */
public interface DataAccessMapper extends BaseMapper<DataAccess> {

    void updateTable(@Param("tableName") String tableName, @Param("column") String column);
    int  selectExist(@Param("accessResource") String accessResource);

}
