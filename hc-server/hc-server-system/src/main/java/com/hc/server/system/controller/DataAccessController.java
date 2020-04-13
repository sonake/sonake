package com.hc.server.system.controller;


import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.system.DataAccess;
import com.hc.common.exception.HcException;
import com.hc.common.result.R;
import com.hc.common.result.Rs;
import com.hc.server.system.mapper.TableMapper;
import com.hc.server.system.service.IDataAccessService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("data/access")
@AllArgsConstructor
public class DataAccessController {

    IDataAccessService dataAccessService;
    TableMapper tableMapper;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('dataAccess:view')")
    public Object list(QueryPage queryRequest, DataAccess dataAccess) {

        return Rs.success(dataAccessService.findPage(dataAccess,queryRequest));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('dataAccess:add')")
    public Object save(@Valid DataAccess dataAccess) throws HcException {
        try {
            this.dataAccessService.saveDataAccess(dataAccess);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增数据权限规则失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @PutMapping
    @PreAuthorize("hasAnyAuthority('dataAccess:update')")
    public Object update(@Valid DataAccess dataAccess) throws HcException {
        try {
            this.dataAccessService.updateById(dataAccess);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改数据权限规则失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('dataAccess:delete')")
    public Object delete(@NotBlank(message = "{required}") String dataIds) throws HcException {
        try {
            List<Long> ids = Arrays.asList(dataIds.split(StringPool.COMMA)).stream().map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
            this.dataAccessService.removeByIds(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除数据权限规则失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    @GetMapping("/tableList")
    public Object tableList() {
        List<Map>  map =  tableMapper.listTable();
        return Rs.success(map);
    }
    @RequestMapping("/tableId")
    public Object tableId(String tableName) {
        int index = tableName.lastIndexOf("_");
        Map<String,String> columns = tableMapper.tableId(tableName);
        String unionId = tableName.substring(index+1)+"_"+columns.get("columnName");
        columns.put("columnName",unionId);
        return Rs.success(columns);
    }
}
