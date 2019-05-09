/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.hc.gen.controller;

import com.hc.gen.service.SysGeneratorService;
import com.hc.gen.utils.PageUtils;
import com.hc.gen.utils.Query;
import com.hc.gen.utils.R;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器
 * 
 * @author Mark sunlightcs@gmail.com
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {
	private static Logger logger = LoggerFactory.getLogger(SysGeneratorController.class);
	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils pageUtil = sysGeneratorService.queryList(new Query(params));
		
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping("/code")
	public void code(String tables, HttpServletResponse response,
					 @Param("codeUrl")String codeUrl,
					 @Param("xmlUrl")String xmlUrl,
					 @Param("vueUrl")String vueUrl ) throws IOException{
		//拼装路径参数
		Map<String,String> params = new HashMap<>();
		params.put("codeUrl",codeUrl);
		params.put("xmlUrl",xmlUrl);
		params.put("vueUrl",vueUrl);
		boolean isExist = sysGeneratorService.generatorCode(tables.split(","),params);
		
		response.reset();
		if(isExist) {
			IOUtils.write("200", response.getOutputStream());
			logger.info("代码生成完毕");
		}else{
			IOUtils.write("500", response.getOutputStream());
		    logger.error("代码生成失败，请检查配置后重新操作！");}
	}
}
