package com.hc.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hc.common.bean.system.SysArea;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysAreaMapper extends BaseMapper<SysArea> {

    List<SysArea> findList(@Param("area") SysArea area);
}
