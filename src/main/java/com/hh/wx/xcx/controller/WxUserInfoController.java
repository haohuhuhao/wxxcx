package com.hh.wx.xcx.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.annotation.RequestModel;
import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.WxUser;
import com.hh.wx.xcx.service.WxUserService;
import com.hh.wx.xcx.utils.wx.WxDataHander;

@RestController
@RequestMapping("wxUser")
public class WxUserInfoController {
	
	@Autowired
	private WxDataHander wxDataHander;
	
	@Autowired
	private WxUserService wxUserService;
	
	@RequestMapping("login")
	public ResultVo<Map<String,Object>> login(String code,@RequestHeader HttpHeaders headers,Long appId){
		Map<String,Object> result = new HashMap<>();
//		WxSession session = wxDataHander.getWxSession(code,appId);
//		if(session == null){
//			return ResultUtils.fail("wxSession过期");
//		}else{
			String openid = "o2FqI5Ok1jwSvYe02T_l8wNcG4I4";//session.getOpenid();
			WxUser wxUser = wxUserService.findByOpengId(openid);
			if(wxUser != null){
				String token = wxUserService.getLogin(wxUser);
				if(token == null){
					return ResultUtils.fail("登陆失败", -1);
				}else{
					result.put("need_recevie", wxUser.isRecevieMsg());
					result.put("token", token);
					return ResultUtils.secusses(result);
				}
			}else{
				result.put("openid", openid);
				return ResultUtils.failWithData("需要授权",result, -4);
			}
		//}
	}
	
	@RequestMapping("regiest")
	public ResultVo<String> regiest(@RequestModel WxUser user){
		
		user.setId(IdGenerator.getInstance().generateId());
		user.setType(0);
		wxUserService.addUser(user);
		String token = wxUserService.getLogin(user);
		if(token != null){
			return ResultUtils.secusses(token);
		}else{
			return ResultUtils.fail("新增用户登陆失败");
		}
		
	}
	
	
}
