package com.hugo.watcher;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import com.hugo.watcher.config.WatcherConfig;

public class Watcher {

    private WatcherConfig mWatcherConfig;
    private boolean mHasStarted = false;

    private Watcher() {
    }

    public static Watcher getInstance() {
        return SingletonHolder.Instance;
    }

    private static class SingletonHolder {
        private static final Watcher Instance = new Watcher();
    }

    public Watcher setWatcherConfig(WatcherConfig watcherConfig) {
        mWatcherConfig = watcherConfig;
        return this;
    }

    public void start(Context context) {
        if (mHasStarted) {
            return;
        }

        if (mWatcherConfig == null) {
            mWatcherConfig = new WatcherConfig();
        }

        if (!mWatcherConfig.isDebug) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(context)) {
                Log.e("Watcher", "!!! ---> Can't start Watcher : permission denied for window type");
                return;
            }
        }
        Intent intent = new Intent(context, WatcherService.class);
        intent.putExtra(WatcherConfig.CONFIG_KEY, mWatcherConfig);
        context.startService(intent);
        mHasStarted = true;
    }

    public void stop(Context context) {
        if (mHasStarted) {
            mHasStarted = false;
            context.stopService(new Intent(context, WatcherService.class));
        }
    }
}
