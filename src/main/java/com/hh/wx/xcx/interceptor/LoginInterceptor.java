package com.hh.wx.xcx.interceptor;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
	
	private RedisTemplate<String, Object> redisTemplate;
	
	public LoginInterceptor(RedisTemplate<String, Object> redisTemplate){
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ResultVo<String> res = null;
		String token = request.getHeader("token");
		String businessType = request.getHeader("businessType");
		if(StringUtils.isEmpty(token)){
			res = ResultUtils.fail("token不能为空");
		}else{
			String UserInfoStr = (String) redisTemplate.opsForValue().get(token);
			if(StringUtils.isEmpty(UserInfoStr)){
				res = ResultUtils.fail("token已失效");
			}else{
				LoginUserInfo user = JSONObject.parseObject(UserInfoStr, LoginUserInfo.class);
				user.setBusinessType(businessType);
				redisTemplate.opsForValue().set(token, UserInfoStr,60*60,TimeUnit.SECONDS);
				LoginInfoUtils.SetLoginInfo(user);
				return true;
			}
		}
		sendResponse(request,response,res);
		return false;
	}

	public static void sendResponse(HttpServletRequest request, HttpServletResponse response,ResultVo<String> result)
			throws IOException {
		
		String origin = request.getHeader("origin");
		if (StringUtils.isEmpty(origin)) {
			response.setHeader("Access-Control-Allow-Origin", "*");
		} else {
			response.setHeader("Access-Control-Allow-Origin", origin);
		}

		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "appid,businesstype,token,access-token,share_code");
		response.setContentType("application/json");
		response.getOutputStream().write(JSONObject.toJSONString(result).getBytes("utf-8"));
	}
}
