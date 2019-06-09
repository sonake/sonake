package com.hc.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.hc.admin.bean.Role;
import com.hc.admin.common.*;
import com.hc.admin.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;

/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:44
 */
@RestController
@RequestMapping("sys/role")
@Slf4j
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Role列表", notes = "查询Role列表")
    public Object list(@Valid Role dto){
        PageUtils page = roleService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Role", notes = "新增Role")
    @HcLog(value = "save Role",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Role role){
        log.info(JSON.toJSONString(role));
		roleService.save(role);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Role", notes = "新增Role")
    @HcLog(value = "update Role" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Role role){
        log.info(JSON.toJSONString(role));
		roleService.updateById(role);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Role", notes = "删除 Role")
    @HcLog(value = "delete Role",key = "Id")
    @DeleteMapping
    public Object delete(@RequestBody Role role){
        Long [] ids=role.getIds();
		roleService.removeByIds(Arrays.asList(ids));

        return Rets.success();
    }

}
