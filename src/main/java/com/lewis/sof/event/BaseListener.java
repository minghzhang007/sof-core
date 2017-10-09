package com.lewis.sof.event;

import java.util.EventListener;

/**
 * 事件监听器
 */
public interface BaseListener<E extends BaseEvent> extends EventListener {

    /**
     * 是否支持处理该事件类型
     */
    boolean supportEvent(BaseEvent baseEvent);

    /**
     * 处理该事件的接口方法
     */
    void onEvent(E e);
}
