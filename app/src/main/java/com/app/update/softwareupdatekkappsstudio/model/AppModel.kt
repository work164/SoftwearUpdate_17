package com.app.update.softwareupdatekkappsstudio.model

import android.graphics.drawable.Drawable

data class AppModel(
    var appName: String,
    val appSize: String,
    val versionName: String,
    val icon: Drawable,
    var packageName: String
)