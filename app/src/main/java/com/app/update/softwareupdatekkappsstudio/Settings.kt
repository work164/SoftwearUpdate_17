package com.app.update.softwareupdatekkappsstudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.update.softwareupdatekkappsstudio.databinding.ActivitySettingsBinding

class Settings : AppCompatActivity() {

    private var settingsBinding:ActivitySettingsBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        settingsBinding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(settingsBinding?.root)



    }
}