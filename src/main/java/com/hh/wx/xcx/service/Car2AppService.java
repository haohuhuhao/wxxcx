package com.hh.wx.xcx.service;


import com.hh.wx.xcx.commons.ResultVo;
import com.hh.wx.xcx.model.Car2App;

public interface Car2AppService {

	ResultVo<String> insert(Long carId);

	ResultVo<String> deleteByCarId(Long carId);

	Car2App getByCarId(Long id);

}
