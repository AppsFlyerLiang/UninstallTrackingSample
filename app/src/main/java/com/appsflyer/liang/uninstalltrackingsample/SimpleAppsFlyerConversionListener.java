package com.appsflyer.liang.uninstalltrackingsample;

import com.appsflyer.AppsFlyerConversionListener;

import java.util.Map;

public class SimpleAppsFlyerConversionListener implements AppsFlyerConversionListener {
    @Override
    public void onConversionDataSuccess(Map<String, Object> map) {
    }

    @Override
    public void onConversionDataFail(String s) {
    }

    @Override
    public void onAppOpenAttribution(Map<String, String> map) {
    }

    @Override
    public void onAttributionFailure(String s) {
    }
}
