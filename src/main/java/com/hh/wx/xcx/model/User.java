package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class User extends BaseModel{

	private String name="";
	
	private String pwd="";
	
	private String phone="";
	
}
