package com.hh.wx.xcx.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.UserAppInfo;

@Mapper
public interface UserAppInfoMapper {

	void add(UserAppInfo userAppInfo);

}
