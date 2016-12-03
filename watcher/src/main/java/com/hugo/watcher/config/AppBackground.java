package com.hugo.watcher.config;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.hugo.watcher.Watcher;

/**
 * Created by HugoXie on 2016/12/3.
 *
 * Email: Hugo3641@gmail.com
 * GitHub: https://github.com/xcc3641
 * Info:
 */

public class AppBackground implements Application.ActivityLifecycleCallbacks {
    private static final String TAG = AppBackground.class.getSimpleName();

    private static AppBackground sInstance;
    private int mCount = 0;
    private boolean mIsBackground = false;

    public static AppBackground init(Application app) {
        if (sInstance == null) {
            sInstance = new AppBackground(app);
        }
        return sInstance;
    }

    private AppBackground(Application app) {
        app.registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        mCount++;
        if (mIsBackground) {
            mIsBackground = false;
            Watcher.getInstance().start(activity.getApplicationContext());
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        mCount--;
        if (!mIsBackground && mCount < 0) {
            mIsBackground = true;
            Watcher.getInstance().stop(activity.getApplicationContext());
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
