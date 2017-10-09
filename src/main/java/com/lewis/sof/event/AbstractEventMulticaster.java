package com.lewis.sof.event;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Administrator on 2017/10/9.
 */
public abstract class AbstractEventMulticaster implements EventMulticaster {

    /**
     * 默认事件监听器容器，保存所有注册的事件监听器
     */
    private final ListenerKeeper defaultListenerKeeper = new ListenerKeeper();

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void addListener(BaseListener<BaseEvent> listener) {
        try {
            lock.writeLock().lock();
            this.defaultListenerKeeper.listeners.add(listener);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeListener(BaseListener<BaseEvent> listener) {
        try {
            lock.writeLock().lock();
            this.defaultListenerKeeper.listeners.remove(listener);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void removeAllListener() {
        try {
            lock.writeLock().lock();
            this.defaultListenerKeeper.listeners.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Collection<BaseListener<BaseEvent>> getAllListeners() {
        return this.defaultListenerKeeper.retrieveListeners();
    }

    public Collection<BaseListener<BaseEvent>> getAllListeners(BaseEvent baseEvent) {
        List<BaseListener<BaseEvent>> allListeners = new LinkedList<BaseListener<BaseEvent>>();
        for (BaseListener<BaseEvent> listener : this.defaultListenerKeeper.listeners) {
            if (listener.supportEvent(baseEvent)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 事件监听器容器
     */
    private class ListenerKeeper {

        /**
         * 注册事件容器
         */
        public final Set<BaseListener<BaseEvent>> listeners;

        public ListenerKeeper() {
            listeners = new LinkedHashSet<BaseListener<BaseEvent>>();
        }

        /**
         * 获取容器中注册的监听器
         */
        public Collection<BaseListener<BaseEvent>> retrieveListeners() {
            List<BaseListener<BaseEvent>> allListeners = new ArrayList<BaseListener<BaseEvent>>(this.listeners.size());
            for (BaseListener<BaseEvent> listener : this.listeners) {
                allListeners.add(listener);
            }
            return allListeners;
        }
    }
}
