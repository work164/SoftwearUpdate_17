package com.app.update.softwareupdatekkappsstudio

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.provider.Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.app.update.softwareupdatekkappsstudio.ads.MyApp
import com.app.update.softwareupdatekkappsstudio.database.Word
import com.app.update.softwareupdatekkappsstudio.database.WordViewModel
import com.app.update.softwareupdatekkappsstudio.database.WordViewModelFactory
import com.app.update.softwareupdatekkappsstudio.listeners.OnDateSet
import com.app.update.softwareupdatekkappsstudio.listeners.OnTimeSet
import com.app.update.softwareupdatekkappsstudio.model.AlarmData
import com.app.update.softwareupdatekkappsstudio.utils.scheduleAlarm
import com.google.gson.Gson
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    var hour: Int = -1
    var minute: Int = -1
    var year: Int = -1
    var month: Int = -1
    var day: Int = -1
    private var setAlarmButton: AppCompatButton? = null
    var dateButton: TextView? = null
    var timeButton: TextView? = null
    private var alarmNameEdiText: TextView? = null
    private var isRepeatedCheckBox: CheckBox? = null

    val resultLauncher =
        registerForActivityResult(StartActivityForResult()) { result ->
            Log.d(TAG, "Request permission: " + result.resultCode)
        }
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((application as MyApp).repository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setAlarmButton = findViewById(R.id.setAlarmButton)
        dateButton = findViewById(R.id.dateButton)
        timeButton = findViewById(R.id.timeButton)
        alarmNameEdiText = findViewById(R.id.alarmNameEdiText)
        isRepeatedCheckBox = findViewById(R.id.isRepeatedCheckBox)
        setAlarmButton?.setOnClickListener {

            val word = Word("reply")
            wordViewModel.insert(word)

          //  setAlarm()
        }
        //show dialog for request work in background and over lay
        requestAlertPermission()

        wordViewModel.allWords.observe(this) { words ->
            // Update the cached copy of the words in the adapter.
            Log.d("working","  $words")

        }


    }

    fun showDatePickerDialog(v: View) {
        DatePickerFragment(object : OnDateSet {
            override fun pickedDate(year: Int, month: Int, day: Int) {
                this@MainActivity.day = day
                this@MainActivity.month = month
                this@MainActivity.year = year
                dateButton?.text = "${year}/${month}/${day}"
            }
        }).show(supportFragmentManager, "datePicker")
    }

    fun showTimePickerDialog(v: View) {
        TimePickerFragment(object : OnTimeSet {
            override fun pickedTime(hoursOfDay: Int, minutes: Int) {
                this@MainActivity.hour = hoursOfDay
                this@MainActivity.minute = minutes
                timeButton?.text = "$hoursOfDay : $minutes"
            }
        }).show(supportFragmentManager, "timePicker")
    }

    private fun setAlarm() {

        if (hour == -1 || minute == -1 || year == -1 || month == -1 || day == -1) {
            Toast.makeText(this, "pick date and time yal", Toast.LENGTH_LONG).show()
            return
        }

        val newCalendar = Calendar.getInstance()
        newCalendar[Calendar.YEAR] = year
        newCalendar[Calendar.MONTH] = month
        newCalendar[Calendar.DAY_OF_MONTH] = day
        newCalendar[Calendar.HOUR_OF_DAY] = hour
        newCalendar[Calendar.MINUTE] = minute
        newCalendar[Calendar.SECOND] = 0
        newCalendar[Calendar.MILLISECOND] = 0

        val alarm = AlarmData(
            alarm_id = 52,
            time = newCalendar.time.time,
            repeated = isRepeatedCheckBox?.isChecked?:return,
            name = alarmNameEdiText?.text.toString()
        )

        val sharedPref = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE
        )

        sharedPref.edit().putString("alarm_details", Gson().toJson(alarm)).apply()

        scheduleAlarm(this, alarm)
    }

    private fun requestAlertPermission() {
        // check if we already  have permission to draw over other apps
        if (!Settings.canDrawOverlays(this)) {
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Play In background")
            alert.setMessage("Please allow the app to run in the background so the app functions work properly.")
            alert.setPositiveButton("Ok") { _: DialogInterface?, _: Int ->
                requestDrawOverlay()
            }
            alert.setNegativeButton("Cancel") { dialog: DialogInterface, _: Int -> finish() }
            alert.show()
        }
        requestBatteryOptimizationPermission()

    }

    private fun requestDrawOverlay() {
        // if not construct intent to request permission
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:" + applicationContext.packageName)
        )
        resultLauncher.launch(intent)
    }

    @SuppressLint("BatteryLife")
    private fun requestBatteryOptimizationPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.data = Uri.parse("package:" + this.applicationContext.packageName)
            checkIntentAndStart(this, intent)
        } else {
            val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
            if (checkIntentAndStart(this, intent))
                Toast.makeText(
                    this,
                    "Please enable battery optimizations switch",
                    Toast.LENGTH_LONG
                ).show()
        }
    }


    private fun checkIntentAndStart(context: Context, intent: Intent): Boolean {
        intent.resolveActivity(context.packageManager)?.let {
            context.startActivity(intent)
            return true
        }

        return false
    }




}


