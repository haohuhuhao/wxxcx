package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.AppInfo;

@Mapper
public interface AppInfoMapper {

	void insert(AppInfo app);

	List<AppInfo> queryAll(AppInfo app);
}