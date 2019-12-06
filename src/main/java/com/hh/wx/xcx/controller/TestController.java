package com.hh.wx.xcx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.annotation.RequestModel;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppInfo;

@RestController
public class TestController {
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public ResultVo<String> test(@RequestModel AppInfo app,@RequestModel("sss")String ss){
		
		return ResultUtils.secusses();
	}
}

