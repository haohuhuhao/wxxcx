package com.hh.wx.xcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.utils.wx.WxDataHander;
import com.hh.wx.xcx.wx.model.WxSession;

@RestController
@RequestMapping("wxUser")
public class WxUserInfoController {
	
	@Autowired
	private WxDataHander wxDataHander;
	
	@RequestMapping("code2Session")
	public ResultVo<String> code2Session(String code){
		WxSession se = wxDataHander.getWxSession(code);
		ResultVo<String> re = ResultUtils.secusses();
		return re;
	}
}
