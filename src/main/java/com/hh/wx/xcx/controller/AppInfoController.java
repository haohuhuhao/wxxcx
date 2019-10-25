package com.hh.wx.xcx.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppInfo;
import com.hh.wx.xcx.service.AppInfoService;

@RestController
@RequestMapping("app")
public class AppInfoController {
	
	@Autowired
	private AppInfoService appInfoService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> add(@RequestBody AppInfo app){
		
		return appInfoService.add(app);
	}
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public ResultVo<List<AppInfo>> query(@RequestBody AppInfo app){
		
		return appInfoService.queryAll(app);
	}
	
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public ResultVo<AppInfo> query(@PathVariable("id") Long id){
		
		return appInfoService.getById(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody AppInfo app){
		
		return appInfoService.update(app);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> delete(@PathVariable("id") Long id){
		
		return appInfoService.deleteById(id);
	}
	
	
	@RequestMapping(value="effect/{id}",method=RequestMethod.PUT)
	public ResultVo<String> effect(@PathVariable("id") Long id,
			@RequestParam(value="effectTime" ,required=true) 
			@DateTimeFormat(pattern="yyyy-MM-dd")Date effectTime){
		
		return appInfoService.effect(id,effectTime);
	}
}

