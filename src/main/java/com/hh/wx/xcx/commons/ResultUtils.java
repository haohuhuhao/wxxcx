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
		
		return fail(msg,-1);
	}
	
	public static <T> ResultVo<T> fail(String msg,Integer code){
		ResultVo<T> result = new ResultVo<>();
		result.setCode(code);
		result.setMsg(msg);
		return result;
	}
	
	public static <T> ResultVo<T> failWithData(String msg,T data,Integer code){
		ResultVo<T> result = new ResultVo<>();
		result.setCode(code);
		result.setData(data);
		result.setMsg(msg);
		return result;
	}
}
