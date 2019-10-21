package com.hh.wx.xcx.eventBus.beanPost;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

import com.google.common.eventbus.Subscribe;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultDomainEventPublisher;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultEventBusSubscriberBeanPostProcessor implements BeanPostProcessor {
	
	private DefaultDomainEventPublisher defaultDomainEventPublisher;
	
	public DefaultEventBusSubscriberBeanPostProcessor(DefaultDomainEventPublisher defaultDomainEventPublisher) {
		this.defaultDomainEventPublisher = defaultDomainEventPublisher;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		for (Method m : ReflectionUtils.getAllDeclaredMethods(bean.getClass())) {
                if (m.isAnnotationPresent(Subscribe.class)) {
                    log.info("注册默认事件订阅者：{}", bean.getClass().getName());
                    this.defaultDomainEventPublisher.register(bean);

                    break;
                }
		}
		return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
	}

}
