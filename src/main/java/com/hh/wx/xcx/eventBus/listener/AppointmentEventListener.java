package com.hh.wx.xcx.eventBus.listener;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.hh.wx.xcx.eventBus.event.AppointmentEvent;
import com.hh.wx.xcx.quartx.SchedulerManager;

@Component
public class AppointmentEventListener {
	
	@Autowired
    public SchedulerManager myScheduler;
	

	@Subscribe
	public void onEvent(AppointmentEvent event){
		myScheduler.startJob("", jobName, jobGroup, jobClass, jobDataMap);
	}
	
	
}
