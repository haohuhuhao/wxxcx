package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.AppInfo;

@Mapper
public interface InsuranceMapper {

	void insert(AppInfo app);

	List<AppInfo> queryAll(AppInfo app);

	AppInfo getById(Long id);

	void update(AppInfo app);

	void deleteById(Long id);
}
