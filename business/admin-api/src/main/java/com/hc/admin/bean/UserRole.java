package com.hc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:43
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long urId;
	/**
	 * 角色Id
	 */
	private Long roleId;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 
	 */
	private String delFlag;

}
