package com.hh.wx.xcx.service.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.Remind;

@Mapper
public interface RemindMapper {

	void insert(Remind remind);

	void topOrNo(Remind remind);

	void publishOrNo(Remind remind);

	Remind getById(Long remindId);

	Long count(Remind queryRemind);

	List<Remind> list(@Param("queryRemind") Remind queryRemind,@Param("index") Integer index ,@Param("limit") Integer limit);
}
