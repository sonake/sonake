package com.hc.admin.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:43
 */
@RestController
@RequestMapping("generator/userrole")
public class UserRoleController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(UserRoleController.class);
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 UserRole列表", notes = "查询UserRole列表")
    public Object list(@Valid UserRoleDto dto){
        PageUtils page = userRoleService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 UserRole", notes = "新增UserRole")
    @BussinessLog(value = "save UserRole",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody UserRoleEntity userRole, BindingResult result){
        logger.info(JSON.toJSONString(userRole));
		userRoleService.save(userRole);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 UserRole", notes = "新增UserRole")
    @BussinessLog(value = "update UserRole" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody UserRoleEntity userRole){
        logger.info(JSON.toJSONString(UserRole));
		userRoleService.updateById(userRole);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 UserRole", notes = "删除 UserRole")
    @BussinessLog(value = "delete UserRole",key = "Id")
    public R delete(@RequestBody UserRoleDto UserRole){
        Long [] ids=UserRole.getIds();
		userRoleService.removeByIds(Arrays.asList(urIds));

        return Rets.success();
    }

}
