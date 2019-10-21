package com.hh.wx.xcx.wx.eventbus.factory;

import java.util.Date;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.wx.eventbus.event.DomainEvent;

public  abstract class  AbstractDomainEventPublisher extends AbstractDomainEventBasePublisher{

    public AbstractDomainEventPublisher(ThreadPoolTaskExecutor threadPoolTaskExecutor){
        this.eventBus = new EventBus(identify());
        this.asyncEventBus = new AsyncEventBus(identify(), threadPoolTaskExecutor);
    }

    @Override
    public <T extends DomainEvent> void encapsulationEvent(T event) {
        DomainEvent domainEvent = event;
        domainEvent.setEventId(String.valueOf(IdGenerator.getInstance().generateId()));
        domainEvent.setEventType(identify());
        domainEvent.setCreateTime(new Date());
        domainEvent.setEntityType(event.getClass().getSimpleName());
    }

    public void register(Object listener){
        eventBus.register(listener);
        asyncEventBus.register(listener);
    }

    /**
     * 事件identify。必须设置，比如订单处理，可设置为:ORDER_EVENT
     * @author mb.wang
     * @date 2018/5/30 08:59
     * @return void
     */
    public abstract String identify();

}