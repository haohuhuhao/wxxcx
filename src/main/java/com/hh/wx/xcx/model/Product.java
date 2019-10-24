package com.hh.wx.xcx.model;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

@Data
public class Product extends BaseModel{

	  private String title;
	  
	  private Integer price;
	  
	  private Integer cutPrice;
	  
	  private String remark;
	  
	  private String image;
	  
	  private String businessType;
	  
	  private Long appId;
	  
	  private Integer status;
	  
	  private Integer type;
}
