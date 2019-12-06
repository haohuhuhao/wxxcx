package com.hh.wx.xcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.User;
import com.hh.wx.xcx.service.UserService;

@RestController
@RequestMapping("user")
public class UserInfoController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	public ResultVo<String> login(@RequestBody User user){
		
		return userService.loginByPhone(user.getPhone(), user.getPwd());
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public ResultVo<String> update(@RequestBody User user,@RequestHeader(value="token") String token){
		
		return userService.update(user,token);
	}
	
	@RequestMapping(value="getLoginUserInfo",method=RequestMethod.GET)
	public ResultVo<LoginUserInfo> getLoginUserInfo(){
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		return ResultUtils.secusses(loginUserInfo);
	}
	
	@RequestMapping(value="regist",method=RequestMethod.POST)
	public ResultVo<String> regist(@RequestBody User user){
		return userService.insert(user);
	}
	
	@RequestMapping(value="chooseAppId",method=RequestMethod.GET)
	public ResultVo<String> chooseAppId(Long appId,@RequestHeader(value="token") String token){
		return userService.chooseAppId(appId, token);
	}
	
}
