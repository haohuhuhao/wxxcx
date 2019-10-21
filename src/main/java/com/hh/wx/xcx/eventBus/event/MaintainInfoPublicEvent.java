package com.hh.wx.xcx.eventBus.event;

import java.util.UUID;

import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.wx.eventbus.event.DomainEvent;

import lombok.Data;

@Data
public class MaintainInfoPublicEvent extends DomainEvent{

	public MaintainInfoPublicEvent() {
		super(UUID.randomUUID().toString());
	}
	
	private Long maintainInfoId;
}
