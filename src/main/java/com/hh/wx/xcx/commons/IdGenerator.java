package com.hh.wx.xcx.commons;


public class IdGenerator {
    private volatile static IdGenerator instance;
    private volatile static SnowFlake snowFlake;

    private IdGenerator() {
    	snowFlake = new SnowFlake(2, 3);
    }

    public static IdGenerator getInstance() {
        if (instance == null) {
            synchronized (IdGenerator.class) {
                if (instance == null) {
                    instance = new IdGenerator();
                }
            }
        }
        return instance;
    }

    public long generateId() {
        return snowFlake.nextId();
    }
}
