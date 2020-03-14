package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hc.common.base.BaseBean;
import lombok.Data;

import java.io.Serializable;

@TableName("t_role")
@Data
public class SysRole extends BaseBean implements Serializable {

    private static final long serialVersionUID = 465261877785017851L;

    /**
     * 角色 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 状态
     */
    private String status="1";

    @TableField(exist = false)
    private Long[] menuIds;

    @TableField(exist = false)
    private String mIds;
    @TableField(exist = false)
    private String mName;
}
