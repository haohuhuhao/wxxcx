package com.hh.wx.xcx.commons;

import lombok.Data;

@Data
public class WXLoginUserInfo extends LoginUserInfo{

	private String openid;
	
	private Long appId;
	
	private String wxSession;
	
}
