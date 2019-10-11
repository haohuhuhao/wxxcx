package com.hh.wx.xcx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.service.AppointmentService;

/**
 * @author hh
 * 
 */
@RestController
@RequestMapping("appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(value="create",method=RequestMethod.POST)
	public ResultVo<String> createAppointmentOrder(@RequestBody Appointment appointment){
		return appointmentService.create(appointment);
	}
}
