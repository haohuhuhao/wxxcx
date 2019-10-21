package com.hh.wx.xcx.wx.eventbus.factory;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultSubscriberExceptionHandler implements SubscriberExceptionHandler {
    @Override
    public void handleException(Throwable throwable, SubscriberExceptionContext subscriberExceptionContext) {

        Logger logger = logger(subscriberExceptionContext);
        if (logger.isDebugEnabled()) {
            logger.debug( message(subscriberExceptionContext), throwable);
        }
    }

    private static Logger logger(SubscriberExceptionContext context) {
        return LoggerFactory.getLogger(EventBus.class.getName() + "." + context.getEventBus().identifier());
    }

    private static String message(SubscriberExceptionContext context) {
        Method method = context.getSubscriberMethod();
        return "Exception thrown by subscriber method " + method.getName() + '(' + method.getParameterTypes()[0].getName() + ')' + " on subscriber " + context.getSubscriber() + " when dispatching event: " + context.getEvent();
    }
}
