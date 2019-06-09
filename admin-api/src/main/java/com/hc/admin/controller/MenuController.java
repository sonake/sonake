package com.hc.admin.controller;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.hc.admin.bean.Menu;
import com.hc.admin.common.BaseController;
import com.hc.admin.common.HcLog;
import com.hc.admin.common.PageUtils;
import com.hc.admin.common.Rets;
import com.hc.admin.service.MenuService;
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
 * @date 2019-06-08 17:36:39
 */
@RestController
@RequestMapping("sys/menu")
@Slf4j
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Menu列表", notes = "查询Menu列表")
    public Object list(@Valid Menu dto){
        PageUtils page = menuService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Menu", notes = "新增Menu")
    @HcLog(value = "save Menu",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody Menu menu, BindingResult result){
        log.info(JSON.toJSONString(menu));
		menuService.save(menu);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Menu", notes = "新增Menu")
    @HcLog(value = "update Menu" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody Menu menu){
        log.info(JSON.toJSONString(menu));
		menuService.updateById(menu);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Menu", notes = "删除 Menu")
    @HcLog(value = "delete Menu",key = "Id")
    @DeleteMapping
    public Object delete(@RequestBody Menu menu){
        Long [] ids=menu.getIds();
		menuService.removeByIds(Arrays.asList(ids));
       //todo 需要将其所属的子菜单一并删除
        return Rets.success();
    }

}
