package com.hugo.watcher.monitor;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.Choreographer;
import com.hugo.watcher.config.MonitorTimeMethod;
import com.hugo.watcher.config.WatcherListener;
import java.util.concurrent.TimeUnit;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class FpsMonitor implements Choreographer.FrameCallback, MonitorTimeMethod {
    private Choreographer mChoreographer;

    private long startTime = 0;
    private int renderCount = 0; // 渲染次数

    private int interval = 500; // 间隔时间

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
        this.interval = time;
    }

    public void setListener(WatcherListener listener) {
        this.mListener = listener;
    }

    @Override
    public void doFrame(long l) {
        // 返回的是纳秒，转换为毫秒
        long currentTimeMills = TimeUnit.MILLISECONDS.convert(l, TimeUnit.NANOSECONDS);
        if (startTime > 0) {
            long waitTime = currentTimeMills - startTime;
            renderCount++;
            if (waitTime > interval) {
                // 计算帧率
                int fps = (int) ((renderCount * 1000) / waitTime);
                startTime = currentTimeMills;
                renderCount = 0;
                mListener.post(fps);
            }
        } else {
            startTime = currentTimeMills;
        }

        mChoreographer.postFrameCallback(this);
    }


}
