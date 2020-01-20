package com.hh.wx.xcx.commons.model;


import lombok.Data;

@Data
public class PageQuery<T> {

	private Integer page=1;
	
	private Integer pageSize=10;
	
	private T data;
	
	private long total=0;
}
