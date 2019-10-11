package com.hh.wx.xcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.service.WxUserService;
import com.hh.wx.xcx.utils.wx.WxDataHander;
import com.hh.wx.xcx.wx.model.WxSession;

@RestController
@RequestMapping("wxUser")
public class WxUserInfoController {
	
	@Autowired
	private WxDataHander wxDataHander;
	
	@Autowired
	private WxUserService wxUserService;
	
	@RequestMapping("login")
	public ResultVo<String> login(String code,@RequestHeader HttpHeaders headers){
		if(headers.get("token")!=null){
			
		}
		WxSession session = wxDataHander.getWxSession(code);
		if(session == null){
			return ResultUtils.fail("wxSession过期");
		}else{
			String openid = session.getOpenid();
			headers.getFirst("");
			//wxUserService.findByOpengId(openid);
		}
		
		return ResultUtils.secusses();
	}
}
