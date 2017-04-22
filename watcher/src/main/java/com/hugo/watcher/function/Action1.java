package com.hugo.watcher.function;


public interface Action1<T> extends Action {
    void call(T t);
}
