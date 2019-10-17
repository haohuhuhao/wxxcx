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
import com.hh.wx.xcx.model.MaintainDetail;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.service.AppointmentService;
import com.hh.wx.xcx.service.MaintainDetailService;
import com.hh.wx.xcx.service.MaintainInfoService;

/**
 * @author hh
 * 
 */
@RestController
@RequestMapping("maintainDetail")
public class MaintainDetailController {
	
	@Autowired
	private MaintainDetailService maintainDetailService;

	@RequestMapping(value="create",method=RequestMethod.POST)
	public ResultVo<String> createMaintainInfo(@RequestBody MaintainDetail maintainDetail){
		return maintainDetailService.create(maintainDetail);
	}
	
	@RequestMapping(value="queryBymaintainInfoId",method=RequestMethod.GET)
	public ResultVo<List<MaintainDetail>> queryBymaintainInfoId(@RequestParam(value="maintainInfoId",required=true)Long maintainInfoId){
		return maintainDetailService.queryBymaintainInfoId(maintainInfoId);
	}
	
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody MaintainDetail maintainDetail){
		return maintainDetailService.update(maintainDetail);
	}
}
