package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class InsuranceDetail extends BaseModel{

	private String title;
	
	private String remark;
	
	private Long insuranceId;
}
