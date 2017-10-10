package com.lewis.sof.context;

/**
 * SOF上下文感知接口，业务代码通过实现该接口获得SOF上下文
 * 系统会将所有实现了该接口的实现类，在系统启动时注入SOF上下文给实现类
 */
public interface SOFContextAware {

    /**
     * 注入SOF上下文
     */
    void setSOFContext(SOFContext context);
}
