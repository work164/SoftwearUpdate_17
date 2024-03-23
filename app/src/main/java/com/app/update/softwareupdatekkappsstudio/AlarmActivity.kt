package com.app.update.softwareupdatekkappsstudio

import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.app.update.softwareupdatekkappsstudio.model.AlarmData
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.utils.NotificationHelper.cancelNotification
import com.app.update.softwareupdatekkappsstudio.utils.NotificationHelper.runNotification
import java.util.*


class AlarmActivity : AppCompatActivity() {

    private lateinit var wakeLock: PowerManager.WakeLock
    private var alarm_title : TextView? = null
    private var closeBtn : ImageView? = null
    private var remain_to_stop : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wakeLock =
            (getSystemService(Context.POWER_SERVICE) as PowerManager).run {
                newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Update::Alarm").apply {
                    acquire()
                }
            }


        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON,
            (WindowManager.LayoutParams.FLAG_FULLSCREEN or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        )

        setContentView(R.layout.activity_alarm)

        closeBtn = findViewById(R.id.closeBtn)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val alarmData = intent.getParcelableExtra<AlarmData>(Constants.ALARM_DATA) as AlarmData
        alarm_title?.text = alarmData.name
        runNotification(this, alarmData.name, alarmData.alarm_id)
        closeBtn?.setOnClickListener {
         //   cancelNotification()
            finish()
        }

        val timer = Timer()
        var remainingSec = 30
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    remainingSec -= 1
                    remain_to_stop?.text = remainingSec.toString()
                    if (remainingSec == 0) {
                        timer.cancel()
                        finish()
                    }

                }
            }
        }, 1000, 1000)

    }

    override fun onDestroy() {
        wakeLock.release()
        super.onDestroy()
    }


}