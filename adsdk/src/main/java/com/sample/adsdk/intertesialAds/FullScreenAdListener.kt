package com.sample.adsdk.intertesialAds

interface FullScreenAdListener {
    fun adLoaded(){}
    fun adAlreadyLoaded(){}
    fun adFailed(){}
    fun adValidate(){}
}