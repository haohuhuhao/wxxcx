package com.hh.wx.xcx.model;

import java.util.Date;
import java.util.List;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class MaintainInfo extends BaseModel{

	private Long appId;
	
	private Long carId;
	
	private Date maintainTime;
	
	/**
	 * 0 未发发布
	 */
	private Integer status;
	
	private String maintainTitle;
	
	private String maintainAddr;
	
	private String maintainer;
	
	//private List<MaintainDetail> maintainDetails;
}
