package com.hugo.watchersample;

import android.app.Activity;
import android.os.Bundle;
import com.hugo.watcher.Watcher;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Watcher.getInstance().start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Watcher.getInstance().stop(this);
    }
}
