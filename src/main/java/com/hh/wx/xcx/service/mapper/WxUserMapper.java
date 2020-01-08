package com.hh.wx.xcx.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.WxUser;

@Mapper
public interface WxUserMapper {

	WxUser findByOpengId(String openid);

	void addUser(WxUser user);

}
