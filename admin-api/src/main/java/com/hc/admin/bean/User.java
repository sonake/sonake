package com.hc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户信息表
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@Data
@TableName("t_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 用户ID
	 */
	@TableId(type = IdType.AUTO)
	private Long userId;
	/**
	 * 部门ID
	 */
	private Long deptId;
	/**
	 * 
	 */
	private Long areaId;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 用户邮箱
	 */
	private String email;
	/**
	 * 手机号码
	 */
	private String phonenumber;
	/**
	 * 用户性别（0男 1女 2未知）
	 */
	private String sex;
	/**
	 * 头像路径
	 */
	private String avatar;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 帐号状态（0正常 1停用）
	 */
	private String status;
	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	private String delFlag;
	/**
	 * 最后登陆IP
	 */
	private String loginIp;
	/**
	 * 最后登陆时间
	 */
	private Date loginDate;
	/**
	 * 创建者
	 */
	private String createBy;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新者
	 */
	private String updateBy;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 备注
	 */
	private String remark;

}
