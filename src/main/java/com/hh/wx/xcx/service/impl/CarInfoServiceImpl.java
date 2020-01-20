package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.model.CarInfo;
import com.hh.wx.xcx.service.CarInfoService;
import com.hh.wx.xcx.service.mapper.CarMapper;

@Service
public class CarInfoServiceImpl implements CarInfoService {

	@Autowired
	private CarMapper carMapper;
	
	@Override
	public ResultVo<String> insert(CarInfo car) {
		WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		car.setUserId(userInfo.getId());
		car.setId(IdGenerator.getInstance().generateId());
		carMapper.insert(car);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<CarInfo>> queryByLoginUser() {
		WXLoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		List<CarInfo> cars = carMapper.queryByUserId(userInfo.getId());
		return ResultUtils.secusses(cars);
	}

	@Override
	public ResultVo<CarInfo> getById(Long id) {
		CarInfo car = carMapper.getById(id);
		return ResultUtils.secusses(car);
	}

	@Override
	public ResultVo<String> deleteById(Long id) {
		carMapper.deleteById(id);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> update(CarInfo car) {
		carMapper.update(car);
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<CarInfo> getByCarNum(String num) {
		
		return ResultUtils.secusses(getByCarEntryNum(num));
	}

	@Override
	public CarInfo getByCarEntryNum(String num) {
		LoginUserInfo userInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		
		return carMapper.getByCarNum(num,userInfo.getAppId());
	}
}
