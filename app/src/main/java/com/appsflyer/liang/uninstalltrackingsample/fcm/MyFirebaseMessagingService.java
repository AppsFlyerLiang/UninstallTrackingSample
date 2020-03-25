package com.appsflyer.liang.uninstalltrackingsample.fcm;


import androidx.annotation.NonNull;

import com.appsflyer.AppsFlyerLib;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        // Sending new token to AppsFlyer
        AppsFlyerLib.getInstance().updateServerUninstallToken(getApplicationContext(), s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().containsKey("af-uinstall-tracking")){
            return;
        } else {
             handleNotification(remoteMessage);
        }
    }

    private void handleNotification(RemoteMessage remoteMessage) {
        //Handle your push notification
    }
}
