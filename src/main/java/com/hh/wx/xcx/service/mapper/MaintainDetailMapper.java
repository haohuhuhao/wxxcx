package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.MaintainDetail;

@Mapper
public interface MaintainDetailMapper {
	
	void insert(MaintainDetail maintainDetail);

	List<MaintainDetail> queryBymaintainInfoId(@Param("maintainInfoId")Long maintainInfoId);

	void update(MaintainDetail maintainDetail);

	void bacthInsert(List<MaintainDetail> maintainDetails);

	void batchUpdate(List<MaintainDetail> maintainDetails);
}
