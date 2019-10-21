package com.hh.wx.xcx.wx.eventbus.factory;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadEventListener {
    @Subscribe
    public void onEvent(DeadEvent de) {
        log.warn("发布了错误的事件:{}" , de.getEvent());
    }
}
