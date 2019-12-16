package com.hh.wx.xcx.quartx.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScheduledJob implements Job
{
 
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException
    {
    	
    	JobDataMap map = jobExecutionContext.getJobDetail().getJobDataMap();
 
    	if(map != null){
    		System.out.println("data:"+map.getString("id"));
    	}
        //执行任务逻辑....
        log.info("执行自定义定时任务, time is {}.", new Date());
    }
}
