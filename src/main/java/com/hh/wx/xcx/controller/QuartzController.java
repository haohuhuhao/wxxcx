package com.hh.wx.xcx.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hh.wx.xcx.quartx.SchedulerManager;
import com.hh.wx.xcx.quartx.job.ScheduledJob;

@RestController
@RequestMapping("/quartz")
public class QuartzController
{
    @Autowired
    public SchedulerManager myScheduler;
 
    @RequestMapping(value = "/job2", method = RequestMethod.GET)
    public String scheduleJob2()
    {
        try
        {
            myScheduler.startJob("0/5 * * * * ?", "job2", "group2", ScheduledJob.class);//每五秒执行一次
            return "启动定时器成功";
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        }
        return "启动定时器失败";
    }
 
    @RequestMapping(value = "/del_job2", method = RequestMethod.GET)
    public String deleteScheduleJob2()
    {
        try
        {
            myScheduler.deleteJob("job2", "group2");
            return "删除定时器成功";
        }
        catch (SchedulerException e)
        {
            e.printStackTrace();
        }
        return "删除定时器失败";
    }
}
