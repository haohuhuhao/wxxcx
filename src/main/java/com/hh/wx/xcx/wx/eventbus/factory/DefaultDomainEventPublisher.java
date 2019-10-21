package com.hh.wx.xcx.wx.eventbus.factory;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

public class DefaultDomainEventPublisher extends AbstractDomainEventBasePublisher {
	public void register(Object object) {
		this.eventBus.register(object);
		this.asyncEventBus.register(object);
	}

	public void unregister(Object object) {
		this.eventBus.unregister(object);
		this.asyncEventBus.unregister(object);
	}

	public DefaultDomainEventPublisher(final EventBus eventBus, final AsyncEventBus asyncEventBus) {
		this.eventBus = eventBus;
		this.asyncEventBus = asyncEventBus;
	}
}
