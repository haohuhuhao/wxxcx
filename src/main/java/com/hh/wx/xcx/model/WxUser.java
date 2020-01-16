package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class WxUser extends BaseModel{
	private String name;
	
	private Integer age;
	
	private Integer sex;
	
	private String nickName;
	
	private String addr;
	
	private String phone;
	
	private String img;
	
	/**
	 * 微信openid
	 */
	private String openid;
	
	private Integer type;
	
	/**
	 * 应用id
	 */
	private Long appId;
}
