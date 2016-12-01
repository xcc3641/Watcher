package com.hugo.watcher.config;


public interface MonitorTimeMethod {

    void start();

    void stop();

    void setInterval(int time);
}
