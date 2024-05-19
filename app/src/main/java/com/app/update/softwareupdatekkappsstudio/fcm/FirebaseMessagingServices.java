package com.app.update.softwareupdatekkappsstudio.fcm;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.BuildConfig;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class FirebaseMessagingServices extends FirebaseMessagingService {

    String ICON_KEY = "icon";
    String APP_TITLE_KEY = "title";
    String SHORT_DESC_KEY = "short_desc";
    String LONG_DESC_KEY = "long_desc";
    String APP_FEATURE_KEY = "feature";
    String APP_URL_KEY = "app_url";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...


        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("MessageData", "Message data payload: " + remoteMessage.getData());

        } else {
            Log.d("DataFailed", "Message Notification Body: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getData() != null) {
            Log.d("MessageTitle", Objects.requireNonNull(remoteMessage.getData().get("title")));
            Log.d("MessageDesc", Objects.requireNonNull(remoteMessage.getData().get("short_desc")));

            String iconURL = remoteMessage.getData().get(ICON_KEY);
            String title = remoteMessage.getData().get(APP_TITLE_KEY);
            String shortDesc = remoteMessage.getData().get(SHORT_DESC_KEY);
            String longDesc = remoteMessage.getData().get(LONG_DESC_KEY);
            String feature = remoteMessage.getData().get(APP_FEATURE_KEY);
            String appURL = remoteMessage.getData().get(APP_URL_KEY);

            if (iconURL != null && title != null && shortDesc != null && feature != null && appURL != null) {
                String standard = "https://play.google.com/store/details?id=";

                try {

                    MyNotificationManager.getInstance(getApplicationContext()).displayNotification(
                            title, shortDesc, longDesc,
                            appURL, feature, iconURL, getNotificationID());

                } catch (Exception e) {
                    if (BuildConfig.DEBUG)
                        Log.e("FcmFireBase", "package not valid");
                }

            }
        } else {
            Log.d("MessageFailed", "Message Notification Body: " + remoteMessage.getData());
        }
    }

    private AtomicInteger seed = new AtomicInteger();

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("FCM_Token", "onNewToken: " + s);
    }

    private int getNotificationID() {
        return seed.incrementAndGet();
    }
}
