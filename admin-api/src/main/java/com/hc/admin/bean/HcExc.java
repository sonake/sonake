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
 * @date 2019-06-20 14:49:40
 */
@Data
@TableName("t_hc_exc")
public class HcExc implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = IdType.AUTO)
	private Long exId;
	/**
	 * 异常码
	 */
	private String code;
	/**
	 * 异常信息
	 */
	private String msg;

	private String classname;
	/**
	 * 对应字段
	 */
	private String fieldname;
	/**
	 * 异常描述
	 */
	private String excDesc;

}
