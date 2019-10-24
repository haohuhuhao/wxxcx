package com.hh.wx.xcx.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hh.wx.xcx.commons.ResultUtils;
import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Insurance;
import com.hh.wx.xcx.service.InsuranceService;
import com.hh.wx.xcx.service.mapper.InsuranceMapper;

@Service
public class InsuranceServiceImpl implements InsuranceService {

	@Autowired
	private InsuranceMapper insuranceMapper;

	@Override
	public ResultVo<String> add(Insurance insurance) {
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<List<Insurance>> queryAll(Insurance insurance) {
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<Insurance> getById(Long id) {
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> update(Insurance insurance) {
		return ResultUtils.secusses();
	}

	@Override
	public ResultVo<String> deleteById(Long id) {
		return ResultUtils.secusses();
	}
	
}
