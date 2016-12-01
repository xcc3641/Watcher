package com.hugo.watcher.monitor;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Debug;
import com.hugo.watcher.config.IMonitorMethod;
import com.hugo.watcher.config.WatcherListener;
import java.util.List;

public class MemoryMonitor implements IMonitorMethod {
    private int mInterval = 500;
    private long mStartTime = 0;
    private boolean isFinish = false;

    private WatcherListener mListener;
    private ActivityManager mActivityManager;
    private String mPackageName;

    private RunningAppProcessInfo mRunningAppProcessInfo;

    public MemoryMonitor(ActivityManager mActivityManager, String packageName) {
        this.mActivityManager = mActivityManager;
        this.mPackageName = packageName;
    }

    public void setListener(WatcherListener listener) {
        mListener = listener;
    }

    @Override
    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isFinish) {
                    long currentTime = System.currentTimeMillis();
                    long diff = currentTime - mStartTime;
                    if (diff > mInterval) {
                        mListener.post(getRunningAppProcessInfo());
                        mStartTime = System.currentTimeMillis();
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void stop() {
        isFinish = true;
    }

    @Override
    public void setInterval(int time) {
        this.mInterval = time;
    }

    private double getRunningAppProcessInfo() {
        double memSize;

        if (mRunningAppProcessInfo != null) {
            return getMemSize(mRunningAppProcessInfo) / 1024;
        }

        // 通过调用 ActivityManager 的 getRunningAppProcesses() 方法获得系统里所有正在运行的进程
        List<RunningAppProcessInfo> appProcessList = mActivityManager
            .getRunningAppProcesses();
        for (RunningAppProcessInfo appProcessInfo : appProcessList) {
            // 进程名，默认是包名或者由属性 android：process="" 指定
            String processName = appProcessInfo.processName;
            memSize = getMemSize(appProcessInfo);
            if (processName.equals(mPackageName)) {
                mRunningAppProcessInfo = appProcessInfo;
                return memSize / 1024;
            }
        }

        return -1;
    }

    private double getMemSize(RunningAppProcessInfo appProcessInfo) {
        // 进程 ID 号
        int pid = appProcessInfo.pid;
        // 获得该进程占用的内存
        int[] memPid = new int[] { pid };
        // 此 MemoryInfo 位于 android.os.Debug.MemoryInfo 包中，用来统计进程的内存信息
        Debug.MemoryInfo[] memoryInfo = mActivityManager.getProcessMemoryInfo(memPid);
        // 获取进程占内存用信息 kb 单位
        return memoryInfo[0].dalvikPrivateDirty;
    }
}
