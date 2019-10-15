package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:13
 * @description：用户角色信息关联
 * @version: 1.0
 */
@Data
@TableName("t_user_role")
public class SysUserRole implements Serializable {
    private static final long serialVersionUID = -989349136498454968L;

    private Long userId;

    private Long roleId;

    private String delFlag;
}
