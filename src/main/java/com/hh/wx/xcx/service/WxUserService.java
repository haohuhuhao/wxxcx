package com.hh.wx.xcx.service;

import com.hh.wx.xcx.model.WxUser;

public interface WxUserService {

	WxUser findByOpengId(String openid);
	
	String getLogin(String openid);

}
