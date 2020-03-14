package com.hc.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.common.bean.system.SysRole;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Select;

public interface SysRoleMapper extends BaseMapper<SysRole> {

    IPage<SysRole> findPageDetail(Page page, @Param("role") SysRole role);
}
