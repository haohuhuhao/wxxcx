package com.hh.wx.xcx.service.impl;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hh.wx.xcx.annotation.DataAuthor;
import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppInfo;
import com.hh.wx.xcx.model.UserAppInfo;
import com.hh.wx.xcx.service.AppInfoService;
import com.hh.wx.xcx.service.mapper.AppInfoMapper;
import com.hh.wx.xcx.service.mapper.UserAppInfoMapper;

@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoMapper appInfoMapper;
	
	@Autowired
	private UserAppInfoMapper userAppInfoMapper;

	@Transactional
	@Override
	public ResultVo<String> add(AppInfo appInfo) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		appInfo.setBusinessType(loginUserInfo.getBusinessType());
		appInfo.setCreateUser(loginUserInfo.getId());
		Long appId = IdGenerator.getInstance().generateId();
		appInfo.setId(appId);
		appInfoMapper.insert(appInfo);
		
		UserAppInfo userAppInfo = new UserAppInfo();
		userAppInfo.setAppId(appId);
		userAppInfo.setBusinessType(loginUserInfo.getBusinessType());
		userAppInfo.setUserId(loginUserInfo.getId());
		userAppInfo.setAuthority(1);
		userAppInfo.setId(IdGenerator.getInstance().generateId());
		userAppInfoMapper.add(userAppInfo);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<AppInfo>> queryAll(AppInfo app) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		app.setCreateUser(loginUserInfo.getId());
		app.setBusinessType(loginUserInfo.getBusinessType());
		List<AppInfo> apps = appInfoMapper.queryAll(app);
		return ResultUtils.secusses(apps);
	}

	@DataAuthor
	@Override
	public ResultVo<AppInfo> getById(Long id) {
		return ResultUtils.secusses(getEntryById(id));
	}
	
	@Override
	public AppInfo getEntryById(Long id) {
		
		return appInfoMapper.getById(id);
	}

	@Override
	public ResultVo<String> update(AppInfo app) {
		if(app.getId() == null){
			return ResultUtils.fail("应用id不能为空");
		}
		
		appInfoMapper.update(app);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> deleteById(Long id) {
		appInfoMapper.deleteById(id);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> effect(Long id, Date effectTime) {
		appInfoMapper.effect(id, effectTime);
		return ResultUtils.secusses();
	}
	
}
