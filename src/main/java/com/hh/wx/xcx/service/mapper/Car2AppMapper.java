package com.hh.wx.xcx.service.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.Car2App;

@Mapper
public interface Car2AppMapper {

	void insert(Car2App car2App);


	void deleteByIdAndAppId(@Param("carId") Long carId,@Param("appId")Long appId);


	Car2App getByCarIdAndAppId(@Param("carId") Long carId,@Param("appId")Long appId);

}
