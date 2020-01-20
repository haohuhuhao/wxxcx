//package com.hh.wx.xcx.converter;
//
//import org.springframework.core.convert.converter.Converter;
//
//public class ServiceTypeConverterConfig<T extends Enum> implements Converter<String, T> {
//
//	private Class<T> enumType;
//
//	public ServiceTypeConverterConfig(Class<T> enumType) {
//		this.enumType = enumType;
//	}
//
//	@Override
//	public T convert(String source) {
//		if (source == null) {
//			return null;
//		}
//		int typeIndex = Integer.valueOf(source);
//		T[] type = enumType.getEnumConstants();
//		if (typeIndex >= type.length) {
//			throw new RuntimeException("枚举类型参数有误,枚举类型为:" + enumType + ",source:" + source);
//		}
//		return (T) enumType.getEnumConstants()[typeIndex];
//	}
//
//}
