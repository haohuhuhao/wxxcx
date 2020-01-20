package com.hh.wx.xcx.commons.model;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {

	private Integer page=1;
	
	private Integer pageSize=10;
	
	private List<T> data;
	
	private long total=0;
	
	
	public Page(PageQuery<T> pageQuery,Long total,List<T> data){
		this.page = pageQuery.getPage();
		this.pageSize = pageQuery.getPageSize();
		this.total = total;
		this.data = data;
	}
	
}
