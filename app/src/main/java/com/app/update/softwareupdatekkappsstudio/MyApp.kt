package com.app.update.softwareupdatekkappsstudio

import android.app.Application
import android.util.Log
import com.app.update.softwareupdatekkappsstudio.database.WordRepository
import com.app.update.softwareupdatekkappsstudio.database.WordRoomDatabase
import com.example.adssdk.Ads
import com.intuit.sdp.BuildConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyApp : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { WordRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { WordRepository(database.wordDao()) }
    override fun onCreate() {
        super.onCreate()
Ads(applicationContext?:return,false, listOf(""), BuildConfig.BUILD_TYPE,"appProd")
        Log.d("interstitial_even_ad", "onActivityCreated: ")
    }

    companion object {
        @JvmField
        var startInter = false

        @JvmField
        var appopen = true
    }
}