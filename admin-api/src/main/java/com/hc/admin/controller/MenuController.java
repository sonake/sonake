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

import com.hc.admin.generator.entity.MenuEntity;
import com.hc.admin.generator.service.MenuService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:39
 */
@RestController
@RequestMapping("generator/menu")
public class MenuController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(MenuController.class);
    @Autowired
    private MenuService menuService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Menu列表", notes = "查询Menu列表")
    public Object list(@Valid MenuDto dto){
        PageUtils page = menuService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Menu", notes = "新增Menu")
    @BussinessLog(value = "save Menu",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody MenuEntity menu, BindingResult result){
        logger.info(JSON.toJSONString(menu));
		menuService.save(menu);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Menu", notes = "新增Menu")
    @BussinessLog(value = "update Menu" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody MenuEntity menu){
        logger.info(JSON.toJSONString(Menu));
		menuService.updateById(menu);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Menu", notes = "删除 Menu")
    @BussinessLog(value = "delete Menu",key = "Id")
    public R delete(@RequestBody MenuDto Menu){
        Long [] ids=Menu.getIds();
		menuService.removeByIds(Arrays.asList(menuIds));

        return Rets.success();
    }

}
