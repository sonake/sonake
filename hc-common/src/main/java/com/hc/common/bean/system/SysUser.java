package com.hc.common.bean.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hc.common.annotation.validate.IsMobile;
import com.hc.common.base.BaseBean;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 10:30
 * @description：用户信息表
 * @version: 1.0
 */
@Data
@TableName("t_user")
public class SysUser extends BaseBean implements Serializable {
    private static final long serialVersionUID = -2450869099255904262L;
    // 用户状态：有效
    public static final String STATUS_VALID = "1";
    // 用户状态：锁定
    public static final String STATUS_LOCK = "0";
    // 默认头像
    public static final String DEFAULT_AVATAR = "default.jpg";
    // 默认密码
    public static final String DEFAULT_PASSWORD = "1234qwer";
    // 性别男
    public static final String SEX_MALE = "0";
    // 性别女
    public static final String SEX_FEMALE = "1";
    // 性别保密
    public static final String SEX_UNKNOW = "2";

    /**
     * 用户 ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @Size(min = 4, max = 10, message = "{range}")
    private String username;
    /**
     * 姓名
     */
    private String nickname;
    /**
     * 密码
     */
    private String password;

    /**
     * 部门 ID
     */
    private Long deptId;
    /**
     * 地区id
     */
    private Long areaId;
    /**
     * 邮箱
     */
    @Size(max = 50, message = "{noMoreThan}")
    @Email(message = "{email}")
    private String email;

    /**
     * 联系电话
     */
    @IsMobile(message = "{mobile}")
    private String phone;

    /**
     * 状态 0锁定 1有效
     */
    @NotBlank(message = "{required}")
    private String status;


    /**
     * 最后登录ip
      */
    private String loginIp;
    /**
     * 最近访问时间
     */
    private Date lastLoginTime;

    /**
     * 性别 0男 1女 2 保密
     */
    @NotBlank(message = "{required}")
    private String sex;

    /**
     * 头像
     */
    private String avatar;



    /**
     * 部门名称
     */
    @TableField(exist = false)
    private String deptName;

    @TableField(exist = false)
    private String createTimeFrom;
    @TableField(exist = false)
    private String createTimeTo;
    /**
     * 角色 ID
     */
    @TableField(exist = false)
    private String roleId;

    @TableField(exist = false)
    private String roleName;

}
