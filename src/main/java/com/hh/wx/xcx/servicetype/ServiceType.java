package com.hh.wx.xcx.servicetype;

public enum ServiceType {

	RESCUE(0,"救援","");
	private Integer code;
	
	private String describe;
	
	private String templateId;
	
	private ServiceType(Integer code,String describe,String templateId) {
		
	}

	public Integer getCode() {
		return code;
	}

	public String getDescribe() {
		return describe;
	}

	public String getTemplateId() {
		return templateId;
	}
}
