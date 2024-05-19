package com.app.update.softwareupdatekkappsstudio.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.app.update.softwareupdatekkappsstudio.HomeActivity;
import com.app.update.softwareupdatekkappsstudio.MainActivity;
import com.app.update.softwareupdatekkappsstudio.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MyNotificationManager {

    private static MyNotificationManager myNotificationManager;
    private Context mContext;

    public MyNotificationManager(Context mContext) {
        this.mContext = mContext;
    }

    public static synchronized MyNotificationManager getInstance(Context mContext) {
        if (myNotificationManager == null) {
            myNotificationManager = new MyNotificationManager(mContext);
        }
        return myNotificationManager;
    }

    public void displayNotification(String title, String short_desc, String long_desc, String appUrl, String feature, String iconURL, int notificationID) {
        Intent toAppURL;

        if (appUrl.contains("https")) {
            toAppURL = new Intent(Intent.ACTION_VIEW, Uri.parse(appUrl));
        } else {
            toAppURL = new Intent(mContext, MainActivity.class);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, toAppURL, PendingIntent.FLAG_IMMUTABLE);

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.fcm_notification);
        remoteViews.setTextViewText(R.id.tv_title, title);
        remoteViews.setTextViewText(R.id.fcm_short_dis, short_desc);
        remoteViews.setTextViewText(R.id.tv_fcm_long_dis, long_desc);

        if (long_desc != null && !long_desc.isEmpty())
            remoteViews.setViewVisibility(R.id.tv_fcm_long_dis, View.VISIBLE);
        else
            remoteViews.setViewVisibility(R.id.tv_fcm_long_dis, View.GONE);
        try {

            Bitmap bmp = Picasso.get().load(iconURL).get();
            Bitmap bmpfeature = Picasso.get().load(feature).get();
            remoteViews.setImageViewBitmap(R.id.fcm_icon, bmp);
            remoteViews.setImageViewBitmap(R.id.iv_fcm_feature, bmpfeature);
        } catch (IOException e) {
            Log.d("MessageData", "exception");
            e.printStackTrace();
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mContext, "ID_DATA_SHARE")
                .setContentTitle(title)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setSmallIcon(R.drawable.ic_android_icon)
                .setContentIntent(pendingIntent)
                .setOnlyAlertOnce(true)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews);

        NotificationManager mNotificationManager = (NotificationManager) this.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        Log.d("MessageData", "exception");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("ID_DATA_SHARE",
                    "Unseen", NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId("ID_DATA_SHARE");
        }

        mNotificationManager.notify(notificationID, mBuilder.build());


    }


}
