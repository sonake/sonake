package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_menu_role")
public class SysMenuRole implements Serializable {
    private static final long serialVersionUID = 496267999522568404L;

    private Long roleId;

    private Long menuId;

    private String delFlag;
}
