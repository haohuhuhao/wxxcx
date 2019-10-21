package com.hh.wx.xcx.configer;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.hh.wx.xcx.quartx.AutoWiredSpringBeanToJobFactory;
import com.hh.wx.xcx.quartx.job.ScheduledJob;

@Configuration
@EnableScheduling
public class SchedulerConfig {
	
		@Autowired
	    @Qualifier(value = "dataSource")
	    private DataSource dataSource;
		
		@Bean
	    public JobFactory jobFactory(ApplicationContext applicationContext)
	    {
			AutoWiredSpringBeanToJobFactory jobFactory = new AutoWiredSpringBeanToJobFactory();
	        jobFactory.setApplicationContext(applicationContext);
	        return jobFactory;
	    }
		
		
		@Bean
	    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory/*,
	                                                     Trigger simpleJobTrigger*/)
	        throws IOException
	    {
	        SchedulerFactoryBean factory = new SchedulerFactoryBean();
	 
	        factory.setJobFactory(jobFactory);
	        factory.setQuartzProperties(quartzProperties());
	        //factory.setTriggers(simpleJobTrigger);
	        factory.setDataSource(dataSource);
	        factory.setWaitForJobsToCompleteOnShutdown(
	            true);//这样当spring关闭时，会等待所有已经启动的quartz job结束后spring才能完全shutdown。
	        factory.setOverwriteExistingJobs(false);
	        factory.setStartupDelay(1);
	        //设置调度器自动运行
	        factory.setAutoStartup(true);
	        //设置上下文spring bean name
	        factory.setApplicationContextSchedulerContextKey("applicationContext");
	 
	        return factory;
	    }
		
		//@Bean
	    public CronTriggerFactoryBean simpleJobTrigger(
	        @Qualifier("simpleJobDetail") JobDetail jobDetail)
	    {
	        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
	 
	        factoryBean.setJobDetail(jobDetail);
	        factoryBean.setStartDelay(1000L);
	        factoryBean.setName("trigger1");
	        factoryBean.setGroup("group1");
	        //周1至周5，每天上午8点至下午18点，每分钟执行一次
	        factoryBean.setCronExpression("0 0/1 8-18 ? * 2-6");
	 
	        return factoryBean;
	    }
		
		//@Bean
	    public JobDetailFactoryBean simpleJobDetail()
	    {
	        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
	 
	        factoryBean.setJobClass(ScheduledJob.class);
	        factoryBean.setGroup("group1");
	        factoryBean.setName("job1");
	 
	        return factoryBean;
	    }
		
		@Bean
	    public Properties quartzProperties()throws IOException{
	        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
	        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
	        propertiesFactoryBean.afterPropertiesSet();
	        return propertiesFactoryBean.getObject();
	    }
}
