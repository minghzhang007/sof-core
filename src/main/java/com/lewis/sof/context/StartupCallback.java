package com.lewis.sof.context;

/**
 * SOF容器启动感知接口，在SOF完成启动后，会调用所有实现了该接口的实现类的startup方法，
 * 应用通过实现该接口，可以在容器启动后，执行自定义业务逻辑
 */
public interface StartupCallback extends Order {

    /**
     * 默认顺序
     */
    static final int DEFAULT_ORDER = 99;

    /**
     * 执行容器启动后的后处理操作
     *
     * @param context SOF上下文
     */
    void startup(SOFContext context);

}
