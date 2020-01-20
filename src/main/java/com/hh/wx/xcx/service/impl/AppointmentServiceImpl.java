package com.hh.wx.xcx.service.impl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.commons.LoginInfoUtils;
import com.hh.wx.xcx.commons.LoginUserInfo;
import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.WXLoginUserInfo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.eventBus.event.AppointmentEvent;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.model.CarInfo;
import com.hh.wx.xcx.model.MaintainDetail;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.service.AppointmentService;
import com.hh.wx.xcx.service.CarInfoService;
import com.hh.wx.xcx.service.MaintainDetailService;
import com.hh.wx.xcx.service.MaintainInfoService;
import com.hh.wx.xcx.service.mapper.AppointmentMapper;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultDomainEventPublisher;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentMapper appointmentMapper;
	
	@Autowired
	private MaintainInfoService maintainInfoService;
	
	@Autowired
	private MaintainDetailService maintainDetailService;
	
	@Autowired
	private DefaultDomainEventPublisher defaultDomainEventPublisher;
	
	@Autowired
	private CarInfoService carInfoService;

	@Override
	public ResultVo<String> create(Appointment appointment) {
		WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		appointment.setAppId(loginUserInfo.getAppId());
		appointment.setUserId(loginUserInfo.getId());
		appointment.setBusinessType(loginUserInfo.getBusinessType());
		Long id = IdGenerator.getInstance().generateId();
		appointment.setId(id);
		appointment.setStatus(0);
		appointmentMapper.insert(appointment);
		
		//发布提醒任务
		//defaultDomainEventPublisher.postAsync(new AppointmentEvent(id,appointment.getType(),appointment.getTime(),loginUserInfo.getOpenid(),loginUserInfo.getAppId()));
		
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<Page<Appointment>> queryAppointmentOrder(PageQuery<Appointment> appointment, Integer userType) {
		Appointment queryData = appointment.getData();
		
		Appointment queryBody = new Appointment();
		if(userType.equals(0)){
			WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
			
			queryBody.setUserId(loginUserInfo.getId());
			queryBody.setAppId(loginUserInfo.getAppId());
			
		}else{
			LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
			queryBody.setAppId(loginUserInfo.getAppId());
			
		}
		
		queryBody.setStatus(queryData.getStatus());
		
		Long count = appointmentMapper.getCount(queryBody);
		List<Appointment> data = appointmentMapper.query(queryBody,(appointment.getPage()-1)*appointment.getPageSize(),appointment.getPageSize());
		return ResultUtils.secusses(new Page<Appointment>(appointment,count,data));
	}

	@Override
	public ResultVo<String> cancelAppointmentOrder(Long id) {
		WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		appointmentMapper.cancelAppointmentOrder(id,loginUserInfo.getId(),loginUserInfo.getAppId());
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> deleteAppointmentOrder(Long id) {
		WXLoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(WXLoginUserInfo.class);
		Appointment toDelete = appointmentMapper.findById(id);
		
		if(toDelete == null || (!loginUserInfo.getId().equals(toDelete.getUserId()) || !loginUserInfo.getAppId().equals(toDelete.getAppId()))){
			ResultUtils.fail("记录不存在");
		}
		appointmentMapper.deleteById(id,loginUserInfo.getId(),loginUserInfo.getAppId());
		return ResultUtils.secusses();
	}

	@Transactional
	@Override
	public ResultVo<String> createMaitain(Appointment appointment, MaintainInfo maintainInfo, String carNumber) {
		LoginUserInfo loginUserInfo = LoginInfoUtils.getLoginInfo(LoginUserInfo.class);
		
		CarInfo carInfo = carInfoService.getByCarEntryNum(carNumber);
		
		if(carInfo != null){
			Long maintainInfoId = IdGenerator.getInstance().generateId();
			maintainInfo.setAppId(loginUserInfo.getAppId());
			maintainInfo.setId(maintainInfoId);
			maintainInfo.setCarId(carInfo.getId());
			maintainInfoService.create(maintainInfo);
			
			List<MaintainDetail> maintainDetails = maintainInfo.getMaintainDetails();
			for(MaintainDetail maintainDetail:maintainDetails){
				maintainDetail.setMaintainInfoId(maintainInfoId);
			}
			
			maintainDetailService.bacthCreate(maintainDetails);
			
			appointment.setBelongId(maintainInfoId);
			appointment.setAppId(loginUserInfo.getAppId());
			appointment.setUserId(carInfo.getUserId());
			return create(appointment);
		}else{
			return ResultUtils.fail("车辆信息不存在");
		}
	}

}
