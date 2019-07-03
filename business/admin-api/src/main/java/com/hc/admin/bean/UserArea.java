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
 * @date 2019-06-08 17:36:40
 */
@Data
@TableName("t_user_area")
public class UserArea implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long uaId;
	/**
	 * 
	 */
	private Long userId;
	/**
	 * 
	 */
	private Long areaId;
	/**
	 * 删除标志（0代表存在 1代表删除）
	 */
	private String delFlag;

}
