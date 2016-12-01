package com.hugo.watcher.config;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import android.view.Gravity;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class WatcherConfig implements Parcelable {

    public static final String CONFIG_KEY = "config_key";

    /**
     * 状态
     */
    public boolean isDebug = true;
    /**
     * 开启 fps
     */
    public boolean enableFps = true;

    /**
     * 开启内存
     */
    public boolean enableMemory = true;

    @IntDef({
        Seat.TOP_RIGHT
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Seat {
        int TOP_RIGHT = Gravity.TOP | Gravity.END;
    }

    public WatcherConfig() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.isDebug ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enableFps ? (byte) 1 : (byte) 0);
        dest.writeByte(this.enableMemory ? (byte) 1 : (byte) 0);
    }

    protected WatcherConfig(Parcel in) {
        this.isDebug = in.readByte() != 0;
        this.enableFps = in.readByte() != 0;
        this.enableMemory = in.readByte() != 0;
    }

    public static final Creator<WatcherConfig> CREATOR = new Creator<WatcherConfig>() {
        @Override
        public WatcherConfig createFromParcel(Parcel source) {
            return new WatcherConfig(source);
        }

        @Override
        public WatcherConfig[] newArray(int size) {
            return new WatcherConfig[size];
        }
    };
}
