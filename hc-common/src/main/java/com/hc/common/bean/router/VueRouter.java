package com.hc.common.bean.router;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 构建 Vue路由
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VueRouter<T> implements Serializable {

    private static final long serialVersionUID = -3327478146308500708L;

    private String id;
    @JsonIgnore
    private String parentId;

    private String path;
    private String icon;
    private String name;
    private String component;
    private RouterMeta meta;
    private String redirect;
    private Boolean hidden = false;
    private Boolean alwaysShow = false;
    private List<VueRouter<T>> children;

    @JsonIgnore
    private Boolean hasParent = false;

    @JsonIgnore
    private Boolean hasChildren = false;

    public void initChildren(){
        this.children = new ArrayList<>();
    }

}