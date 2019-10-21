package com.hh.wx.xcx.wx.eventbus.factory;

import com.alibaba.fastjson.JSON;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.hh.wx.xcx.commons.IdGenerator;
import com.hh.wx.xcx.wx.eventbus.event.DomainEvent;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;



@Slf4j
public abstract class AbstractDomainEventBasePublisher {
    protected EventBus eventBus;
    protected AsyncEventBus asyncEventBus;

    public <T extends DomainEvent> void encapsulationEvent(final T event){
        DomainEvent domainEvent = event;
        domainEvent.setEventId(String.valueOf(IdGenerator.getInstance().generateId()));
        domainEvent.setEventType("default");
        domainEvent.setCreateTime(new Date());
        domainEvent.setEntityType(event.getClass().getSimpleName());
    }

    public <T extends DomainEvent> void post(final T event) {
        encapsulationEvent(event);
        if(log.isDebugEnabled()){
            log.debug("同步处理,事件报文：{}", JSON.toJSONString(event));
        }
        this.eventBus.post(event);
    }

    public <T extends DomainEvent> void postAsync(final T event) {
        encapsulationEvent(event);
        if(log.isDebugEnabled()){
            log.debug("异步处理,事件报文：{}", JSON.toJSONString(event));
        }
        this.asyncEventBus.post(event);
    }
}

