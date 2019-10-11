package com.hh.wx.xcx.model;

import java.util.Date;

import com.hh.wx.xcx.commons.model.BaseModel;

import lombok.Data;

/**
 * @author hh
 * 预约实体类
 *
 */
@Data
public class Appointment extends BaseModel{

	private Long userId;
	
	private Date time;
	
	private String phone;
	
	private Long appId;
	
	private Integer status;
	
	private String openid;
	
	private Integer type;
	
	private Long belongId;
}
