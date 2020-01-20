package com.hh.wx.xcx.utils.wx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	private static final String SESSION_URL = "https://api.weixin.qq.com/sns/jscode2session";
	
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	private static final String SEND_WX_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	
	private static final String SEND_WX_TEMPLATE_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";
	
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

	/**
	 * @param code
	 * @param appId
	 * @return
	 * @desc 获取微信access_token
	 */
	public String getAccessToken(Long appId){
		String token = redisTemplate.opsForValue().get("access_token_"+appId);
		if(token == null){
			AppInfo appInfo = appInfoService.getEntryById(appId);
			if(appInfo != null){
				String url = String.format(TOKEN_URL, appInfo.getAppid(),appInfo.getSecret());
				
				JSONObject session = httpUtils.doGet(JSONObject.class, url, null, (result)->{
						String body = result.getBody();
						if(body.indexOf("access_token")!=-1){
							return JSONObject.parseObject(body);
						}
						return null;
				}, null);
				
				if(session != null){
					token = session.getString("access_token");
					redisTemplate.opsForValue().set("access_token_"+appId,token ,session.getInteger("expires_in")-60,TimeUnit.SECONDS);
				}
			}
		}
		
		return token;
	}

	/**
	 * 发送客服消息
	 */
	public void sendkefumsg(Long appId,String openid) {
		Map<String,Object> param = new HashMap<>();
		param.put("touser", openid);
		param.put("msgtype", "text");
	
		Map<String,String> content = new HashMap<>();
		content.put("content", "rrr");
		param.put("text", content);
		httpUtils.doPost(JSONObject.class, String.format(SEND_WX_MESSAGE, getAccessToken(appId)), param, null, null);
		
	}
	
	/**
	 * 发送模板消息
	 */
	public void sendmbmsg(Long appId,String openid) {
		Map<String,Object> param = new HashMap<>();
		param.put("touser", openid);
		param.put("msgtype", "text");
		
		param.put("template_id", "LI0BfcCuOgO0Tn24hLmSIrLDrIgsLzvNJ2wvdP-4idQ");
		param.put("page", "index");
		
		
	
		Map<String,Object> content = new HashMap<>();
		content.put("car_number1", new HashMap<String,String>(){{
			put("value", "川AHU266");
		}});
		content.put("thing2",  new HashMap<String,String>(){{
			put("value", "需要保养");
		}});
		content.put("thing3", new HashMap<String,String>(){{
			put("value", "捷达");
		}});
		
		param.put("data", content);
		httpUtils.doPost(JSONObject.class, String.format(SEND_WX_TEMPLATE_MESSAGE, getAccessToken(appId)), param, null, null);
		
	}
}
