package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hc.common.base.BaseBean;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_data_access")
public class DataAccess extends BaseBean implements Serializable {
    private static final long serialVersionUID = -900659084414566679L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 数据权限分组名称
     */
    private String accessName;
    /**
     * 数据权限控制主体，如部门表或者地区表
     */
    private String accessSubject;
    /**
     * 数据权限资源集，需要进行数据过滤的资源，例如商品表
     */
    private String accessResource;
    /**
     * 关联子字段，如果用户没设置,则为数据权限表的id，例如dept_id
     */
    private String accessResourceField;
    /**
     * 用于【数据权限】的条件规则
     */
    private String dataPermissionRule;
}
