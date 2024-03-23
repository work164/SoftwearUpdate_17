package com.app.update.softwareupdatekkappsstudio.ads

import androidx.annotation.Keep

var remoteData: RemoteConfigModel? = null

@Keep
data class RemoteConfigModel(
    var app_open_l: Boolean? = true,
    var admob_banner_id: Boolean? = true,
    var admob_splashinterstitial: Boolean? = true,
    var admob_interal: Boolean? = true
    )