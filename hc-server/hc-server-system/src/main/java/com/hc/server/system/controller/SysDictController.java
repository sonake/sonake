package com.hc.server.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.SysDict;
import com.hc.common.bean.system.SysUser;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.server.system.service.ISysDictService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("dict")
@AllArgsConstructor
public class SysDictController {

    ISysDictService dictService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('dict:view')")
    public Object dictList(QueryPage queryRequest, SysDict dict) {
        return Rs.success(dictService.findPage(dict,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('dict:add')")
    public Object addUser(@Valid SysDict dict) throws HcException {
        try {
            this.dictService.save(dict);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增字典失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('dict:update')")
    public Object updateUser(@Valid SysDict dict) throws HcException {
        try {
            this.dictService.updateById(dict);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改字典失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('dict:delete')")
    public Object deleteUsers(@NotBlank(message = "{required}") String dictIds) throws HcException {
        try {
            List<Long> ids = Arrays.asList(dictIds.split(StringPool.COMMA)).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            this.dictService.removeByIds(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除字典失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }
}
