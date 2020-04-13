package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.Tree;
import com.hc.common.bean.system.DeptTree;
import com.hc.common.bean.system.SysDept;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.TreeUtil;
import com.hc.server.system.mapper.SysDeptMapper;
import com.hc.server.system.service.ISysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Override
    public PageUtils findAll(SysDept dept, QueryPage queryRequest) {
        int total=0;
        Map<String, List> result = new HashMap<>(1);
        try {
            List<SysDept> deptList = this.baseMapper.findList(dept);
            List<DeptTree> deptTrees = new ArrayList<>();
            buildTrees(deptTrees, deptList);

            List<? extends Tree> deptTree = TreeUtil.build(deptTrees);
            result.put("rows", deptTree);
            total = deptList.size();
        } catch (NumberFormatException e) {
            log.error("查询菜单失败", e);
        }
        return new PageUtils(result.get("rows"),total);
    }

    private void buildTrees(List<DeptTree> trees, List<SysDept> depts) {
        depts.forEach(dept -> {
            DeptTree tree = new DeptTree();
            tree.setId(dept.getId().toString());
            tree.setParentId(dept.getParentId().toString());
            tree.setLabel(dept.getDeptName());
            tree.setParentName(dept.getParentName());
            trees.add(tree);
        });
    }

}
