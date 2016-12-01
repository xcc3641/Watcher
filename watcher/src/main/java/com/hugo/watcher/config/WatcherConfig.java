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

    /**
     * 位置
     */
    @Seat
    public int seat = Seat.BOTTOM_LEFT;

    @IntDef({
        Seat.TOP_RIGHT, Seat.TOP_LEFT, Seat.TOP_CENTER, Seat.BOTTOM_RIGHT, Seat.BOTTOM_LEFT, Seat.BOTTOM_CENTER,
        Seat.RIGHT_CENTER, Seat.LEFT_CENTER, Seat.CENTER
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Seat {
        int TOP_RIGHT = Gravity.TOP | Gravity.END;
        int TOP_LEFT = Gravity.TOP | Gravity.START;
        int TOP_CENTER = Gravity.TOP | Gravity.CENTER_HORIZONTAL;

        int BOTTOM_RIGHT = Gravity.BOTTOM | Gravity.END;
        int BOTTOM_LEFT = Gravity.BOTTOM | Gravity.START;
        int BOTTOM_CENTER = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;

        int RIGHT_CENTER = Gravity.END | Gravity.CENTER_VERTICAL;
        int LEFT_CENTER = Gravity.START | Gravity.CENTER_VERTICAL;
        int CENTER = Gravity.CENTER;
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
        dest.writeInt(this.seat);
    }

    protected WatcherConfig(Parcel in) {
        this.isDebug = in.readByte() != 0;
        this.enableFps = in.readByte() != 0;
        this.enableMemory = in.readByte() != 0;
        this.seat = in.readInt();
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
