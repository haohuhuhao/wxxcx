package com.hh.wx.xcx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Insurance;
import com.hh.wx.xcx.service.InsuranceService;

@RestController
@RequestMapping("insurance")
public class InsuranceController {
	
	@Autowired
	private InsuranceService insuranceService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> add(@RequestBody Insurance insurance){
		
		return insuranceService.add(insurance);
	}
	
	@RequestMapping(value="query",method=RequestMethod.POST)
	public ResultVo<List<Insurance>> query(@RequestBody Insurance insurance){
		
		return insuranceService.queryAll(insurance);
	}
	
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public ResultVo<Insurance> query(@PathVariable("id") Long id){
		
		return insuranceService.getById(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody Insurance insurance){
		
		return insuranceService.update(insurance);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> delete(@PathVariable("id") Long id){
		
		return insuranceService.deleteById(id);
	}
}

