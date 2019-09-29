package com.hh.wx.xcx.utils.http;

import org.springframework.http.ResponseEntity;

/**
* @author hh
* @date 2018年8月16日 下午1:43:10
* @desc 类说明
*/
public interface HttpUtilResultHander<T> {

	/**
	 * @author hh
	 * @date 2018年8月16日 下午1:55:21
	 * @param result
	 * @desc 自定义请求结果处理方式
	 * @return
	 */
	public T hander(ResponseEntity<String> result);
}
