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

import com.hc.admin.generator.entity.DeptEntity;
import com.hc.admin.generator.service.DeptService;
import cn.nis.ntc.api.controller.BaseController;
import com.common.utils.PageUtils;

import javax.validation.Valid;
import cn.nis.ntc.bean.vo.front.Rets;


/**
 * 
 *
 * @author xzyuan
 * @email yxzbby@aliyun.com
 * @date 2019-06-08 17:36:42
 */
@RestController
@RequestMapping("generator/dept")
public class DeptController extends BaseController{
    private Logger logger = LoggerFactory.getLogger(DeptController.class);
    @Autowired
    private DeptService deptService;

    /**
     * 列表
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiResponses({@ApiResponse(code = 200, message = "请求成功")})
    @ApiOperation(value = "查询 Dept列表", notes = "查询Dept列表")
    public Object list(@Valid DeptDto dto){
        PageUtils page = deptService.queryPage(dto);

        return Rets.success(page);
    }

    /**
     * 保存
     */
    @ApiResponses({@ApiResponse(code = 200, message = "新增成功")})
    @ApiOperation(value = "新增 Dept", notes = "新增Dept")
    @BussinessLog(value = "save Dept",key = "Id")
    @RequestMapping(method = RequestMethod.POST)
    public Object save(@RequestBody DeptEntity dept, BindingResult result){
        logger.info(JSON.toJSONString(dept));
		deptService.save(dept);

        return Rets.success();
    }

    /**
     * 修改
     */
    @ApiResponses({@ApiResponse(code = 200, message = "修改成功")})
    @ApiOperation(value = "修改 Dept", notes = "新增Dept")
    @BussinessLog(value = "update Dept" ,key = "Id")
    @RequestMapping(method = RequestMethod.PUT)
    public Object update(@RequestBody DeptEntity dept){
        logger.info(JSON.toJSONString(Dept));
		deptService.updateById(dept);

        return Rets.success();
    }

    /**
     * 删除
     */
    @ApiResponses({@ApiResponse(code = 200, message = "删除成功")})
    @ApiOperation(value = "删除 Dept", notes = "删除 Dept")
    @BussinessLog(value = "delete Dept",key = "Id")
    public R delete(@RequestBody DeptDto Dept){
        Long [] ids=Dept.getIds();
		deptService.removeByIds(Arrays.asList(deptIds));

        return Rets.success();
    }

}
