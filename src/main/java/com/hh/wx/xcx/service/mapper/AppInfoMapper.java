package com.hh.wx.xcx.service.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.AppInfo;

@Mapper
public interface AppInfoMapper {

	void insert(AppInfo app);

	List<AppInfo> queryAll(AppInfo app);

	AppInfo getById(Long id);

	void update(AppInfo app);

	void deleteById(Long id);

	boolean isHasAppRight(@Param("appId")Long appId,@Param("userId") Long userId);

	void effect(@Param("id") Long id,@Param("effectTime") Date effectTime);
}
