package com.hc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
@Data
@TableName("t_menu")
public class Menu implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 菜单/按钮ID
	 */
	@TableId(type = IdType.AUTO)
	private Long menuId;
	/**
	 * 上级菜单ID
	 */
	private Long pId;
	/**
	 * 菜单/按钮名称
	 */
	private String menuName;
	/**
	 * 对应路由path
	 */
	private String path;
	/**
	 * 对应路由组件component
	 */
	private String component;
	/**
	 * 权限标识
	 */
	private String perms;
	/**
	 * 图标
	 */
	private String icon;
	/**
	 * 类型 0菜单 1按钮
	 */
	private String type;
	/**
	 * 排序
	 */
	private Double orderNum;
	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	private String delFlag;
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
