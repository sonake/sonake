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
 * @date 2019-06-08 17:36:41
 */
@Data
@TableName("t_area")
public class Area implements Serializable {
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
