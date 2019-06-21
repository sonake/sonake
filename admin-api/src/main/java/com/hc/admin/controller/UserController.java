package com.hc.admin.controller;

import java.util.Arrays;

import com.alibaba.fastjson.JSON;
import com.hc.admin.annotion.ParamValidator;
import com.hc.admin.bean.User;
import com.hc.admin.common.BaseController;
import com.hc.admin.annotion.HcLog;
import com.hc.admin.common.PageUtils;
import com.hc.admin.common.Rets;
import com.hc.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;


/**
 * 用户信息表
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@RestController
@RequestMapping("sys/user")
@Slf4j
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequiresPermissions("sys:user:list")
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 User列表", notes = "查询User列表")
    @ParamValidator(classname = "com.hc.admin.bean.User")
    public Object list(@Valid User dto,BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            StringBuilder sb=new StringBuilder();
//            for(ObjectError error:bindingResult.getAllErrors()){
//                sb.append(error.getDefaultMessage());
//            }
//            return Rets.failure(sb.toString());
//        }
            PageUtils page = userService.queryPage(dto);
            return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 User", notes = "新增User")
    @HcLog(value = "save User",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody User user){
        log.info(JSON.toJSONString(user));
		userService.save(user);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 User", notes = "新增User")
    @HcLog(value = "update User" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody User user){
        log.info(JSON.toJSONString(user));
		userService.updateById(user);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 User", notes = "删除 User")
    @HcLog(value = "delete User",key = "Id")
    @DeleteMapping
    public Object delete(@RequestBody User user){
        Long [] ids=user.getIds();
		userService.removeByIds(Arrays.asList(ids));

        return Rets.success();
    }

}
