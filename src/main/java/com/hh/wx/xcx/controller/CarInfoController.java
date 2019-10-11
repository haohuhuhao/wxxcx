package com.hh.wx.xcx.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.CarInfo;
import com.hh.wx.xcx.service.CarInfoService;

@RestController
@RequestMapping("car")
public class CarInfoController {
	
	@Autowired
	private CarInfoService carInfoService;
	

	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResultVo<String> addByUser(@RequestBody CarInfo car){
		
		return carInfoService.insert(car);
	}
	
	@RequestMapping(value="queryByLonginUser",method=RequestMethod.GET)
	public ResultVo<List<CarInfo>> query(){
		
		return carInfoService.queryByLoginUser();
	}
	
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public ResultVo<CarInfo> getById(@PathVariable("id") Long id){
		
		return carInfoService.getById(id);
	}
	
	@RequestMapping(value="delete/{id}",method=RequestMethod.DELETE)
	public ResultVo<String> deleteById(@PathVariable("id") Long id){
		
		return carInfoService.deleteById(id);
	}
	
	@RequestMapping(value="update",method=RequestMethod.PUT)
	public ResultVo<String> update(@RequestBody CarInfo car){
		
		return carInfoService.update(car);
	}
}

