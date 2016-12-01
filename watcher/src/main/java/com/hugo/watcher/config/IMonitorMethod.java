package com.hugo.watcher.config;


public interface IMonitorMethod {

    void start();

    void stop();

    void setInterval(int time);
}
