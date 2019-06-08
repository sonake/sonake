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

import com.hc.admin.generator.entity.UserEntity;
import com.hc.admin.generator.service.UserService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 用户信息表
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@RestController
@RequestMapping("generator/user")
public class UserController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 User列表", notes = "查询User列表")
    public Object list(@Valid UserDto dto){
        PageUtils page = userService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 User", notes = "新增User")
    @BussinessLog(value = "save User",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody UserEntity user, BindingResult result){
        logger.info(JSON.toJSONString(user));
		userService.save(user);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 User", notes = "新增User")
    @BussinessLog(value = "update User" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody UserEntity user){
        logger.info(JSON.toJSONString(User));
		userService.updateById(user);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 User", notes = "删除 User")
    @BussinessLog(value = "delete User",key = "Id")
    public R delete(@RequestBody UserDto User){
        Long [] ids=User.getIds();
		userService.removeByIds(Arrays.asList(userIds));

        return Rets.success();
    }

}
