package com.hh.wx.xcx.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.service.Car2AppService;

@RestController
@RequestMapping("car2App")
public class Car2AppController {
	
	@Autowired
	private Car2AppService car2AppService;
	

	@RequestMapping(value="add",method=RequestMethod.PUT)
	public ResultVo<String> addByUser(Long carId){
		
		return car2AppService.insert(carId);
	}
	
	@RequestMapping(value="delete/{carId}",method=RequestMethod.DELETE)
	public ResultVo<String> deleteById(@PathVariable("carId") Long carId){
		
		return car2AppService.deleteByCarId(carId);
	}
	
}

