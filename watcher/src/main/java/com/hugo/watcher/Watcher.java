package com.hugo.watcher;

import android.content.Context;
import android.content.Intent;
import com.hugo.watcher.config.WatcherConfig;

public class Watcher {

    private WatcherConfig mWatcherConfig;
    private boolean hasStarted;
    private static final Watcher INSTANCE = new Watcher();

    private Watcher() {
    }

    public static Watcher getInstance() {
        return INSTANCE;
    }

    public Watcher setWatcherConfig(WatcherConfig watcherConfig) {
        mWatcherConfig = watcherConfig;
        return this;
    }

    public void start(Context context) {
        if (mWatcherConfig == null) {
            mWatcherConfig = new WatcherConfig();
        }

        if (!mWatcherConfig.isDebug) {
            return;
        }

        Intent intent = new Intent(context, JWatcherService.class);
        intent.putExtra(WatcherConfig.CONFIG_KEY, mWatcherConfig);
        context.startService(intent);
        hasStarted = true;
    }

    public void stop(Context context) {
        if (!mWatcherConfig.isDebug) {
            return;
        }

        if (hasStarted) {
            context.stopService(new Intent(context, JWatcherService.class));
        }
    }

}
