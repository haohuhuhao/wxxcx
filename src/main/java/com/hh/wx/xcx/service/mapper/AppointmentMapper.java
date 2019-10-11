package com.hh.wx.xcx.service.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.hh.wx.xcx.model.Appointment;

@Mapper
public interface AppointmentMapper {
	
	void insert(Appointment appointment);
}
