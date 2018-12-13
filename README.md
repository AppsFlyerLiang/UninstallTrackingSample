# How to track uninstalls

To enable uninstall tracking based on AppsFlyer SDK, the following steps should be done.

### 1. Set up Firebase Cloud Messaging
Go to https://console.firebase.google.com/ to set up firebase for your app.
After the set up, you can see your app is as below
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide14.jpeg)

### 2. Implement Firebase message in application.
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide13.jpeg)

After implementation, check your google-services.json is under your **/app** folder
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide15.jpeg)

Make sure you have implement google services and firebase-messaging
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide16.jpeg)
 

### 3. Implement AppsFlyer SDK
Refer to https://support.appsflyer.com/hc/en-us/articles/207032126-AppsFlyer-SDK-Integration-Android

### 4. Add com.appsflyer.FirebaseInstanceIdListener to AndroidManifest.xml
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide18.jpeg)

### 5. Enable Uninstall Tracking by Sender ID.
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide17.jpeg)

### 6. Put FCM Server key to AppsFlyer's App Setting -> 
![alt text](https://github.com/AppsFlyerLiang/UninstallTrackingSample/blob/master/Slides/Slide19.jpeg)

Build, run, check the logs.
