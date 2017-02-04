package com.example.whr.photoalbum;

import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by whr on 17-2-4.
 */

public class MyApplication extends Application {
    public static int windowWidth;
    @Override
    public void onCreate() {
        super.onCreate();
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(dm);
        windowWidth = dm.widthPixels;
    }
}
