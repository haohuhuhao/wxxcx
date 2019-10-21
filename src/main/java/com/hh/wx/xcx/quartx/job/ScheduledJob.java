package com.hh.wx.xcx.quartx.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduledJob implements Job
{
 
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledJob.class);
 
    @Override
    public void execute(JobExecutionContext jobExecutionContext)
        throws JobExecutionException
    {
 
        //执行任务逻辑....
        LOGGER.info("执行自定义定时任务, time is {}.", new Date());
    }
}
