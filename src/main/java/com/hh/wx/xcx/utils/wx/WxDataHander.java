package com.hh.wx.xcx.utils.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hh.wx.xcx.model.AppInfo;
import com.hh.wx.xcx.service.AppInfoService;
import com.hh.wx.xcx.utils.http.HttpUtils;
import com.hh.wx.xcx.wx.model.WxSession;

@Component
public class WxDataHander {
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Autowired
	private AppInfoService appInfoService;
	
	@Value("${wx.session-url}")
	private String SESSION_URL;
	
	public WxSession getWxSession(String code,Long appId){
		AppInfo appInfo = appInfoService.getEntryById(appId);
		if(appInfo != null){
			String url = SESSION_URL+"?appid="+appInfo.getAppid()+"&secret="+appInfo.getSecret() + "&js_code="+code+"&grant_type=authorization_code";
			
			WxSession session = httpUtils.doGet(WxSession.class, url, null, (result)->{
					String body = result.getBody();
					if(body.indexOf("openid")!=-1){
						return JSONObject.parseObject(body, WxSession.class);
					}
					return null;
			}, null);
			return session;
		}
		
		return null;
	}

}
