package com.lewis.sof.context;

import com.lewis.sof.configuration.AppConfiguration;
import com.lewis.sof.event.BaseEvent;
import com.lewis.sof.event.EventMulticaster;

/**
 * SOF容器上下文
 */
public interface SOFContext extends ListenerRegistry {

    /**
     * 获取容器ID
     */
    String getId();

    /**
     * 获取应用名称
     */
    String getAppName();

    /**
     * 获取容器运行时名称
     */
    String getContextName();

    /**
     * 获取容器启动时间
     */
    long getStartupTime();

    /**
     * 容器启动
     */
    void start();

    /**
     * 容器关闭
     */
    void shutdown();

    /**
     * 容器是否运行
     */
    boolean isRunning();

    /**
     * 事件投递
     */
    void fireEvent(BaseEvent event, boolean sync);

    /**
     * 获取系统注册的事件广播器
     */
    EventMulticaster getEventMulticaster();

    /**
     * 设置事件广播器
     */
    void setEventMulticaster(EventMulticaster eventMulticaster);

    /**
     * 获取应用配置
     */
    AppConfiguration getAppConfiguration();
}
