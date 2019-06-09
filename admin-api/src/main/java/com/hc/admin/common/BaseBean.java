package com.hc.admin.common;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/6/9 14:08
 * @description：
 * @version:
 */
@Data
public class BaseBean {
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
    /**
     * 批量删除使用
     */
    @TableField(exist = false)
    private Long [] ids;
}
