package com.hc.admin.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.hc.admin.bean.Area;
import com.hc.admin.service.AreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.admin.generator.entity.AreaEntity;
import com.hc.admin.generator.service.AreaService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:41
 */
@RestController
@RequestMapping("generator/area")
public class AreaController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    //@ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    //@ApiOperation(value = "查询 Area列表", notes = "查询Area列表")
    public Object list(@Valid Area dto){
        //PageUtils page = areaService.queryPage(dto);

        return "";
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Area", notes = "新增Area")
    @BussinessLog(value = "save Area",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody AreaEntity area, BindingResult result){
        logger.info(JSON.toJSONString(area));
		areaService.save(area);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Area", notes = "新增Area")
    @BussinessLog(value = "update Area" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody AreaEntity area){
        logger.info(JSON.toJSONString(Area));
		areaService.updateById(area);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Area", notes = "删除 Area")
    @BussinessLog(value = "delete Area",key = "Id")
    public R delete(@RequestBody AreaDto Area){
        Long [] ids=Area.getIds();
		areaService.removeByIds(Arrays.asList(areaIds));

        return Rets.success();
    }

}
