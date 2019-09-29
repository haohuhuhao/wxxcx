package com.hh.wx.xcx.commons;

public class LoginInfoUtils {

	private static ThreadLocal<Object> localThread = new ThreadLocal<>();
	public static <T> T getLoginInfo(Class<T> type){
		T t = (T) localThread.get();
		return t;
	}
	
	public static void SetLoginInfo(Object t){
		localThread.set(t);
	}
}
