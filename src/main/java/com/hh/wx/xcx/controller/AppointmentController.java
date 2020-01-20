package com.hh.wx.xcx.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.annotation.RequestModel;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.commons.model.Page;
import com.hh.wx.xcx.commons.model.PageQuery;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.model.MaintainInfo;
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
	
	@RequestMapping(value="createMaitain/{carNumber}",method=RequestMethod.POST)
	public ResultVo<String> createMaitain(@RequestModel Appointment appointment,@RequestModel("maintainInfo") MaintainInfo maintainInfo,@PathVariable("carNumber") String carNumber){
		return appointmentService.createMaitain(appointment,maintainInfo,carNumber);
	}
	
	@RequestMapping(value="query/{userType}",method=RequestMethod.POST)
	public ResultVo<Page<Appointment>> queryAppointmentOrder(@RequestBody PageQuery<Appointment> appointment,@PathVariable("userType") Integer userType){
		
		return appointmentService.queryAppointmentOrder(appointment,userType);
	}
	
	@RequestMapping(value="cancel/{id}",method=RequestMethod.PUT)
	public ResultVo<String> cancelAppointmentOrder(@PathVariable("id") Long id){
		
		return appointmentService.cancelAppointmentOrder(id);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> deleteAppointmentOrder(@PathVariable("id") Long id){
		
		return appointmentService.deleteAppointmentOrder(id);
	}
}
