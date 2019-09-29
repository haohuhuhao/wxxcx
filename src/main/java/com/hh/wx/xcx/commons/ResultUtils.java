package com.hh.wx.xcx.commons;

public interface ResultUtils {
	
	public static <T> ResultVo<T> secusses(){
		
		return secusses(null);
	}

	public static <T> ResultVo<T> secusses(T data){
		return secusses(data,"操作成功");
	}
	
	public static <T> ResultVo<T> secusses(T data,String msg){
		ResultVo<T> result = new ResultVo<>();
		result.setCode(200);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
	
	public static <T> ResultVo<T> fail(){
		return fail("操作失败");
	}
	
	public static <T> ResultVo<T> fail(String msg){
		ResultVo<T> result = new ResultVo<>();
		result.setCode(-1);
		result.setMsg(msg);
		return result;
	}
}
