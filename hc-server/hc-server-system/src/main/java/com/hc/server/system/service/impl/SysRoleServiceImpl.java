package com.hc.server.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysMenuRole;
import com.hc.common.bean.system.SysRole;
import com.hc.common.result.PageUtils;
import com.hc.common.utils.CommonTools;
import com.hc.server.system.mapper.SysRoleMapper;
import com.hc.server.system.service.ISysMenuRoleService;
import com.hc.server.system.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private ISysMenuRoleService menuRoleService;

    @Override
    public PageUtils findPageDetail(SysRole role, QueryPage request) {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        IPage<SysRole> iPage = this.baseMapper.findPageDetail(CommonTools.getPage(request),role);
        return new PageUtils(iPage);
    }

    @Override
    public void saveRoleAndPerms(SysRole role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setCreateBy(1L);
        role.setUpdateBy(1L);
        this.save(role);
        Arrays.stream(role.getMenuIds()).forEach(menuId->{
            SysMenuRole sysMenuRole = new SysMenuRole();
            sysMenuRole.setRoleId(role.getId());
            sysMenuRole.setMenuId(Long.valueOf(menuId));
            menuRoleService.save(sysMenuRole);
        });
    }
}
