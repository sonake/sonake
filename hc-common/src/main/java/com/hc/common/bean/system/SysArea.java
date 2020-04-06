package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hc.common.base.BaseBean;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_area")
public class SysArea extends BaseBean implements Serializable {
    private static final long serialVersionUID = 6214857125749361648L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String areaName;
    private Long parentId;
    private String areaCode;
    private Integer orderNum;
    @TableField(exist = false)
    private String parentName;
}