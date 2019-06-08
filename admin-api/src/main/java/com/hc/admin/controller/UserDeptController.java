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

import com.hc.admin.generator.entity.UserDeptEntity;
import com.hc.admin.generator.service.UserDeptService;
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
@RequestMapping("generator/userdept")
public class UserDeptController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(UserDeptController.class);
    @Autowired
    private UserDeptService userDeptService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 UserDept列表", notes = "查询UserDept列表")
    public Object list(@Valid UserDeptDto dto){
        PageUtils page = userDeptService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 UserDept", notes = "新增UserDept")
    @BussinessLog(value = "save UserDept",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody UserDeptEntity userDept, BindingResult result){
        logger.info(JSON.toJSONString(userDept));
		userDeptService.save(userDept);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 UserDept", notes = "新增UserDept")
    @BussinessLog(value = "update UserDept" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody UserDeptEntity userDept){
        logger.info(JSON.toJSONString(UserDept));
		userDeptService.updateById(userDept);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 UserDept", notes = "删除 UserDept")
    @BussinessLog(value = "delete UserDept",key = "Id")
    public R delete(@RequestBody UserDeptDto UserDept){
        Long [] ids=UserDept.getIds();
		userDeptService.removeByIds(Arrays.asList(udIds));

        return Rets.success();
    }

}
