package com.hh.wx.xcx.model;

import java.util.Date;
import java.util.List;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class Insurance extends BaseModel{

	private Long carId;
	
	private String title;
	
	private Long appId;
	
	private Date effectTime;
	
	private List<InsuranceDetail> insuranceDetails;
	
}
