package com.hh.wx.xcx.model;


import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class MaintainDetail extends BaseModel{

	private Long maintainInfoId;
	
	private String name;
	
	private String detail;
	
	private String remark;
	
	private String imgs;
}
