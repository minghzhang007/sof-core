package com.lewis.sof.context;

/**
 * SOF容器关闭感知接口，在SOF容器销毁之前，会调用所有实现了该接口的实现类的shutdown方法，
 * 应用通过实现该接口，可以在容器销毁前，执行自定义业务逻辑
 */
public interface ShutdownCallback {

    /**
     * 在容器销毁前执行前处理操作
     */
    void shutdown(SOFContext context);
}
