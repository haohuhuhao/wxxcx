package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Insurance;
import com.hh.wx.xcx.service.InsuranceDetailService;
import com.hh.wx.xcx.service.InsuranceService;
import com.hh.wx.xcx.service.mapper.InsuranceDetailMapper;
import com.hh.wx.xcx.service.mapper.InsuranceMapper;

@Service
public class InsuranceDetailServiceImpl implements InsuranceDetailService {

	@Autowired
	private InsuranceDetailMapper insuranceDetailMapper;

}
