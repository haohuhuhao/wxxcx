package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Insurance;

public interface InsuranceService {

	ResultVo<String> add(Insurance insurance);

	ResultVo<List<Insurance>> queryAll(Insurance insurance);

	ResultVo<Insurance> getById(Long id);

	ResultVo<String> update(Insurance app);

	ResultVo<String> deleteById(Long id);

}
