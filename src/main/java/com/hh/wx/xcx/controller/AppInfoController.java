package com.hh.wx.xcx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
