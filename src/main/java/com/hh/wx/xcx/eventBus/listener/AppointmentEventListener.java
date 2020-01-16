package com.hh.wx.xcx.eventBus.listener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.Subscribe;
import com.hh.wx.xcx.eventBus.event.AppointmentEvent;
import com.hh.wx.xcx.quartx.SchedulerManager;
import com.hh.wx.xcx.quartx.job.ScheduledJob;
import com.hh.wx.xcx.utils.wx.WxDataHander;

@Component
public class AppointmentEventListener {
	
	@Autowired
    public SchedulerManager myScheduler;
	
	@Autowired
	private WxDataHander wxDataHander;
	
	private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
	    @Override
	    protected DateFormat initialValue() {
	        return new SimpleDateFormat("ss mm HH dd MM ? yyyy");
	    }
	};
	

	@Subscribe
	public void onEvent(AppointmentEvent event){
		//myScheduler.startJob("", jobName, jobGroup, jobClass, jobDataMap);
		//wxDataHander.sendkefumsg(event.getAppId(),event.getOpenid());
		
		try {
			JobDataMap map = new JobDataMap();
			myScheduler.startJob(toCornStr(event.getTime()), "提醒"+event.getAppointmentId(), "tixing", ScheduledJob.class, map);
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String toCornStr(Date time){
		return threadLocal.get().format(time);
	}
	
	
}
