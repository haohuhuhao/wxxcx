package com.hh.wx.xcx.utils.wx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hh.wx.xcx.utils.http.HttpUtils;
import com.hh.wx.xcx.wx.model.WxSession;

@Component
public class WxDataHander {
	
	@Autowired
	private HttpUtils httpUtils;
	
	@Value("${wx.session-url}")
	private String SESSION_URL;
	
	@Value("${wx.appid}")
	private String appid;
	
	@Value("${wx.secret}")
	private String secret;
	
	public WxSession getWxSession(String code){
		String url = SESSION_URL+"?appid="+appid+"&secret="+secret + "&js_code="+code+"&grant_type=authorization_code";
		WxSession session = httpUtils.doGet(WxSession.class, url, null, (result)->{
				String body = result.getBody();
				if(body.indexOf("openid")!=-1){
					return JSONObject.parseObject(body, WxSession.class);
				}
				return null;
		}, null);
		return session;
	}

}
