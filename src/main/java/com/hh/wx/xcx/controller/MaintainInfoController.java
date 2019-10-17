package com.hh.wx.xcx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Appointment;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.service.AppointmentService;
import com.hh.wx.xcx.service.MaintainInfoService;

/**
 * @author hh
 * 
 */
@RestController
@RequestMapping("maintainInfo")
public class MaintainInfoController {
	
	@Autowired
	private MaintainInfoService maintainInfoService;

	@RequestMapping(value="create",method=RequestMethod.POST)
	public ResultVo<String> createMaintainInfo(@RequestBody MaintainInfo maintainInfo){
		return maintainInfoService.create(maintainInfo);
	}
	
	@RequestMapping(value="queryByCarId",method=RequestMethod.GET)
	public ResultVo<List<MaintainInfo>> queryByCarId(@RequestParam(value="carId",required=true)Long carId,
			@RequestParam(value="status",required=false)Integer status){
		return maintainInfoService.queryByCarId(carId,status);
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody MaintainInfo maintainInfo){
		return maintainInfoService.update(maintainInfo);
	}
}
