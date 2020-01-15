package com.hh.wx.xcx.eventBus.event;

import java.util.Date;
import java.util.UUID;

import com.hh.wx.xcx.wx.eventbus.event.DomainEvent;

import lombok.Data;

@Data
public class AppointmentEvent extends DomainEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppointmentEvent() {
		super(UUID.randomUUID().toString());
	}
	
	private Long AppointmentId;
	
	private Integer type;
	
	private Date time;

	public AppointmentEvent(Long appointmentId, Integer type, Date time) {
		super(UUID.randomUUID().toString());
		this.AppointmentId = appointmentId;
		this.type = type;
		this.time = time;
	}
	
	
}
