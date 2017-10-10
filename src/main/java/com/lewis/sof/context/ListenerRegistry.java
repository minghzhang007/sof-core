package com.lewis.sof.context;

import com.lewis.sof.event.BaseEvent;
import com.lewis.sof.event.BaseListener;

/**
 * 事件监听器注册接口，系统扫描容器中所有实现了BaseListener接口的实现类，并注册这些监听器
 */
public interface ListenerRegistry {
    /**
     * 注册容器中的监听器
     */
    void registerListener();

    /**
     * 注册指定的监听器
     */
    void registerListener(BaseListener<BaseEvent> baseListener);
}
