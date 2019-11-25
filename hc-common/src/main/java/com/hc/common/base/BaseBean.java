package com.hc.common.base;

import lombok.Data;

import java.util.Date;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/14 11:14
 * @description：基础实体类
 * @version: 1.0
 */
@Data
public class BaseBean {
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     *备注
     */
    private String remark;
    /**
     * 删除标志0正常1删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;
}