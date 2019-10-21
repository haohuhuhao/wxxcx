package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.MaintainInfo;

@Mapper
public interface MaintainInfoMapper {
	
	void insert(MaintainInfo maintainInfo);

	List<MaintainInfo> queryByCarIdAndAppId(@Param("carId")Long carId, @Param("appId")Long appId, @Param("status")Integer status);

	void update(MaintainInfo maintainInfo);

	Integer changeStatus(@Param("id")Long id,@Param("status") Integer status);

	Integer getMaintainInfoStatus(@Param("id")Long id);

	MaintainInfo getById(@Param("id")Long maintainInfoId);
}
