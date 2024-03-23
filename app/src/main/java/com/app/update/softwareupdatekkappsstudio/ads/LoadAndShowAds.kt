package com.app.update.softwareupdatekkappsstudio.ads

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.app.update.softwareupdatekkappsstudio.R

import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.sample.adsdk.Ads
import com.sample.adsdk.callbacks.BannerAdCallbacks
import com.sample.adsdk.intertesialAds.AdMobFullScreenListener
import com.sample.adsdk.intertesialAds.FullScreenAdListener
import com.sample.adsdk.native_ad.CustomNativeAd
import com.sample.adsdk.native_ad.NativeCallback

object LoadAndShowAds {
    @JvmStatic
    fun Activity.loadFullScreenHigh(
        idHigh: String,
        idMed: String,
        idLow: String,
        remoteHigh: Boolean,
        remoteMed: Boolean,
        remoteLow: Boolean
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idHigh)
            .loadFullScreenAd(object : FullScreenAdListener {
                override fun adAlreadyLoaded() {

                }

                override fun adLoaded() {

                }

                override fun adFailed() {
                    Log.d("fullscreenWork", "adFailsFullHigh")
                    loadFullScreenMed(idMed, remoteMed, idLow, remoteLow)
                }

                override fun adValidate() {
                    Log.d("fullscreenWork", "adValidateFullHigh")

                    loadFullScreenMed(idMed, remoteMed, idLow, remoteLow)
                    super.adValidate()
                }
            }, remoteHigh)
    }

    @JvmStatic

    fun Activity.loadFullScreenMed(
        idMed: String,
        remoteMed: Boolean,
        idLow: String,
        remoteLow: Boolean
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idMed)
            .loadFullScreenAd(object : FullScreenAdListener {
                override fun adAlreadyLoaded() {

                }

                override fun adLoaded() {

                }

                override fun adFailed() {
                    Log.d("fullscreenWork", "adFailFullMid")

//                    loadFullScreenLow(idLow, remoteLow)
                }

                override fun adValidate() {
                    Log.d("fullscreenWork", "adValidateFullMid")
//                    loadFullScreenLow(idLow, remoteLow)
                    super.adValidate()
                }
            }, remoteMed)
    }

    @JvmStatic
    fun Activity.loadFullScreenLow(
        idLow: String,
        remoteLow: Boolean,
        fullScreenListener: FullScreenAdListener? = null
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idLow)
            .loadFullScreenAd(fullScreenListener ?: object : FullScreenAdListener {
                override fun adAlreadyLoaded() {

                }

                override fun adLoaded() {

                }

                override fun adFailed() {
                    Log.d("fullscreenWork", "adFailedFullLow")

                }

                override fun adValidate() {
                    Log.d("fullscreenWork", "adValidateFullLow")

                    super.adValidate()
                }
            }, remoteLow)
    }

    @JvmStatic
    fun Activity.showFullScreenHigh(
        idHigh: String,
        idMed: String,
        idLow: String,
        remoteHigh: Boolean,
        remoteMed: Boolean,
        remoteLow: Boolean,
        adMobFullScreenListener: AdMobFullScreenListener
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idHigh)
            .showAndLoad(object : AdMobFullScreenListener {
                override fun fullScreenAdShow() {

                }

                override fun fullScreenAdDismissed() {
                    adMobFullScreenListener.fullScreenAdDismissed()

                }

                override fun fullScreenAdFailedToShow() {
                    adMobFullScreenListener.fullScreenAdFailedToShow()
                    showFullScreenMed(idLow, idMed, remoteLow, remoteMed, adMobFullScreenListener)

                }

                override fun fullScreenAdNotAvailable() {
                    adMobFullScreenListener.fullScreenAdNotAvailable()
                    showFullScreenMed(idLow, idMed, remoteLow, remoteMed, adMobFullScreenListener)
                }
            }, object : FullScreenAdListener {}, remoteHigh, 0)
    }


    @JvmStatic
    fun Activity.showFullScreenMed(
        idLow: String,
        idMed: String,
        remoteMed: Boolean,
        remoteLow: Boolean,
        adMobFullScreenListener: AdMobFullScreenListener
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idMed)
            .showAndLoad(object : AdMobFullScreenListener {
                override fun fullScreenAdShow() {

                }

                override fun fullScreenAdDismissed() {
                    adMobFullScreenListener.fullScreenAdDismissed()
                }

                override fun fullScreenAdFailedToShow() {
                    showFullScreenLow(idLow, remoteLow, adMobFullScreenListener)
                    adMobFullScreenListener.fullScreenAdFailedToShow()

                }

                override fun fullScreenAdNotAvailable() {
                    showFullScreenLow(idLow, remoteLow, adMobFullScreenListener)
                    adMobFullScreenListener.fullScreenAdNotAvailable()

                }
            }, object : FullScreenAdListener {}, remoteMed, 0)
    }

    @JvmStatic
    fun Activity.showFullScreenLow(
        idLow: String,
        remoteLow: Boolean,
        adMobFullScreenListener: AdMobFullScreenListener
    ) {
        Ads(this, getString(R.string.env_dev).toBoolean()).fullScreenAd(idLow)
            .showAndLoad(object : AdMobFullScreenListener {
                override fun fullScreenAdShow() {
                    adMobFullScreenListener.fullScreenAdShow()
                }

                override fun fullScreenAdDismissed() {
                    adMobFullScreenListener.fullScreenAdDismissed()
                }

                override fun fullScreenAdFailedToShow() {
                    adMobFullScreenListener.fullScreenAdFailedToShow()

                }

                override fun fullScreenAdNotAvailable() {
                    adMobFullScreenListener.fullScreenAdNotAvailable()

                }
            }, object : FullScreenAdListener {}, remoteLow, 0)
    }

    @JvmStatic
    fun Activity.loadNativeHigh(
        view: ViewGroup,
        layout: Int,
        idHigh: String,
        idMid: String,
        idLow: String,
        remoteMid: Boolean,
        remoteHigh: Boolean,
        remoteLow: Boolean
    ) {
        val adView = layoutInflater.inflate(layout, null) as NativeAdView
        CustomNativeAd(
            this,
            adView,
            idHigh,
            remoteHigh,
            view,
            getString(R.string.env_dev).toBoolean()
        ).populateNativeAdMedia(
            R.id.ad_app_icon,
            R.id.ad_headline,
            R.id.ad_body,
            R.id.ad_call_to_action,
            R.id.ad_media,
            object : NativeCallback {
                override fun nativeAdCallLoaded(currentNativeAd: NativeAd?) {

                }

                override fun nativeAdCallFailed(loadAdError: LoadAdError) {
                    view.removeAllViews()
                    view.visibility = View.VISIBLE
                    loadNativeMid(view, idLow, remoteLow, idMid, remoteMid)

                }

                override fun nativeAdCallValidate(string: String) {
                    loadNativeMid(view, idLow, remoteLow, idMid, remoteMid)
                }

            }
        )
    }


    @JvmStatic

    fun Activity.loadNativeMid(
        view: ViewGroup,
        idLow: String,
        remoteLow: Boolean,
        idMid: String,
        remoteMid: Boolean
    ) {
        val adView = layoutInflater.inflate(R.layout.native_ads_main_video, null) as NativeAdView
        CustomNativeAd(
            this,
            adView,
            idMid,
            remoteMid,
            view,
            getString(R.string.env_dev).toBoolean()
        ).populateNativeAdMedia(
            R.id.ad_app_icon,
            R.id.ad_headline,
            R.id.ad_body,
            R.id.ad_call_to_action,
            R.id.ad_media,
            object : NativeCallback {
                override fun nativeAdCallLoaded(currentNativeAd: NativeAd?) {

                }

                override fun nativeAdCallFailed(loadAdError: LoadAdError) {
                    view.removeAllViews()
                    view.visibility = View.VISIBLE
                    loadNativeLow(view, idLow, remoteLow)
                }

                override fun nativeAdCallValidate(string: String) {
                    loadNativeLow(view, idLow, remoteLow)

                }

            }
        )
    }

    @JvmStatic
    fun Activity.loadNativeLow(
        view: ViewGroup,
        idLow: String,
        remoteLow: Boolean
    ) {
        val adView = layoutInflater.inflate(R.layout.native_ads_main_video, null) as NativeAdView
        CustomNativeAd(
            this,
            adView,
            idLow,
            remoteLow,
            view,
            getString(R.string.env_dev).toBoolean()
        ).populateNativeAdMedia(
            R.id.ad_app_icon,
            R.id.ad_headline,
            R.id.ad_body,
            R.id.ad_call_to_action,
            R.id.ad_media,
            object : NativeCallback {
                override fun nativeAdCallLoaded(currentNativeAd: NativeAd?) {

                }

                override fun nativeAdCallFailed(loadAdError: LoadAdError) {

                }

                override fun nativeAdCallValidate(string: String) {

                }

            }
        )
    }


    @JvmStatic
    fun Activity.loadNativeLowWithOutMedia(
        view: ViewGroup,
        idLow: String,
        remoteLow: Boolean
    ) {
        val adView = layoutInflater.inflate(R.layout.native_with_out_media, null) as NativeAdView
        CustomNativeAd(
            this,
            adView,
            idLow,
            remoteLow,
            view,
            getString(R.string.env_dev).toBoolean()
        ).populateNativeWithOutMediaAd(
            R.id.ad_app_icon,
            R.id.ad_headline,
            R.id.ad_body,
            R.id.ad_call_to_action,
            object : NativeCallback {
                override fun nativeAdCallLoaded(currentNativeAd: NativeAd?) {

                }

                override fun nativeAdCallFailed(loadAdError: LoadAdError) {

                }

                override fun nativeAdCallValidate(string: String) {

                }

            }
        )
    }


    @JvmStatic

    fun Activity.loadBannerHigh(
        view: ViewGroup,
        adSize: String,
        idHigh: String,
        idMid: String,
        idLow: String,
        remoteHigh: Boolean,
        remoteMid: Boolean,
        remoteLow: Boolean
    ) {
        if (remoteHigh) {
            Ads(this, getString(R.string.env_dev).toBoolean()).adsBanners(
                view,
                adSize,
                idHigh,
                remoteHigh
            )
                .loadBanner(object : BannerAdCallbacks {
                    override fun onAdClicked() {

                    }

                    override fun onAdFailedToLoad(message: String) {
                        view.removeAllViews()

                        loadBannerMid(view, adSize, idMid, idLow, remoteMid, remoteLow)
                    }

                    override fun onAdImpression() {

                    }

                    override fun onAdLoaded() {

                    }

                    override fun onAdOpened() {

                    }

                })

        } else {
            loadBannerMid(view, adSize, idMid, idLow, remoteMid, remoteLow)

        }


    }

    @JvmStatic
    fun Activity.loadBannerMid(
        view: ViewGroup,
        adSize: String,
        idMid: String,
        idLow: String,
        remoteMid: Boolean,
        remoteLow: Boolean
    ) {

        if (remoteMid) {

            Ads(this, getString(R.string.env_dev).toBoolean()).adsBanners(
                view,
                adSize,
                idMid,
                remoteMid
            )
                .loadBanner(object : BannerAdCallbacks {
                    override fun onAdClicked() {

                    }

                    override fun onAdFailedToLoad(message: String) {
                        view.removeAllViews()
                        loadBannerLow(view, adSize, idLow, remoteLow)
                    }

                    override fun onAdImpression() {

                    }

                    override fun onAdLoaded() {

                    }

                    override fun onAdOpened() {

                    }

                })
        } else {
            loadBannerLow(view, adSize, idLow, remoteLow)
        }


    }

    @JvmStatic

    fun Activity.loadBannerLow(
        view: ViewGroup,
        adSize: String,
        idLow: String,
        remoteLow: Boolean
    ) {

        Ads(this, getString(R.string.env_dev).toBoolean()).adsBanners(
            view,
            adSize,
            idLow,
            remoteLow
        )
            .loadBanner(object : BannerAdCallbacks {
                override fun onAdClicked() {

                }

                override fun onAdFailedToLoad(message: String) {

                }

                override fun onAdImpression() {

                }

                override fun onAdLoaded() {

                }

                override fun onAdOpened() {

                }

            })


    }


    @JvmStatic

    fun View.gone() {
        visibility = View.GONE
    }

    @JvmStatic

    fun View.invisible() {
        visibility = View.INVISIBLE
    }

    @JvmStatic

    fun View.visible() {
        visibility = View.VISIBLE
    }

    @JvmStatic

    fun loge(message: String) {
        try {
            Log.e("atiqTest", "$message")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var val_full_screen_splash_h = true
    var val_full_screen_splash_m = true
    var val_full_screen_splash_l = true

    var val_app_open_splash = true
    var val_banner_collaps = true
    var val_native_exit = true
    var val_full_screen = true
    var val_full_screen_3trans = true
}




