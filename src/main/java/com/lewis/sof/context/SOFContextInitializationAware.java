package com.lewis.sof.context;

/**
 * 容器初始化感知接口，应用实现该接口，可以在容器初始化时执行自定义业务逻辑
 */
public interface SOFContextInitializationAware {

    /**容器初始化 */
    void init();
}
