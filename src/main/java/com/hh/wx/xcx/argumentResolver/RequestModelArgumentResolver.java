package com.hh.wx.xcx.argumentResolver;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSON;
import com.hh.wx.xcx.annotation.RequestModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RequestModelArgumentResolver implements HandlerMethodArgumentResolver{

	final ThreadLocal<String> parameterDataLocal = new ThreadLocal<>();
	final ThreadLocal<NativeWebRequest> webRequestLocal = new ThreadLocal<>();
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(RequestModel.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		NativeWebRequest requestLocal = webRequestLocal.get();
		webRequestLocal.set(webRequest);
		if(!(requestLocal == webRequest)){
			parameterDataLocal.set(getRequestBodyStr(webRequest));
		}
		
		return resolveArgument(parameter);
	}
	
	private Object resolveArgument(MethodParameter parameter){
		RequestModel model = parameter.getParameterAnnotation(RequestModel.class);
		//parameter.getGenericParameterType() 返回参数的完整类型（带泛型） 
		Type type = parameter.getGenericParameterType();
		final Object o = JSON.parseObject(parameterDataLocal.get(), type);
		return null;
	}
	
	private String getRequestBodyStr(NativeWebRequest webRequest){
		try {
			HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
			ServletInputStream dataStream = request.getInputStream();
			if(dataStream != null){
				StringBuilder stringBuilder = new StringBuilder();
				byte[] bytes = new byte[24];
				
	            for (int n; (n = dataStream.read(bytes)) != -1;)
	            {
	            	stringBuilder.append(new String(bytes, 0, n));
	            }
				return stringBuilder.toString();
			}
		} catch (IOException e) {
			log.info("解析请求参数异常",e);
			throw new RuntimeException(e);
		}
		
		
		return null;
	}

}
