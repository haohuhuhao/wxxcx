package com.hh.wx.xcx.service;

import java.util.List;

import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.CarInfo;

public interface CarInfoService {

	ResultVo<String> insert(CarInfo car);

	ResultVo<List<CarInfo>> queryByLoginUser();

	ResultVo<CarInfo> getById(Long id);

	ResultVo<String> deleteById(Long id);

	ResultVo<String> update(CarInfo car);

	ResultVo<CarInfo> getByCarNum(String num);

}
