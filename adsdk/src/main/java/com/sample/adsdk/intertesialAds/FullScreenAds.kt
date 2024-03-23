package com.sample.adsdk.intertesialAds

import android.app.Activity
import android.util.Log
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.material.snackbar.Snackbar
import com.sample.adsdk.billing.GoogleBillingProduct
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.constants.Constant.isAdLoading
import com.sample.adsdk.constants.Constant.isDebug
import com.sample.adsdk.constants.Constant.isNetworkAvailable
import com.sample.adsdk.constants.Constant.isOpenAdNotShow
import com.sample.adsdk.constants.Constant.isShowingInterAd
import com.sample.adsdk.constants.Constant.mInterstitialAd
import com.sample.adsdk.constants.Constant.printDifference
import com.sample.adsdk.constants.Constant.saveLastImpressionInterstitialTime
import java.util.Calendar

/**
 * Created by
 * @Author: Hamza ,
 * @Company: Integer Technologies ,
 * @Email: devhamza090@gmail.com ,
 * on 11/5/2021 , Fri .
 *
 *
 */
class FullScreenAds(
    internal val activity: Activity?,
    internal var interstitialProductionId: String,
    buildType: Boolean
) {

    internal val fullScreenLogs = "fullScreenLogs"


    init {
        val regex = Regex(Constant.regexPattern)
        val isValid = regex.matches(interstitialProductionId)

        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(interstitialProductionId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            !isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(interstitialProductionId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(interstitialProductionId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }

            !isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(interstitialProductionId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }
    }

    /** First call this for load add, May be on splash*/
    fun loadFullScreenAd(adsListener: FullScreenAdListener, addConfig: Boolean) {
        if (activity == null) return
        if (isNetworkAvailable(activity) && !GoogleBillingProduct(
                activity,
                object : GoogleBillingProduct.SuccessPurchase {
                    override fun onSuccessPurchase() {
                    }
                }).checkPurchased(
                activity
            ) && addConfig
        ) {
            Log.d(
                fullScreenLogs,
                "full_screen loadFullScreenAd: request with ${this.interstitialProductionId}"
            )
            Log.d(fullScreenLogs, "full_screen Callback $mInterstitialAd: $isAdLoading.")

            if (mInterstitialAd == null && !isAdLoading) {
                try {
                    isAdLoading = true
                    val adRequest = AdRequest.Builder().build()

                    InterstitialAd.load(activity.applicationContext, interstitialProductionId,
                        adRequest, object : InterstitialAdLoadCallback() {
                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                Log.d(
                                    fullScreenLogs,
                                    "full_screen loadFullScreenAd : ${adError.message}"
                                )
                                isOpenAdNotShow = false
                                isAdLoading = false
                                mInterstitialAd = null
                                adsListener.adFailed()
                            }

                            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                                Log.d(interstitialProductionId, "loadFullScreenAd : Ad was loaded.")
                                mInterstitialAd = interstitialAd
                                isAdLoading = false
                                adsListener.adLoaded()
                            }
                        })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                adsListener.adAlreadyLoaded()
                Log.d(
                    interstitialProductionId,
                    "loadFullScreenAd : having a AD. or loading precious"
                )
            }
        } else {
            if (isDebug()) {
                Snackbar.make(
                    activity.window.decorView.rootView,
                    "There is no internet connection available or interstitial remote value is false",
                    Snackbar.LENGTH_LONG
                ).show()
            }
            adsListener.adValidate()
        }

    }


    /**
     * After Calling load call show function preview your add.
     * */
    fun showAndLoad(
        adMobAdListener: AdMobFullScreenListener,
        newAdListener: FullScreenAdListener,
        addConfig: Boolean,
        seconds: Int
    ) {

        val cal = Calendar.getInstance()
        val difference = saveLastImpressionInterstitialTime?.let { printDifference(it, cal.time) }
        if (difference != null)
            if (seconds > difference) {
                return
            }

        if (activity == null) return
        if (mInterstitialAd != null && !GoogleBillingProduct(
                activity,
                object : GoogleBillingProduct.SuccessPurchase {
                    override fun onSuccessPurchase() {
                    }

                }).checkPurchased(activity) && addConfig
        ) {
            isOpenAdNotShow = true
            mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    Log.d(fullScreenLogs, "full_screen Callback : Ad was dismissed.")
                    isOpenAdNotShow = false
                    isShowingInterAd = false
                    mInterstitialAd = null
                    adMobAdListener.fullScreenAdDismissed()
                  //  loadFullScreenAd(newAdListener, addConfig)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    Log.d(fullScreenLogs, "full_screen Callback : Ad failed to show.")
                    mInterstitialAd = null
                    isShowingInterAd = false
                    if (isDebug()) {
                        Snackbar.make(
                            activity.window.decorView.rootView,
                            "AD Error : ${adError.message}", Snackbar.LENGTH_LONG
                        ).show()
                    }
                    adMobAdListener.fullScreenAdFailedToShow()
                }

                override fun onAdShowedFullScreenContent() {
                    Log.d(fullScreenLogs, "full_screen Callback : Ad showed fullscreen content.")
                    val cal = Calendar.getInstance()
                    saveLastImpressionInterstitialTime = cal.time
                    isOpenAdNotShow = true
                    adMobAdListener.fullScreenAdShow()
                }

                override fun onAdClicked() {
                    super.onAdClicked()
                }
            }
            isShowingInterAd = true
            mInterstitialAd?.show(activity)

        } else {
            isShowingInterAd = false
            isOpenAdNotShow = false
//            mInterstitialAd = null
            adMobAdListener.fullScreenAdNotAvailable()
        }
    }
}