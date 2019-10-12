package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.model.Car2App;
import com.hh.wx.xcx.model.CarInfo;
import com.hh.wx.xcx.service.Car2AppService;
import com.hh.wx.xcx.service.CarInfoService;
import com.hh.wx.xcx.service.mapper.Car2AppMapper;
import com.hh.wx.xcx.service.mapper.CarMapper;

@Service
public class Car2AppServiceImpl implements Car2AppService {

	@Autowired
	private Car2AppMapper car2AppMapper;
	
	@Override
	public ResultVo<String> insert(Long carId) {
		Car2App car2App = new Car2App();
		WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		car2App.setCarId(carId);
		car2App.setAppId(userInfo.getAppId());
		car2AppMapper.insert(car2App);
		return ResultUtils.secusses();
	}


	@Override
	public ResultVo<String> deleteByCarId(Long carId) {
		WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		car2AppMapper.deleteByIdAndAppId(carId,userInfo.getAppId());
		return ResultUtils.secusses();
	}


	@Override
	public Car2App getByCarId(Long carId) {
		WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		
		return car2AppMapper.getByCarIdAndAppId(carId,userInfo.getAppId());
	}
}
