package com.hc.admin.controller;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.hc.admin.bean.Area;
import com.hc.admin.common.BaseController;
import com.hc.admin.common.HcLog;
import com.hc.admin.common.PageUtils;
import com.hc.admin.common.Rets;
import com.hc.admin.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:41
 */
@RestController
@Slf4j
@RequestMapping("sys/area")
public class AreaController extends BaseController {
    @Autowired
    private AreaService areaService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Area列表", notes = "查询Area列表")
    public Object list(@Valid Area dto){
        PageUtils page = areaService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Area", notes = "新增Area")
    @HcLog(value = "save Area",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Area area){
        log.info(JSON.toJSONString(area));
		areaService.save(area);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Area", notes = "新增Area")
    @HcLog(value = "update Area" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Area area){
        log.info(JSON.toJSONString(area));
		areaService.updateById(area);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Area", notes = "删除 Area")
    @HcLog(value = "delete Area",key = "Id")
    @DeleteMapping
    public Object delete(@RequestBody Area area){
        Long [] ids=area.getIds();
		areaService.removeByIds(Arrays.asList(ids));

        return Rets.success();
    }

}
