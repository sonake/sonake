package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.Tree;
import com.hc.common.bean.system.AreaTree;
import com.hc.common.bean.system.DeptTree;
import com.hc.common.bean.system.SysArea;
import com.hc.common.bean.system.SysDept;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.ToolUtil;
import com.hc.common.utils.TreeUtil;
import com.hc.server.system.mapper.SysAreaMapper;
import com.hc.server.system.mapper.SysDeptMapper;
import com.hc.server.system.service.ISysAreaService;
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
public class SysAreaServiceImpl extends ServiceImpl<SysAreaMapper, SysArea> implements ISysAreaService {

    @Override
    public PageUtils findAll(SysArea area, QueryPage queryRequest) {
        int total=0;
        Map<String, List> result = new HashMap<>(1);
        try {
            List<SysArea> areaList = this.baseMapper.findList(area);
            List<AreaTree> areaTrees = new ArrayList<>();
            buildTrees(areaTrees, areaList);

            List<? extends Tree> deptTree = TreeUtil.build(areaTrees);
            result.put("rows", deptTree);
            total = areaList.size();
        } catch (NumberFormatException e) {
            log.error("查询地区失败", e);
        }
        return new PageUtils(result.get("rows"),total);
    }

    private void buildTrees(List<AreaTree> trees, List<SysArea> areas) {
        areas.forEach(area -> {
            AreaTree tree = new AreaTree();
            tree.setId(area.getId().toString());
            tree.setParentId(area.getParentId().toString());
            tree.setLabel(area.getAreaName());
            tree.setParentName(area.getParentName());
            tree.setAreaCode(area.getAreaCode());
            tree.setOrderNum(area.getOrderNum());
            trees.add(tree);
        });
    }

    public boolean save(SysArea area) {
        if(ToolUtil.isEmpty(area.getParentId())){
            area.setParentId(0L);
        }
        return this.retBool(this.baseMapper.insert(area));
    }

}
