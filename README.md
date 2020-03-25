# How to track uninstalls

To enable uninstall tracking based on AppsFlyer SDK, the following steps should be done.

## 1. Set up Firebase Cloud Messaging

**Approach 1**: Use Android Studio-> Tools -> Firebase and follow the guide.

![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/FromAndroid.png)

**Approach 2**: You can also open https://console.firebase.google.com/ to set up firebase manually for your app.

After the set up, you can see your app is as below

![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide14.jpeg)

## 2. Implement Firebase message in application.
Now we have the app added on Firebase Console, meaning we can send push notification from the Console or any other server using the Server Key.
Next we need make the application able to receive push notification.

If you implement FCM by Android Studio(Tools -> Firebase), you will have everything done.
and you must check:
_google-services.json_ is under the **/app** folder, if not, download it from Firebase Console.
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide15.jpeg)

Dependencies are added.

_<root>/build.gradle_
```groovy
buildscript {
    dependencies {
        classpath 'com.android.tools.build:gradle:3.6.1'
        classpath 'com.google.gms:google-services:4.3.3'
    }
}
```

_<root>/app/build.gradle_
```groovy
dependencies {
    ...
    implementation 'com.google.firebase:firebase-messaging:20.1.3'

}
apply plugin: 'com.google.gms.google-services'
```

_<root>/app/proguard-rules.pro_
```proguard
-keep public class com.google.firebase.messaging.FirebaseMessagingService {
  public *;
}
```

### 3. Implement AppsFlyer SDK

_<root>/build.gradle_
```groovy
allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
}
```

_<root>/app/build.gradle_
```groovy
dependencies {
    implementation 'com.appsflyer:af-android-sdk:5.2.0@aar'
}
```

_<root>/app/proguard-rules.pro_
```proguard
-keep class com.appsflyer.** { *; }
-dontwarn com.appsflyer.**
```

_<root>/app/src/main/Path/To/MyApplication.java_
```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppsFlyerLib.getInstance().init("<YourAppsFlyerDevKey>", implAppsFlyerConversionListener, this);
        AppsFlyerLib.getInstance().startTracking(this);
    }
}
```

_<root>AndroidManifest.xml_
```xml
    <application
        android:name=".MyApplication"
        android:allowBackup="false"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        ...
    </application>
```

For more details, Refer to https://support.appsflyer.com/hc/en-us/articles/207032126-AppsFlyer-SDK-Integration-Android

### 4. Add com.appsflyer.FirebaseInstanceIdListener to AndroidManifest.xml
#### 4-A. If first use Firebase for AppsFlyer uninstall measurement

    If a developer integrates FCM for the sole purpose of measurement uninstalls with AppsFlyer, they can make use of the appsFlyer.FirebaseMessagingServiceListener service, included in our SDK.
    This is done by adding the service to the AndroidManifest.xml:

_<root>AndroidManifest.xml_
```xml
      <service android:name="com.appsflyer.FirebaseMessagingServiceListener">
        <intent-filter>
          <action android:name="com.google.firebase.MESSAGING_EVENT"/>
        </intent-filter>
      </service>
```
#### 4-B. If app is already using Firebase Messaging Service

_<root>/Path/To/YourFirebaseMessagingService_
```java
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
             // handleNotification(remoteMessage);
        }
    }
}

```


### 6. Put FCM Server key to AppsFlyer's App Setting -> 
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide19.jpeg)

Build, run, check the logs.
