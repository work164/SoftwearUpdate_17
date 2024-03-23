package com.sample.adsdk

import android.app.Activity
import android.app.Application
import android.view.ViewGroup
import com.sample.adsdk.banner_ads.BannerAds
import com.sample.adsdk.intertesialAds.FullScreenAds
import com.sample.adsdk.native_ad.LoadNativeAd
import com.sample.adsdk.open_app_ad.OpenAppAd
import com.sample.adsdk.open_app_ad.OpenAppAdForSplash

class Ads(val activity: Activity, val buildType: Boolean) {

    fun adsBanners(view: ViewGroup, adSize: String, adId: String, remoteValue: Boolean): BannerAds {
        return BannerAds(activity, view, adId, adSize, buildType, remoteValue)
    }

    fun fullScreenAd(adId: String): FullScreenAds {
        return FullScreenAds(activity, adId, buildType)
    }

    fun loadNativeAd(adId: String, remoteConfig: String, frameLayout :ViewGroup): LoadNativeAd {
        return LoadNativeAd(activity, adId, remoteConfig.toBoolean(), buildType,frameLayout)
    }

    fun openAppAdSplash(remoteConfig: String, adId: String): OpenAppAdForSplash {
        return OpenAppAdForSplash(activity, remoteValue = remoteConfig.toBoolean(), adId, buildType)
    }

    fun openAppAd(application: Application, remoteConfig: Boolean,  openAdIdH: String, openAdIdM: String, openAdIdL: String): OpenAppAd {
        return OpenAppAd(application,openAdIdL, buildType)
    }
}