//package com.hh.wx.xcx.converter;
//
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.stereotype.Component;
//
//import com.hh.wx.xcx.servicetype.ServiceType;
//
////@Component
//public class ServiceTypeConverter implements Converter<String, ServiceType> {
//
//
//	@Override
//	public ServiceType convert(String source) {
//		if (source == null) {
//			return null;
//		}
//		int typeIndex = Integer.valueOf(source);
//		ServiceType[] type = ServiceType.class.getEnumConstants();
//		if (typeIndex >= type.length) {
//			throw new RuntimeException("枚举类型参数有误,枚举类型为:" + ServiceType.class + ",source:" + source);
//		}
//		return ServiceType.class.getEnumConstants()[typeIndex];
//	}
//
//}
