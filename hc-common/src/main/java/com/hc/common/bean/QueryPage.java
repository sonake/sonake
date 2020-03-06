package com.hc.common.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ：xzyuan
 * @date ：Created in 2019/10/15 10:02
 * @description：分页参数
 * @version: 1.0
 */
@Data
public class QueryPage implements Serializable {
    private static final long serialVersionUID = 5116732347314374829L;
    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNo = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
