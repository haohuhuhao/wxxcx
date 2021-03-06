package com.hh.wx.xcx.model;

import java.util.Date;

import com.hh.wx.xcx.commons.model.BaseModel;
import com.hh.wx.xcx.servicetype.ServiceType;

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
	
	private String contact;
	
	private Long appId;
	
	private String businessType;
	
	private Integer status;
	
	private Long belongId;
	
	private Integer type;
}
