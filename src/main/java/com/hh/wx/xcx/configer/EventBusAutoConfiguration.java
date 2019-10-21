package com.hh.wx.xcx.configer;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.hh.wx.xcx.eventBus.beanPost.DefaultEventBusSubscriberBeanPostProcessor;
import com.hh.wx.xcx.wx.eventbus.factory.DeadEventListener;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultDomainEventPublisher;
import com.hh.wx.xcx.wx.eventbus.factory.DefaultSubscriberExceptionHandler;

@ConditionalOnClass(EventBus.class)
@Configuration
public class EventBusAutoConfiguration {

    @Bean(destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        //执行初始化
        executor.initialize();

        return executor;
    }

    @Bean
    public SubscriberExceptionHandler subscriberExceptionHandler(){
        return new DefaultSubscriberExceptionHandler();
    }

    @Bean
    public DefaultDomainEventPublisher eventBusWrapper(SubscriberExceptionHandler exceptionHandler,DeadEventListener deadEventListener) {
        EventBus eventBus = new EventBus(exceptionHandler);
        eventBus.register(deadEventListener);

        AsyncEventBus asyncEventBus = new AsyncEventBus(threadPoolTaskExecutor(),exceptionHandler);
        asyncEventBus.register(deadEventListener);
        return new DefaultDomainEventPublisher(eventBus, asyncEventBus);
    }

    @Bean
    public DefaultEventBusSubscriberBeanPostProcessor subscriberAnnotationProcessor(DefaultDomainEventPublisher defaultDomainEventPublisher) {
        return new DefaultEventBusSubscriberBeanPostProcessor(defaultDomainEventPublisher);
    }

    @Bean
    public DeadEventListener deadEventListener(){
        return new DeadEventListener();
    }
}

