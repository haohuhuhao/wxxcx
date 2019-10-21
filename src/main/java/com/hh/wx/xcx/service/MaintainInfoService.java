package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.MaintainInfo;

public interface MaintainInfoService {

	ResultVo<String> create(MaintainInfo maintainInfo);

	ResultVo<List<MaintainInfo>> queryByCarId(Long carId,Integer status);

	ResultVo<String> update(MaintainInfo maintainInfo);

	ResultVo<String> changeStatus(Long id);

	MaintainInfo getById(Long maintainInfoId);

}
