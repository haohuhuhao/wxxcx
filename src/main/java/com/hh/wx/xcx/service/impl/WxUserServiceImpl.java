package com.hh.wx.xcx.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hh.wx.xcx.commons.Base64Code;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.model.WxUser;
import com.hh.wx.xcx.service.WxUserService;
import com.hh.wx.xcx.service.mapper.WxUserMapper;

@Service
public class WxUserServiceImpl implements WxUserService {

	@Autowired
	private WxUserMapper wxUserMapper;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public WxUser findByOpengId(String openid) {
		WxUser wxUser = wxUserMapper.findByOpengId(openid);
		return wxUser;
	}
	
	private String addUserToSession(WxUser wxUser){
		WXLoginUserInfo wxUserInfo = new WXLoginUserInfo();
		wxUserInfo.setId(wxUser.getId());
		wxUserInfo.setName(wxUser.getName());
		wxUserInfo.setPhone(wxUser.getPhone());
		wxUserInfo.setOpenid(wxUser.getOpenid());
		String userInfoStr = JSONObject.toJSONString(wxUserInfo);
		String key = Base64Code.getToken(wxUserInfo.getId()+""+System.currentTimeMillis());
		redisTemplate.opsForValue().set(key, userInfoStr,60*60,TimeUnit.SECONDS);
		return key;
	}

	@Override
	public String getLogin(WxUser wxUser) {
		
		if(wxUser != null){
			return addUserToSession(wxUser);
		}
		return null;
	}

	@Override
	public void addUser(WxUser user) {
		wxUserMapper.addUser(user);
		
	}

}
