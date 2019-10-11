package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.CarInfo;

@Mapper
public interface CarMapper {

	void insert(CarInfo car);

	List<CarInfo> queryByUserId(Long id);

	CarInfo getById(Long id);

	void deleteById(Long id);

	void update(CarInfo car);
}
