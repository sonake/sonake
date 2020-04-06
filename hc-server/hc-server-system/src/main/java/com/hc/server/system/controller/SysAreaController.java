package com.hc.server.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysArea;
import com.hc.common.bean.system.SysDept;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.server.system.service.ISysAreaService;
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
@RequestMapping("area")
@AllArgsConstructor
public class SysAreaController {

    ISysAreaService areaService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('area:view')")
    public Object list(QueryPage queryRequest, SysArea area) {
        return Rs.success(areaService.findAll(area,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('area:add')")
    public Object save(@Valid SysArea area) throws HcException {
        try {
            this.areaService.save(area);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增地区失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('area:update')")
    public Object update(@Valid SysArea area) throws HcException {
        try {
            this.areaService.updateById(area);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改地区失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('area:delete')")
    public Object delete(@NotBlank(message = "{required}") String areaIds) throws HcException {
        try {
            List<Long> ids = Arrays.asList(areaIds.split(StringPool.COMMA)).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            this.areaService.removeByIds(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除部门失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
