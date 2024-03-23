package com.app.update.softwareupdatekkappsstudio.info

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.app.update.softwareupdatekkappsstudio.utils.Constants.BOOT_COMPLETED
import com.app.update.softwareupdatekkappsstudio.utils.Constants.FIRE_ALARM
import com.app.update.softwareupdatekkappsstudio.utils.Constants.OPEN_TYPE
import com.app.update.softwareupdatekkappsstudio.utils.Constants.REFRESH
import com.app.update.softwareupdatekkappsstudio.utils.Constants.alarm_id


class AlarmsReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val newIntent = Intent(context, AlarmsForegroundService::class.java)

        if (intent.action == BOOT_COMPLETED) {
            newIntent.putExtra(OPEN_TYPE, REFRESH)
        } else {
            val alarmid = intent.getIntExtra(alarm_id, -1)
            if (alarmid == -1) return
            newIntent.putExtra(OPEN_TYPE, FIRE_ALARM)
            newIntent.putExtra(alarm_id, alarmid)
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            context.startForegroundService(newIntent)
        } else {
            context.startService(newIntent)
        }
    }


}
