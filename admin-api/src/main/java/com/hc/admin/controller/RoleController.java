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

import com.hc.admin.generator.entity.RoleEntity;
import com.hc.admin.generator.service.RoleService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:44
 */
@RestController
@RequestMapping("generator/role")
public class RoleController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Role列表", notes = "查询Role列表")
    public Object list(@Valid RoleDto dto){
        PageUtils page = roleService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Role", notes = "新增Role")
    @BussinessLog(value = "save Role",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody RoleEntity role, BindingResult result){
        logger.info(JSON.toJSONString(role));
		roleService.save(role);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Role", notes = "新增Role")
    @BussinessLog(value = "update Role" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody RoleEntity role){
        logger.info(JSON.toJSONString(Role));
		roleService.updateById(role);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Role", notes = "删除 Role")
    @BussinessLog(value = "delete Role",key = "Id")
    public R delete(@RequestBody RoleDto Role){
        Long [] ids=Role.getIds();
		roleService.removeByIds(Arrays.asList(roleIds));

        return Rets.success();
    }

}
