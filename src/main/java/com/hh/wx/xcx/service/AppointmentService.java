package com.hh.wx.xcx.service;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Appointment;

public interface AppointmentService {

	ResultVo<String> create(Appointment appointment);

}
