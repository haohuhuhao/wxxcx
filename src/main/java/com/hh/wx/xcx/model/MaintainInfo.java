package com.hh.wx.xcx.model;

import java.util.Date;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class MaintainInfo extends BaseModel{

	private Long appId;
	
	private Long carId;
	
	private Date maintainTime;
	
	private Integer status;
	
	private String maintainTitle;
	
	private String maintainAddr;
	
	private String maintainer;
}
