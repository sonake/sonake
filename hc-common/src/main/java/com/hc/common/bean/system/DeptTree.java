package com.hc.common.bean.system;


import com.hc.common.bean.Tree;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DeptTree extends Tree<SysDept> {
    private String parentName;
}