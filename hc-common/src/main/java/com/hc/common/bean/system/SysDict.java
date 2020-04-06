package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("t_dict")
@Data
public class SysDict implements Serializable {
    private static final long serialVersionUID = -466738185126936912L;
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;
    private String value;
    private String fieldName;
}
