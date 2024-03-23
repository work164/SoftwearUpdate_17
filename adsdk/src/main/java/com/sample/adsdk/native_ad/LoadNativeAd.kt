package com.sample.adsdk.native_ad

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.sample.adsdk.billing.GoogleBillingProduct
import com.sample.adsdk.callbacks.NativeListener
import com.sample.adsdk.constants.Constant
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.VideoOptions
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.material.snackbar.Snackbar

class LoadNativeAd(
    val activity: Activity,
    private val nativeAdId: String,
    val config:Boolean,
    buildType:Boolean,
    val frameLayout: ViewGroup,
    ) {
    private val nativeLogs = "fullNative"
    init {
        val regex = Regex(Constant.regexPattern)
        val isValid = regex.matches(nativeAdId)
        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            Constant.isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            !Constant.isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            Constant.isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
            !Constant.isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }
    }
    //@Deprecated("This one is deprecated in our SDK...")
    fun loadNativeAd(nativeListener: NativeListener) {
        if (Constant.isNetworkAvailable(activity) && !GoogleBillingProduct(
                activity,
                object : GoogleBillingProduct.SuccessPurchase {
                    override fun onSuccessPurchase() {
                    }

                }).checkPurchased(activity) && config
        ) {

            if (Constant.isNativeLoading) {
                Log.d(nativeLogs, "  Native Already loading Ad")
                return
            }
            if (Constant.currentNativeAd != null && Constant.nativeCounter == 0) {
                nativeListener.nativeAdLoaded(Constant.currentNativeAd)
                Constant.nativeCounter += 1
                Log.d(nativeLogs, " Native  Having loaded Ad")
                return
            } else {
                Constant.nativeCounter = 0
            }

            Constant.isNativeLoading = true
            val builder = AdLoader.Builder(
                activity,
                nativeAdId
            )
            builder.forNativeAd { nativeAd ->

                if (Constant.currentNativeAd != null) {
                    Constant.currentNativeAd?.destroy()
                }
                Constant.isNativeLoading = false
                Constant.currentNativeAd = nativeAd
                Log.d(nativeLogs, " Native  loaded native Ad")

                nativeListener.nativeAdLoaded(Constant.currentNativeAd)
            }

            val videoOptions = VideoOptions.Builder()
                .setStartMuted(true)
                .build()

            val adOptions = NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .setAdChoicesPlacement(NativeAdOptions.ADCHOICES_TOP_RIGHT)
                .build()
            builder.withNativeAdOptions(adOptions)

            val adLoader = builder.withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {

                    if (Constant.isDebug()) {
                        Snackbar.make(
                            activity.window.decorView.rootView,
                            " Native AD Error Native: ${loadAdError.message}",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    Log.d(nativeLogs, "Native failed native Ad  ${loadAdError.message}")
                    Constant.isNativeLoading = false
                    nativeListener.nativeAdFailed(loadAdError)

                }

                override fun onAdImpression() {
                    Constant.currentNativeAd = null
                    Constant.isNativeLoading = false
                    Log.d(nativeLogs, "Native onAdImpression native Ad")
                    super.onAdImpression()
                }

                override fun onAdClicked() {
                    Constant.isOpenNative = true
                    Log.d(nativeLogs, "Native onAdClicked native Ad")
                    Constant.isNativeLoading = false
                    super.onAdClicked()
                }

                override fun onAdLoaded() {
                    Constant.isNativeLoading = false

                    Log.d(nativeLogs, "Native onAdLoaded native Ad")
                    super.onAdLoaded()
                }
            }).build()
            adLoader.loadAd(
                AdRequest.Builder().build()
            )
        } else {
            Log.d(nativeLogs, "There is no internet connection available or Native ads Remote value is false ")
            frameLayout.visibility = View.VISIBLE
            nativeListener.nativeAdValidate("hideAll")
            if (Constant.isDebug()) {
                Snackbar.make(
                    activity.window.decorView.rootView,
                    "There is no internet connection available or Native ads Remote value is false ",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }
}