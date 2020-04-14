package vn.cns.covid19;

import android.app.Application;

import vn.cns.covid19.Utils.PreferencesUtils;

public class DeviceApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferencesUtils.initInstance(getApplicationContext());
    }
}
