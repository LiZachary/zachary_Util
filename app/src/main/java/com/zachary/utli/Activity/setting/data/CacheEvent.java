package com.zachary.utli.Activity.setting.data;

import com.zachary.utli.BaseEventBus.Event;

/**
 * Created by Zachary on 2017-04-26.
 */
public class CacheEvent extends Event{

    public boolean infinish;
    public CacheEvent(boolean infinish,Object source) {
        super(source);
        this.infinish=infinish;
    }
}
