package com.hh.wx.xcx.argumentResolver;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.alibaba.fastjson.JSONObject;
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
		String requestBodyStr = parameterDataLocal.get();
		RequestModel model = parameter.getParameterAnnotation(RequestModel.class);
		if(StringUtils.isEmpty(requestBodyStr)){
			return null;
		}
		String paramKey = model.value();
		if(StringUtils.isNotEmpty(paramKey)){
			List<String> keys = Arrays.asList(paramKey.split("\\."));
			for(String key:keys){
				requestBodyStr = JSONObject.parseObject(requestBodyStr).getString(key);
			}
		}
		
		//Type type = parameter.getGenericParameterType();
		
		Class<?> clazz = parameter.getParameterType();
		
		if(StringUtils.isEmpty(requestBodyStr)){
			if(model.required()){
				throw new RuntimeException("比需参数："+paramKey+",缺失");
			}
			
			if(clazz.isPrimitive()){
				return toBeBaseType(clazz,requestBodyStr);
			}
			return null;
		}
		
		Object result = toBeBaseType(clazz,requestBodyStr);
		if(result != null){
			return result;
		}
		return JSONObject.parseObject(requestBodyStr, clazz);
	}
	
	private Object  toBeBaseType(Class<?> clazz,String requestBodyStr){
		if(clazz.equals(String.class)){
			return requestBodyStr;
		}
		
		if(clazz.equals(Integer.class)){
			return Integer.valueOf(requestBodyStr);
		}
		
		if(clazz.equals(Long.class)){
			return Long.valueOf(requestBodyStr);
		}
		
		if(clazz.equals(Boolean.class)){
			return Boolean.valueOf(requestBodyStr);
		}
		
		if(clazz.isPrimitive()){
			if(clazz.equals(int.class)){
				if(StringUtils.isEmpty(requestBodyStr)){
					return 0;
				}
				return Integer.valueOf(requestBodyStr);
			}
			
			if(clazz.equals(long.class)){
				if(StringUtils.isEmpty(requestBodyStr)){
					return 0l;
				}
				return Long.valueOf(requestBodyStr);
			}
			
			if(clazz.equals(boolean.class)){
				if(StringUtils.isEmpty(requestBodyStr)){
					return false;
				}
				return Integer.valueOf(requestBodyStr);
			}
			
		}
		
		
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
