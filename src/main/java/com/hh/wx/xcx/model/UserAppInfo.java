package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class UserAppInfo extends BaseModel{

	private Long userId;
	
	private Long appId;
	
	private Integer authority;
	
	private String businessType;
	
}
