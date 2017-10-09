package com.lewis.sof.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 简单事件广播实现，同步、异步广播事件，并从事件监听器注册容器中，获取正确的监听器进行事件处理
 */
public class SimpleEventMulticaster extends AbstractEventMulticaster {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(SimpleEventMulticaster.class);

    /**
     * Asynchronous executor
     */
    private Executor taskExecutor;

    public void multicastEvent(BaseEvent event, boolean sync) {
        for (final BaseListener<BaseEvent> listener : getAllListeners(event)) {
            Executor executor = getTaskExecutor();

            if (!sync) {
                // async
                executor.execute(new AnonymityEventHandler(event, listener));
            } else {
                // sync
                try {
                    listener.onEvent(event);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * 获取任务执行器
     *
     * @return
     */
    public Executor getTaskExecutor() {
        if (taskExecutor == null) {
            taskExecutor = Executors.newCachedThreadPool(new NamedThreadFactory("sof-event", true));
        }
        return taskExecutor;
    }

    /**
     * 设置任务执行器
     *
     * @param taskExecutor
     */
    public void setTaskExecutor(Executor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }


    /**
     * 异步事件处理
     */
    private final class AnonymityEventHandler implements Runnable {
        /**
         * 事件
         */
        private BaseEvent event;

        /**
         * 事件监听器
         */
        private BaseListener<BaseEvent> listener;

        /**
         * 构造函数
         *
         * @param event    事件
         * @param listener 事件监听器
         */
        public AnonymityEventHandler(BaseEvent event, BaseListener<BaseEvent> listener) {
            super();
            this.event = event;
            this.listener = listener;
        }

        /**
         * 事件处理
         *
         * @see java.lang.Runnable#run()
         */
        public void run() {
            try {
                listener.onEvent(event);
            } catch (Exception e) {
                logger.error("[Process a event, failure] event: " + event.getClass().getName() + ":" + event.getId(), e);
            }
        }
    }

    private static class NamedThreadFactory implements ThreadFactory {

        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        private final String mPrefix;

        private final boolean mDaemo;

        private final ThreadGroup mGroup;

        public NamedThreadFactory(String prefix, boolean daemon) {
            mPrefix = prefix + "-thread-";
            mDaemo = daemon;
            SecurityManager s = System.getSecurityManager();
            mGroup = (s == null) ? Thread.currentThread().getThreadGroup() : s.getThreadGroup();
        }

        public Thread newThread(Runnable runnable) {
            String name = mPrefix + mThreadNum.getAndIncrement();
            Thread ret = new Thread(mGroup, runnable, name, 0);
            ret.setDaemon(mDaemo);
            return ret;
        }
    }
}
