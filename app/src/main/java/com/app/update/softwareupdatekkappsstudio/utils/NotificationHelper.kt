package com.app.update.softwareupdatekkappsstudio.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.app.update.softwareupdatekkappsstudio.MainActivity
import com.app.update.softwareupdatekkappsstudio.R


object NotificationHelper {
    private var NotificationID = 1005
    private var notification: Notification? = null
    private var notificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null

    fun runNotification(context: Context, body: String?, alarmID: Int) {
        NotificationID = alarmID
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        mBuilder = NotificationCompat.Builder(context.applicationContext, "notify_001")
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        mBuilder?.setSmallIcon(R.mipmap.ic_launcher)
        mBuilder?.setContentTitle(body)
        mBuilder?.setAutoCancel(true)
        mBuilder?.setOngoing(false)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        mBuilder?.setSound(uri)
        mBuilder?.priority = Notification.PRIORITY_HIGH
        mBuilder?.setOnlyAlertOnce(true)
        mBuilder?.build()?.flags = Notification.FLAG_NO_CLEAR or Notification.PRIORITY_HIGH
        mBuilder?.setContentIntent(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "update_apps"
            val channel =
                NotificationChannel(channelId, "Update Apps", NotificationManager.IMPORTANCE_HIGH)
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            notificationManager?.createNotificationChannel(channel)
            mBuilder?.setChannelId(channelId)
        }
        notification = mBuilder?.build()
        notificationManager?.notify(NotificationID, notification)
    }

    fun cancelNotification() {
        notificationManager?.cancel(NotificationID)
    }
}