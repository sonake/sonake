package com.hc.admin.controller;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.hc.admin.bean.Dept;
import com.hc.admin.common.BaseController;
import com.hc.admin.annotion.HcLog;
import com.hc.admin.common.PageUtils;
import com.hc.admin.common.Rets;
import com.hc.admin.service.DeptService;
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
 * @date 2019-06-08 17:36:42
 */
@RestController
@Slf4j
@RequestMapping("sys/dept")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Dept列表", notes = "查询Dept列表")
    public Object list(@Valid Dept dept){
        PageUtils page = deptService.queryPage(dept);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Dept", notes = "新增Dept")
    @HcLog(value = "save Dept",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Dept dept){
        log.info(JSON.toJSONString(dept));
		deptService.save(dept);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Dept", notes = "新增Dept")
    @HcLog(value = "update Dept" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Dept dept){
        log.info(JSON.toJSONString(dept));
		deptService.updateById(dept);

        return Rets.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Dept", notes = "删除 Dept")
    @HcLog(value = "delete Dept",key = "Id")
    public Object delete(@RequestBody Dept dept){
        Long [] ids=dept.getIds();
		deptService.removeByIds(Arrays.asList(ids));

        return Rets.success();
    }

}
