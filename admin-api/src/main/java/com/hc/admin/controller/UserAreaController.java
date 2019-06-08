package com.hc.admin.controller;

import java.util.Arrays;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hc.admin.generator.entity.UserAreaEntity;
import com.hc.admin.generator.service.UserAreaService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:40
 */
@RestController
@RequestMapping("generator/userarea")
public class UserAreaController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(UserAreaController.class);
    @Autowired
    private UserAreaService userAreaService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 UserArea列表", notes = "查询UserArea列表")
    public Object list(@Valid UserAreaDto dto){
        PageUtils page = userAreaService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 UserArea", notes = "新增UserArea")
    @BussinessLog(value = "save UserArea",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody UserAreaEntity userArea, BindingResult result){
        logger.info(JSON.toJSONString(userArea));
		userAreaService.save(userArea);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 UserArea", notes = "新增UserArea")
    @BussinessLog(value = "update UserArea" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody UserAreaEntity userArea){
        logger.info(JSON.toJSONString(UserArea));
		userAreaService.updateById(userArea);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 UserArea", notes = "删除 UserArea")
    @BussinessLog(value = "delete UserArea",key = "Id")
    public R delete(@RequestBody UserAreaDto UserArea){
        Long [] ids=UserArea.getIds();
		userAreaService.removeByIds(Arrays.asList(uaIds));

        return Rets.success();
    }

}
