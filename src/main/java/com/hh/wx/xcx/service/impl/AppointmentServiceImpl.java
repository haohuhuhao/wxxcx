package com.hh.wx.xcx.service.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.eventBus.event.AppointmentEvent;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.service.AppointmentService;
import com.hh.wx.xcx.service.mapper.AppointmentMapper;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultDomainEventPublisher;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Autowired
	private DefaultDomainEventPublisher defaultDomainEventPublisher;

	@Override
	public ResultVo<String> create(Appointment appointment) {
		WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		appointment.setAppId(loginUserInfo.getAppId());
		appointment.setUserId(loginUserInfo.getId());
		appointment.setBusinessType(loginUserInfo.getBusinessType());
		Long id = IdGenerator.getInstance().generateId();
		appointment.setId(id);
		//appointmentMapper.insert(appointment);
		
		//发布提醒任务
		defaultDomainEventPublisher.postAsync(new AppointmentEvent(id,appointment.getType(),appointment.getTime(),loginUserInfo.getOpenid(),loginUserInfo.getAppId()));
		
		return ResultUtils.secusses();
	}

}
