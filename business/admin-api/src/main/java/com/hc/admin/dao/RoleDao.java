package com.hc.admin.dao;

import com.hc.admin.bean.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:44
 */
public interface RoleDao extends BaseMapper<Role> {
    List<Role> findUserRole(@Param("userName") String userName);
	
}
