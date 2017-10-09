package com.lewis.sof.event;

import java.util.Collection;

/**
 * 事件广播接口，负责事件框架生命周期内的事件监听器注册、注销，事件的推送
 */
public interface EventMulticaster {

    /**
     * 注册事件监听器
     *
     * @param listener 待注册的事件监听器
     */
    void addListener(BaseListener<BaseEvent> listener);

    /**
     * 注销事件监听器
     *
     * @param listener 待注销的事件监听器
     */
    void removeListener(BaseListener<BaseEvent> listener);

    /**
     * 注销所有的事件监听器
     */
    void removeAllListener();

    /**
     * 获取所有注册的事件监听器
     */
    Collection<BaseListener<BaseEvent>> getAllListeners();

    /**
     * 获取所有注册的支持该事件的事件监听器
     *
     * @param baseEvent
     * @return
     */
    Collection<BaseListener<BaseEvent>> getAllListeners(BaseEvent baseEvent);

    /**
     * 广播事件
     *
     * @param event 事件
     * @param sync  true:同步  false:异步广播
     */
    void multicastEvent(BaseEvent event, boolean sync);
}
