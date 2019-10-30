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
	 * 是否已绑定（无需数据库持久化）
	 */
	private Boolean bind;
	  
	  /**
	 * 里程
	 */
	private Long carMileage;
	
	private String image;
	  
	private Date carAge;
}
