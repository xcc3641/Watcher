package com.hugo.watchersample;

import android.app.Application;
import com.hugo.watcher.Watcher;
import com.hugo.watcher.config.WatcherConfig;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // default
        Watcher.getInstance().start(this);

        // custom
        WatcherConfig watcherConfig = new WatcherConfig();
        watcherConfig.enableFps = false;
        // ....
        Watcher.getInstance().setWatcherConfig(watcherConfig).start(this);
    }
}
