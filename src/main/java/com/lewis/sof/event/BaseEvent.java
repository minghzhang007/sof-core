package com.lewis.sof.event;

import java.util.EventObject;

/**
 * 基础事件模型
 */
public abstract class BaseEvent extends EventObject {

    /**
     * 事件Id
     */
    private final String id;

    /**
     * 事件产生的时间
     */
    private final long timestamp;

    /**
     * Constructs a prototypical Event.
     *
     * @param source 产生事件的事件源
     * @param id     事件id
     */
    public BaseEvent(Object source, String id) {
        super(source);
        this.id = id;
        this.timestamp = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "BaseEvent{" + "id='" + id + ", timestamp=" + timestamp + '}';
    }
}
