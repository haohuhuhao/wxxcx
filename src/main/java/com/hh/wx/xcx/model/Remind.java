package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class Remind extends BaseModel{

	private Long directId;
	
	private String title;
	
	private String content;
	
	private Long appId;
	
	private String businessType;
	
	private Integer property;
	
	private Integer type;
	
	private Integer status;
	
	private Long receiver;
	
}
