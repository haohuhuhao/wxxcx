package com.hh.wx.xcx.model;

import java.util.Date;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class AppInfo extends BaseModel{

	private String name;
	
	private String remark;
	
	private Long userId;
	
	private Date effectTime;
	
	private Integer status;
	
	private String businessType;
	
	private String appid;
	
	private String secret;
	
}
