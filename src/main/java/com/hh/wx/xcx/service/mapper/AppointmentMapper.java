package com.hh.wx.xcx.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hh.wx.xcx.model.Appointment;

@Mapper
public interface AppointmentMapper {
	
	void insert(Appointment appointment);

	Long getCount(Appointment queryBody);

	List<Appointment> query(@Param("appointment")Appointment queryBody, @Param("skip")Integer page, @Param("limit")Integer pageSize);

	void cancelAppointmentOrder(@Param("id")Long id, @Param("userId")Long userId, @Param("appId")Long appId);

	Appointment findById(Long id);

	void deleteById(@Param("id")Long id, @Param("userId")Long userId, @Param("appId")Long appId);
}
