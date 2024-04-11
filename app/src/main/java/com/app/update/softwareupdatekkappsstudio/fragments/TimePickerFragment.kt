package com.app.update.softwareupdatekkappsstudio.fragments

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.app.update.softwareupdatekkappsstudio.listeners.OnTimeSet
import java.util.*

class TimePickerFragment constructor(private val timeListener: OnTimeSet? = null) : DialogFragment(),
    TimePickerDialog.OnTimeSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        return TimePickerDialog(requireActivity(), this, hour, minute, true)
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        timeListener?.pickedTime(hourOfDay, minute)
    }
}
