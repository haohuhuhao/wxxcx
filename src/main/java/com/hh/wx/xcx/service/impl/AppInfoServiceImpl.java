package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.AppInfo;
import com.hh.wx.xcx.service.AppInfoService;
import com.hh.wx.xcx.service.mapper.AppInfoMapper;

@Service
public class AppInfoServiceImpl implements AppInfoService {

	@Autowired
	private AppInfoMapper appInfoMapper;

	@Override
	public ResultVo<String> add(AppInfo appInfo) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		appInfo.setUserId(loginUserInfo.getId());
		appInfo.setBusinessType(loginUserInfo.getBusinessType());
		appInfoMapper.insert(appInfo);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<AppInfo>> queryAll(AppInfo app) {
		List<AppInfo> apps = appInfoMapper.queryAll(app);
		return ResultUtils.secusses(apps);
	}
	
}