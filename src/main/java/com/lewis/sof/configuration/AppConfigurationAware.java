package com.lewis.sof.configuration;

/**
 * 实现该接口的类，将获取应用配置信息实例
 */
public interface AppConfigurationAware {
    /**
     * 设置应用配置信息
     *
     * @param appConfiguration
     */
    void setAppConfiguration(AppConfiguration appConfiguration);
}
