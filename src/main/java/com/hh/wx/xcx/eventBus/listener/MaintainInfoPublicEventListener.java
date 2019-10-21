package com.hh.wx.xcx.eventBus.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.hh.wx.xcx.eventBus.event.MaintainInfoPublicEvent;
import com.hh.wx.xcx.model.MaintainInfo;
import com.hh.wx.xcx.quartx.SchedulerManager;
import com.hh.wx.xcx.service.MaintainInfoService;

@Component
public class MaintainInfoPublicEventListener {
	
	@Autowired
	private MaintainInfoService maintainInfoService;
	
	@Autowired
    public SchedulerManager myScheduler;
	

	@Subscribe
	public void onEvent(MaintainInfoPublicEvent event){
		MaintainInfo maintainInfo = maintainInfoService.getById(event.getMaintainInfoId());
		
		//myScheduler.startJob(cron, jobName, jobGroup, jobClass);
	}
	
	
}
