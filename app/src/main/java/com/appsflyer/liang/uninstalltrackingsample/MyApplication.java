package com.appsflyer.liang.uninstalltrackingsample;

import android.app.Application;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppsFlyerLib.getInstance().init(BuildConfig.AF_DEV_KEY, new SimpleAppsFlyerConversionListener(), this);
        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().enableUninstallTracking("485765251553");
        AppsFlyerLib.getInstance().startTracking(this);
    }
}
