package com.zachary.utli.BaseEventBus;

/**
 * Created by Zachary on 2018-04-10.
 */
public class Event {
    private Object mSource;

    public Event(Object source) {
        if(null!=source){
            this.mSource = source;
        }

    }
}
