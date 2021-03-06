package com.hh.wx.xcx.quartx;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
/**
 * 
 * @author Button
 * 为JobFactory注入SpringBean,否则Job无法使用Spring创建的bean
 */
public class AutoWiredSpringBeanToJobFactory extends SpringBeanJobFactory{
	private transient AutowireCapableBeanFactory beanFactory;

    @Override
    public void setApplicationContext(final ApplicationContext context) {
    	if(beanFactory == null){
    		beanFactory = context.getAutowireCapableBeanFactory();
    	}
    }
    
    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;
    }
}
