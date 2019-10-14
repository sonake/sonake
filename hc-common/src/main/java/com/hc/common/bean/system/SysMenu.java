package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hc.common.base.BaseBean;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 11:23
 * @description：菜单
 * @version: 1.0
 */
@Data
@TableName("t_menu")
public class SysMenu extends BaseBean implements Serializable {
    private static final long serialVersionUID = 4983238114387680715L;
    // 菜单
    public static final String TYPE_MENU = "0";
    // 按钮
    public static final String TYPE_BUTTON = "1";

    /**
     * 菜单/按钮ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 上级菜单ID
     */
    private Long pId;

    /**
     * 菜单/按钮名称
     */
    private String menuName;

    /**
     * 菜单URL
     */
    private String path;

    /**
     * 对应 Vue组件
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
    private Integer orderNum;

    private transient String createTimeFrom;
    private transient String createTimeTo;
}
