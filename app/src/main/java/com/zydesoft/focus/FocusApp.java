package com.zydesoft.focus;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class FocusApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }


}
