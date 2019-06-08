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
@TableName("t_menu_role")
public class MenuRole implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long mrId;
	/**
	 * 
	 */
	private Long menuId;
	/**
	 * 
	 */
	private Long roleId;
	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	private String delFlag;

}
