package com.by5388.demo.preference;

import android.app.Application;
import android.os.StrictMode;

/**
 * @author Administrator  on 2019/9/26.
 */
public class PreferenceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.enableDefaults();
    }
}
