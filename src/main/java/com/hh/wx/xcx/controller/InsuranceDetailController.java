package com.hh.wx.xcx.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.service.InsuranceDetailService;

@RestController
@RequestMapping("insuranceDetail")
public class InsuranceDetailController {
	
	@Autowired
	private InsuranceDetailService insuranceDetailService;
}

