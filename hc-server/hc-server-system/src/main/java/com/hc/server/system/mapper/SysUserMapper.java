package com.hc.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.common.bean.system.SysUser;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 9:33
 * @description：系统用户接口
 * @version: 1.0
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 查找用户详细信息
     *
     * @param page 分页对象
     * @param user 用户对象，用于传递查询条件
     * @return Ipage
     */
    IPage<SysUser> findUserDetailPage(Page page, @Param("user") SysUser user);
}
