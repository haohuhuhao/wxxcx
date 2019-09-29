package com.hh.wx.xcx.commons;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultVo<T> implements Serializable{

	private String msg;
	
	private Integer code;
	
	private T data;

}
