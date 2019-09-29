package com.hh.wx.xcx.utils.http;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
* @author hh
* @date 2019年5月21日 下午4:03:42
* @desc 类说明
*/

@Component
@Slf4j
public class HttpUtils {
	
	public static final String successCode = "200";
	
	@Autowired
    private RestTemplate restTemplate;
	
	
	public final <T> T doPost(Class<T> t,String url,Object param,
    		HttpUtilResultHander<T> hander,HttpHeaders headers){
		return doExchange(t,url,param,hander,headers,HttpMethod.POST);
	}
	
	public final <T> T doGet(Class<T> t,String url,Object param,
    		HttpUtilResultHander<T> hander,HttpHeaders headers){
		return doExchange(t,url,param,hander,headers,HttpMethod.GET);
	}
	
	public final <T> T doDelete(Class<T> t,String url,Object param,
    		HttpUtilResultHander<T> hander,HttpHeaders headers){
		return doExchange(t,url,param,hander,headers,HttpMethod.DELETE);
	}
	
	
	private <T> T doExchange(Class<T> t,String url,Object param,
    		HttpUtilResultHander<T> hander,HttpHeaders headers,HttpMethod method){
    	if(headers == null){
    		headers = new HttpHeaders();
    	}
    	
        	headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        	headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        	HttpEntity<Object>	entity = new HttpEntity<Object>(param, headers);
    	
    	T re = null;
    	
    	ResponseEntity<String> result = null;
    	
    		try {
    			if(log.isDebugEnabled()){
    				log.info("请求链接:{{}},参数为:{{}}",url,param);
    			}
        		result = restTemplate.exchange(url, method, entity, String.class);
        		if(log.isDebugEnabled()){
    				log.info("请求链接:{{}}结果为{{}}",url,result);
    			}
    		} catch (Exception e) {
    			log.error("请求链接:{{}}异常,参数为:{{}}",url,param,e);
    			throw new RuntimeException("接口异常",e);
    		}
    	if (result != null && HttpStatus.OK == result.getStatusCode()) {
    		if(hander != null){
    			re = hander.hander(result);
    		}else{
    			re = handerResult(t,result,url,param);
    		}
        }else{
        	log.error("请求链接:{{}}出错,参数为:{{}},信息为:{{}}",url,param,result);
        }
    	return re;
    }
    
    private <T> T handerResult(Class<T> t,ResponseEntity<String> result,
    		String url,Object param){
    	T re = null;
    	JSONObject body = JSONObject.parseObject(result.getBody());
		if(successCode.equals(body.getString("errcode"))){
			String data = body.getString("data");
			if(!StringUtils.isEmpty(data)){
				re = JSONObject.parseObject(data, t);
			}
		}else{
			log.error("请求链接:{{}}内部出错,参数为:{{}},出错信息为:{{}}",url,param,body.get("errmsg"));
		}
    	return re;
    }
}
