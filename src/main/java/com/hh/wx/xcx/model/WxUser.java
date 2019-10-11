package com.hh.wx.xcx.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	
	private String openid;
}
