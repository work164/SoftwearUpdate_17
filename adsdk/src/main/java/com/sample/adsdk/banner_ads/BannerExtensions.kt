package com.sample.adsdk.banner_ads

import android.os.Bundle
import android.util.Log
import com.sample.adsdk.callbacks.BannerAdCallbacks
import com.sample.adsdk.constants.Constant
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError
import com.google.android.material.snackbar.Snackbar
import com.sample.adsdk.banner_ads.BannerAds

internal fun BannerAds.createBannerAd(callbacks: BannerAdCallbacks?) {

    if (activity == null) return
    if (!Constant.isBannerAdLoading) {
        Constant.isBannerAdLoading = true

        simpleBannerAd = AdView(activity.applicationContext)
        view.removeAllViews()
        view.addView(simpleBannerAd)
        simpleBannerAd?.adUnitId =
            bannerProductionId.toString()
        simpleBannerAd?.setAdSize(AdSize.BANNER)
        val adRequest = AdRequest.Builder().build()
        simpleBannerAd?.loadAd(adRequest)
        simpleBannerAd?.adListener = object : AdListener() {
            override fun onAdClicked() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdClicked")
                callbacks?.onAdClicked()
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d(bannerLogs, "AdaptiveBanner : onAdFailedToLoad ${loadAdError.message}")
                callbacks?.onAdFailedToLoad(loadAdError.message)
                Constant.isBannerAdLoading = false
                super.onAdFailedToLoad(loadAdError)
            }

            override fun onAdImpression() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdImpression")
                callbacks?.onAdImpression()
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdLoaded")
                Constant.isBannerAdLoading = false
                callbacks?.onAdLoaded()
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdOpened")
                callbacks?.onAdOpened()
                super.onAdOpened()
            }
        }
    } else {
        if (Constant.isDebug()) {
//            throw IllegalStateException("Ad already in loading process")
            Snackbar.make(
                activity.window.decorView.rootView,
                " Ad already in loading process",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

}


internal fun BannerAds.createMediumRectangleBannerAd(callbacks: BannerAdCallbacks?) {

    if (activity == null) return
    if (!Constant.isMediumAdLoading) {
        Constant.isMediumAdLoading = true

        mediumRectangleAd = AdView(activity.applicationContext)
        view.removeAllViews()
        view.addView(mediumRectangleAd)
        mediumRectangleAd?.adUnitId = bannerProductionId.toString()
        mediumRectangleAd?.setAdSize(AdSize.MEDIUM_RECTANGLE)
        val adRequest = AdRequest.Builder().build()
        mediumRectangleAd?.loadAd(adRequest)
        mediumRectangleAd?.adListener = object : AdListener() {
            override fun onAdClicked() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdClicked")
                callbacks?.onAdClicked()
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d(bannerLogs, "AdaptiveBanner : onAdFailedToLoad ${loadAdError.message}")
                callbacks?.onAdFailedToLoad(loadAdError.message)
                Constant.isMediumAdLoading = false
                super.onAdFailedToLoad(loadAdError)
            }

            override fun onAdImpression() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdImpression")
                callbacks?.onAdImpression()
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdLoaded")
                Constant.isMediumAdLoading = false
                callbacks?.onAdLoaded()
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdOpened")
                callbacks?.onAdOpened()
                super.onAdOpened()
            }
        }
    } else {
        if (Constant.isDebug()) {
            Log.e("TAG", "createMediumRectangleBannerAd: Ad already in loading process")
//            throw IllegalStateException("Ad already in loading process")
        }
    }

}

internal fun BannerAds.createAdaptiveAd(callbacks: BannerAdCallbacks?) {

    if (activity == null) return
    if (!Constant.isAdaptiveAdLoading) {
        Constant.isAdaptiveAdLoading = true

        adViewAdaptive = AdView(activity.applicationContext)
        view.removeAllViews()

        view.addView(adViewAdaptive)
        adViewAdaptive?.adUnitId = bannerProductionId.toString()
        adViewAdaptive?.setAdSize(Constant.adSize(activity, view))
        val adRequest = AdRequest.Builder().build()
        adViewAdaptive?.loadAd(adRequest)
        adViewAdaptive?.adListener = object : AdListener() {
            override fun onAdClicked() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdClicked")
                callbacks?.onAdClicked()
                super.onAdClicked()
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                Log.d(bannerLogs, "AdaptiveBanner : onAdFailedToLoad ${loadAdError.message}")
                callbacks?.onAdFailedToLoad(loadAdError.message)
                Constant.isAdaptiveAdLoading = false
                super.onAdFailedToLoad(loadAdError)
            }

            override fun onAdImpression() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdImpression")
                callbacks?.onAdImpression()
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdLoaded")
                Constant.isAdaptiveAdLoading = false
                callbacks?.onAdLoaded()
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                Log.d(bannerLogs, "AdaptiveBanner : onAdOpened")
                callbacks?.onAdOpened()
                super.onAdOpened()
            }
        }
    } else {
        if (Constant.isDebug()) {
            try {
                Snackbar.make(
                    activity.window.decorView.rootView,
                    "Banner Ad already in loading process",
                    Snackbar.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}


internal fun BannerAds.loadCollapsibleBanner(callbacks: BannerAdCallbacks?) {
    if (activity == null) return
    if (!Constant.isBannerCollapseAdLoading) {
        Constant.isBannerCollapseAdLoading = true
        Log.d(bannerLogs, "loadAdaptiveBanner : bannerId : $bannerProductionId")
        val extras = Bundle()
        extras.putString("collapsible", "bottom")
        view.removeAllViews()


        collapseAbleAdView = AdView(activity)
        view.removeAllViews()

        view.addView(collapseAbleAdView)
        collapseAbleAdView?.adUnitId = bannerProductionId.toString()
        collapseAbleAdView?.setAdSize(Constant.adSize(activity, view))
        val adRequest =
            AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter::class.java, extras).build()
        collapseAbleAdView?.loadAd(adRequest)

        collapseAbleAdView?.adListener = object : AdListener() {
            override fun onAdClicked() {
                Log.d(bannerLogs, "BannerWithSize : onAdClicked")
                callbacks?.onAdClicked()
                super.onAdClicked()
            }

            override fun onAdClosed() {
                Log.d(bannerLogs, "BannerWithSize : onAdClosed")
                callbacks?.onAdClosed()
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                Constant.isBannerCollapseAdLoading = false
                Log.d(bannerLogs, "BannerWithSize : onAdFailedToLoad ${p0.message}")
                callbacks?.onAdFailedToLoad(p0.message)
                super.onAdFailedToLoad(p0)
            }

            override fun onAdImpression() {
                Log.d(bannerLogs, "BannerWithSize : onAdImpression")
                callbacks?.onAdImpression()
                super.onAdImpression()
            }

            override fun onAdLoaded() {
                Log.d(bannerLogs, "BannerWithSize : onAdLoaded")
                Constant.isBannerCollapseAdLoading = false
                callbacks?.onAdLoaded()
                super.onAdLoaded()
            }

            override fun onAdOpened() {
                Log.d(bannerLogs, "BannerWithSize : onAdOpened")
                callbacks?.onAdOpened()
                super.onAdOpened()
            }

        }
    }
}