package com.hugo.watcher.monitor;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Choreographer;
import com.hugo.watcher.config.IMonitorMethod;
import com.hugo.watcher.config.WatcherListener;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FpsMonitor implements Choreographer.FrameCallback, IMonitorMethod {
    private Choreographer mChoreographer;

    private long mStartTime = 0;
    private int mRenderCount = 0; // 渲染次数

    private int mInterval = 500; // 间隔时间

    private WatcherListener mListener;

    public FpsMonitor() {
        mChoreographer = Choreographer.getInstance();
    }


    @Override
    public void start() {
        mChoreographer.postFrameCallback(this);
    }

    @Override
    public void stop() {
        mChoreographer.removeFrameCallback(this);
    }

    @Override
    public void setInterval(int time) {
        this.mInterval = time;
    }

    public void setListener(WatcherListener listener) {
        this.mListener = listener;
    }

    @Override
    public void doFrame(long l) {
        // 返回的是纳秒，转换为毫秒
        long currentTimeMills = TimeUnit.MILLISECONDS.convert(l, TimeUnit.NANOSECONDS);
        if (mStartTime > 0) {
            long waitTime = currentTimeMills - mStartTime;
            mRenderCount++;
            if (waitTime > mInterval) {
                // 计算帧率
                int fps = (int) ((mRenderCount * 1000) / waitTime);
                mStartTime = currentTimeMills;
                mRenderCount = 0;
                mListener.post(fps);
            }
        } else {
            mStartTime = currentTimeMills;
        }

        mChoreographer.postFrameCallback(this);
    }


}
