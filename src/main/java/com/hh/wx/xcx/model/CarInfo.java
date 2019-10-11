package com.hh.wx.xcx.model;

import java.util.Date;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class CarInfo  extends BaseModel{

	  private Long userId;
	  
	  private String carNum;
	  
	  private String carName;
	  
	  private String carModel;
	  
	  /**
	 * 里程
	 */
	private Long carMileage;
	  
	private Date carAge;
}
