package com.hh.wx.xcx.service;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.model.MaintainInfo;

public interface AppointmentService {

	ResultVo<String> create(Appointment appointment);

	ResultVo<Page<Appointment>> queryAppointmentOrder(PageQuery<Appointment> appointment, Integer userType);

	ResultVo<String> cancelAppointmentOrder(Long id);

	ResultVo<String> deleteAppointmentOrder(Long id);

	/**
	 * @param appointment
	 * @return
	 * @desc 商家主动创建的预约
	 */
	ResultVo<String> createMaitain(Appointment appointment, MaintainInfo maintainInfo, String carNumber);

}
