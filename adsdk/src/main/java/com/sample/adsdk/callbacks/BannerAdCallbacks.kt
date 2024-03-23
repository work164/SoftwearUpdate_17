package com.sample.adsdk.callbacks

interface BannerAdCallbacks {
    fun onAdClicked()
    fun onAdFailedToLoad(message:String)
    fun onAdImpression()
    fun onAdLoaded()
    fun onAdOpened()
    fun onAdClosed(){}
}