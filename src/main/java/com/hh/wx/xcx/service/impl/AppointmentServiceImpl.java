package com.hh.wx.xcx.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.service.AppointmentService;
import com.hh.wx.xcx.service.mapper.AppointmentMapper;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentMapper appointmentMapper;

	@Override
	public ResultVo<String> create(Appointment appointment) {
		WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		appointment.setAppId(loginUserInfo.getAppId());
		appointment.setOpenid(loginUserInfo.getOpenid());
		appointment.setPhone(loginUserInfo.getPhone());
		appointment.setUserId(loginUserInfo.getId());
		appointmentMapper.insert(appointment);
		return ResultUtils.secusses();
	}

}
