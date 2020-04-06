package com.hc.server.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysDept;
import com.hc.common.bean.system.SysDict;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.server.system.service.ISysDeptService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("dept")
@AllArgsConstructor
public class SysDeptController {

    ISysDeptService deptService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('dept:view')")
    public Object deptList(QueryPage queryRequest, SysDept dept) {
        return Rs.success(deptService.findAll(dept,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('dept:add')")
    public Object save(@Valid SysDept dept) throws HcException {
        try {
            this.deptService.save(dept);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增部门失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('dept:update')")
    public Object update(@Valid SysDept dept) throws HcException {
        try {
            this.deptService.updateById(dept);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改部门失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('dept:delete')")
    public Object delete(@NotBlank(message = "{required}") String deptIds) throws HcException {
        try {
            List<Long> ids = Arrays.asList(deptIds.split(StringPool.COMMA)).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            this.deptService.removeByIds(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除部门失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
