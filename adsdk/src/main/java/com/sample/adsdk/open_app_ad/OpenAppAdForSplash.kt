package com.sample.adsdk.open_app_ad

import android.app.Activity
import android.util.Log
import com.sample.adsdk.billing.GoogleBillingProduct
import com.sample.adsdk.callbacks.OnOpenAdFailedToLoadListener
import com.sample.adsdk.callbacks.OnOpenAdFailedToShowListener
import com.sample.adsdk.callbacks.OnOpenAdLoadedListener
import com.sample.adsdk.callbacks.OnShowOpenAdCompleteListener
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.constants.Constant.appOpenAd
import com.sample.adsdk.constants.Constant.isLoadingOpenAppAd
import com.sample.adsdk.constants.Constant.isNetworkAvailable
import com.sample.adsdk.constants.Constant.isShowingOpenAppAd
import com.sample.adsdk.constants.Constant.isSplashOpenAd
import com.sample.adsdk.constants.Constant.loadTime
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.material.snackbar.Snackbar
import java.util.*

class OpenAppAdForSplash(val context: Activity, private val remoteValue: Boolean, private val openAppAdId: String, buildType: Boolean) {

    private val LOG = "OpenAppAdForSplash"

    init {
        val regex = Regex(Constant.regexPattern)
        val isValid = regex.matches(openAppAdId)

        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            Constant.isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(openAppAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            !Constant.isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(openAppAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            Constant.isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(openAppAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }

            !Constant.isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(openAppAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }

    }

    fun loadAd(onOpenAdLoaded: OnOpenAdLoadedListener, onOpenAdFailedToLoadListener: OnOpenAdFailedToLoadListener) {
        if (isNetworkAvailable(context) && remoteValue && !GoogleBillingProduct(
                context,
                object : GoogleBillingProduct.SuccessPurchase {
                    override fun onSuccessPurchase() {
                    }

                }).checkPurchased(
                context
            )
        ) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingOpenAppAd || isAdAvailable()) {
                return
            }

            isLoadingOpenAppAd = true
            val request = AdRequest.Builder().build()
            AppOpenAd.load(context, openAppAdId, request, object : AppOpenAd.AppOpenAdLoadCallback() {
                override fun onAdLoaded(ad: AppOpenAd) {
                    appOpenAd = ad
                    isLoadingOpenAppAd = false
                    loadTime = Date().time
                    Log.d(LOG, "onAdLoaded. open splash")
                    onOpenAdLoaded.onOpenAdLoaded()
                }

                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    isLoadingOpenAppAd = false
                    onOpenAdFailedToLoadListener.onOpenAdFailed()
                    Log.d(LOG, "onAdFailedToLoad->open splash: " + loadAdError.message)
                }
            })
        } else {
            if (Constant.isDebug()) {
                Snackbar.make(
                    context.window.decorView.rootView,
                    "There is no internet connection available or splash open app ads remote value is false",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference: Long = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private fun isAdAvailable(): Boolean {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    fun showAdIfAvailable() {
        showAdIfAvailable(context, object : OnShowOpenAdCompleteListener {
            override fun onShowAdComplete() {
                // Empty because the user will go back to the activity that shows the ad.
            }
        }, object : OnOpenAdFailedToShowListener{
            override fun onOpenAdFailedToShow() {

            }

        })
    }

    fun showAdIfAvailable(activity: Activity, onShowAdCompleteListener: OnShowOpenAdCompleteListener, onOpenAdFailedToShowListener: OnOpenAdFailedToShowListener) {

        // If the app open ad is already showing, do not show the ad again.
        if (isShowingOpenAppAd) {
            Log.d(LOG, "The app open ad is already showing. open splash")
            return
        }

        // If the app open ad is not available yet, invoke the callback then load the ad.
        if (!isAdAvailable()) {
            Log.d(LOG, "The app open ad is not ready yet. open splash")
            onShowAdCompleteListener.onShowAdComplete()
//                loadAd(activity)
            return
        }
        Log.d(LOG, "Will show ad.")

        appOpenAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                // Set the reference to null so isAdAvailable() returns false.
                appOpenAd = null
                isShowingOpenAppAd = false
                Log.d(LOG, "onAdDismissedFullScreenContent. open splash")
                isSplashOpenAd = false
                onShowAdCompleteListener.onShowAdComplete()
//                    loadAd(activity)
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                appOpenAd = null
                isShowingOpenAppAd = false
                isSplashOpenAd = false
                Log.d(LOG, "onAdFailedToShowFullScreenContent -> open splash: " + adError.message)

                onOpenAdFailedToShowListener.onOpenAdFailedToShow()
//                    loadAd(activity)
            }

            override fun onAdShowedFullScreenContent() {
                isSplashOpenAd = true
                Log.d(LOG, "onAdShowedFullScreenContent. open splash")
            }
        }
        isSplashOpenAd = true
        isShowingOpenAppAd = true
        appOpenAd?.show(activity)
    }
}