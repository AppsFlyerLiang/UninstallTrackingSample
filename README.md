# How to track uninstalls

To enable uninstall tracking based on AppsFlyer SDK, the following steps should be done.

### 1. Set up Firebase Cloud Messaging
Go to https://console.firebase.google.com/ to set up firebase for your app.
You can also use Android Studio-> Tools -> Firebase and follow the guide.
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/FromAndroid.png)

After the set up, you can see your app is as below
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide14.jpeg)

### 2. Implement Firebase message in application.
Now you have your app on Firebase Console, meaning you can send push notification from the Console or any other server using the Server Key.
Next we need make the application able to receive push notification.

If you implement FCM by Android Studio(Tools -> Firebase), you will have everything done.
and you must check:
google-services.json is under the **/app** folder, if not, download it from Firebase Console.
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide15.jpeg)

Dependencies are added.

buildscript {
    dependencies {
        classpath 'com.android.tools.build:gradle:3.2.1'
        classpath 'com.google.gms:google-services:4.2.0'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

dependencies {
    ...
    implementation 'com.google.firebase:firebase-messaging:17.3.4'

}
apply plugin: 'com.google.gms.google-services'


### 3. Implement AppsFlyer SDK
<root>/build.gradle
allprojects {
    repositories {
        mavenCentral()
        google()
        jcenter()
    }
}
<root>/app/build.gradle
dependencies {
    ...
    implementation 'com.appsflyer:af-android-sdk:4.8.18@aar'
    implementation 'com.android.installreferrer:installreferrer:1.0'
}

<root>/app/src/main/Path/To/MyApplication.java
        AppsFlyerLib.getInstance().init(BuildConfig.AF_DEV_KEY, new SimpleAppsFlyerConversionListener(), this);
        AppsFlyerLib.getInstance().startTracking(this);

For more details, Refer to https://support.appsflyer.com/hc/en-us/articles/207032126-AppsFlyer-SDK-Integration-Android

### 4. Add com.appsflyer.FirebaseInstanceIdListener to AndroidManifest.xml

        <service android:name="com.appsflyer.FirebaseInstanceIdListener">
            <intent-filter>
                <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
            </intent-filter>
        </service>


### 5. Enable Uninstall Tracking by Sender ID.

        AppsFlyerLib.getInstance().init(BuildConfig.AF_DEV_KEY, new SimpleAppsFlyerConversionListener(), this);
        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().enableUninstallTracking(BuildConfig.SENDER_ID);
        AppsFlyerLib.getInstance().startTracking(this);


### 6. Put FCM Server key to AppsFlyer's App Setting -> 
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide19.jpeg)

Build, run, check the logs.
