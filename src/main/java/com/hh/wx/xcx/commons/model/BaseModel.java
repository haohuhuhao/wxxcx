package com.hh.wx.xcx.commons.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BaseModel implements Serializable{

	private Long id;
	
	private Date createTime;
	
	private Date UpdateTime;
}
