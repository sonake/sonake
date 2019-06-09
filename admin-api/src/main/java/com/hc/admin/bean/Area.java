package com.hc.admin.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.hc.admin.common.BaseBean;
import lombok.Data;

/**
 * 
 * 
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:41
 */
@Data
@TableName("t_area")
public class Area extends BaseBean implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long areaId;
	/**
	 * 
	 */
	private String areaName;
	/**
	 * 
	 */
	private Long areaPid;
	/**
	 * 区号
	 */
	private String areaCode;

}
