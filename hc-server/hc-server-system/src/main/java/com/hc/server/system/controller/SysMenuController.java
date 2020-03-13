package com.hc.server.system.controller;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.hc.common.bean.QueryPage;
import com.hc.common.bean.router.VueRouter;
import com.hc.common.bean.system.SysMenu;
import com.hc.common.exception.HcException;
import com.hc.common.result.Rs;
import com.hc.common.utils.ToolUtil;
import com.hc.server.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.constraints.NotBlank;


/**
 *
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-12-31 17:58:07
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class SysMenuController{
    @Autowired
    private ISysMenuService menuService;


    /**
     * 获取用户权限列表
     */
    @GetMapping("/{userId}")
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Menu列表" , notes = "查询Menu列表")
    public Object list(@NotBlank(message = "{required}") @PathVariable Long userId) {
        List<VueRouter<SysMenu>> userRouters= menuService.findPermsByUserId(userId);
        return Rs.success(userRouters);
    }


    /**
     * 列表
     */
    @GetMapping
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Menu列表" , notes = "查询Menu列表")
    public Object list(QueryPage queryPage, SysMenu menu) {
        return Rs.success(menuService.findAll(menu,queryPage));
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Menu" , notes = "新增Menu")
    @PostMapping
    public Object save(@RequestBody SysMenu entity) throws HcException {
        log.info(JSON.toJSONString(entity));
        try {
            this.menuService.createOrUpdateMenu(entity);
            return Rs.success();
        } catch (Exception e) {
            String message = "新增menu失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Menu" , notes = "新增Menu")
    @PutMapping
    public Object update(@RequestBody SysMenu entity) throws HcException{
        log.info(JSON.toJSONString(entity));
        try {
            this.menuService.createOrUpdateMenu(entity);
            return Rs.success();
        } catch (Exception e) {
            String message = "修改menu失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Menu" , notes = "删除 Menu")
    @DeleteMapping
    public Object delete(@NotBlank(message = "{required}") String userIds) throws HcException {
        try {
            String[] ids = userIds.split(StringPool.COMMA);
            this.menuService.delete(ids);
            return Rs.success();
        } catch (Exception e) {
            String message = "删除部门失败";
            log.error(message, e);
            throw new HcException(message);
        }
    }

}
