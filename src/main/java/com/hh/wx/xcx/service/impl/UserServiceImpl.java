package com.hh.wx.xcx.service.impl;

import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.hh.wx.xcx.commons.Base64Code;
import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.StringRegexUtils;
import com.hh.wx.xcx.model.User;
import com.hh.wx.xcx.service.UserService;
import com.hh.wx.xcx.service.mapper.AppInfoMapper;
import com.hh.wx.xcx.service.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private AppInfoMapper appInfoMapper;
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public ResultVo<String> insert(User user) {
		String phone = user.getPhone().trim();
		if(StringUtils.isEmpty(phone)){
			return ResultUtils.fail("手机号不能都为空");
		}
		if(!StringRegexUtils.isMobile(phone)){
			return ResultUtils.fail("手机号格式错误");
		}
		//判断手机号和用户名唯一
		User userdb = userMapper.findOne(phone);
		if(userdb != null){
			return ResultUtils.fail("手机号已经注册");
		}
		
		if(StringUtils.isEmpty(user.getName())){
			user.setName(phone);
		}
		String pwd = user.getPwd();
		if(StringUtils.isEmpty(pwd.trim())){
			pwd = "123456";
		}
		user.setId(IdGenerator.getInstance().generateId());
		user.setPwd(DigestUtils.md5Hex(pwd));
		userMapper.insert(user);
		return ResultUtils.secusses();
	}
	
	@Override
	public ResultVo<String> loginByPhone(String phone, String pwd) {
		User user = userMapper.findOne(phone);
		if(user == null){
			return ResultUtils.fail("用户不存在");
		}
		if(user.getPwd()!=null){
			if(!DigestUtils.md5Hex(pwd).equals(user.getPwd())){
				return ResultUtils.fail("密码错误");
			}
		}
		
		LoginUserInfo userInfo = new LoginUserInfo();
		userInfo.setId(user.getId());
		userInfo.setName(user.getName());
		userInfo.setPhone(phone);
		String userInfoStr = JSONObject.toJSONString(userInfo);
		String key = Base64Code.getToken(userInfo.getId()+""+System.currentTimeMillis());
		redisTemplate.opsForValue().set(key, userInfoStr,60*60,TimeUnit.SECONDS);
		return ResultUtils.secusses(key);
	}

	@Override
	public ResultVo<String> update(User user,String token) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		user.setId(loginUserInfo.getId());
		String pwd = user.getPwd();
		if(pwd!=null){
			user.setPwd(DigestUtils.md5Hex(pwd));
		}
		
		String name = user.getName();
		if(name != null && !"".equals(name.trim())){
			loginUserInfo.setName(name);
			String userInfoStr = JSONObject.toJSONString(loginUserInfo);
			redisTemplate.opsForValue().set(token, userInfoStr,60*60,TimeUnit.SECONDS);
		}
		userMapper.update(user);
		
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> chooseAppId(Long appId,String token) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		boolean hasRight = appInfoMapper.isHasAppRight(appId,loginUserInfo.getId());
		
		if(hasRight){
			loginUserInfo.setAppId(appId);
			String userInfoStr = JSONObject.toJSONString(loginUserInfo);
			redisTemplate.opsForValue().set(token, userInfoStr,60*60,TimeUnit.SECONDS);
			return ResultUtils.secusses();
		}else{
			return ResultUtils.fail("对此应用无权限");
		}
	}
}
