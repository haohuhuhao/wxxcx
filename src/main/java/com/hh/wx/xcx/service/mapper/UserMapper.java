package com.hh.wx.xcx.service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.User;

@Mapper
public interface UserMapper {

	void insert(User user);

	User findOne( @Param("phone")String phone);

	void update(User user);
}
