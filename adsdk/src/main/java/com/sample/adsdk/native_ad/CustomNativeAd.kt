package com.sample.adsdk.native_ad

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.sample.adsdk.callbacks.NativeListener
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.constants.Constant.isDebug
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView

class CustomNativeAd(
    val activity: Activity,
    private val adView: NativeAdView,
    val nativeAdId: String,
    val config: Boolean,
    val frameLayout: ViewGroup,
    val buildType:Boolean
) {
    val LOG = "CustomNativeAd"
    init {
        val regex = Regex(Constant.regexPattern)
        val isValid = regex.matches(nativeAdId)
        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            !isDebug() && buildType -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
            !isDebug() && !buildType -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }
    }

    fun populateNativeAd(
        adIcon: Int,
        adHeadLine: Int,
        adBody: Int,
        adCallToActionButton: Int
    ) {
        LoadNativeAd(activity, nativeAdId, config,buildType,frameLayout).loadNativeAd(object : NativeListener {
            override fun nativeAdLoaded(currentNativeAd: NativeAd?) {
                if (currentNativeAd != null){
                    adView.headlineView = adView.findViewById(adHeadLine)
                    adView.bodyView = adView.findViewById(adBody)
                    adView.callToActionView = adView.findViewById(adCallToActionButton)
                    adView.iconView = adView.findViewById(adIcon)
                    if (currentNativeAd.headline == null){
                        (adView.headlineView as TextView).visibility = View.INVISIBLE
                    }else {
                        (adView.headlineView as TextView).visibility = View.VISIBLE
                        (adView.headlineView as TextView).text = currentNativeAd.headline
                    }

                    if (currentNativeAd.body == null) {
                        adView.bodyView?.visibility = View.INVISIBLE
                    } else {
                        adView.bodyView?.visibility = View.VISIBLE
                        (adView.bodyView as TextView).text = currentNativeAd.body
                    }

                    if (currentNativeAd.callToAction == null) {
                        adView.callToActionView?.visibility = View.INVISIBLE
                    } else {
                        adView.callToActionView?.visibility = View.VISIBLE
                        (adView.callToActionView as AppCompatButton).text = currentNativeAd.callToAction
                    }

                    if (currentNativeAd.icon == null) {
                        adView.iconView?.visibility = View.GONE
                    } else {
                        (adView.iconView as ImageView).setImageDrawable(
                            currentNativeAd.icon?.drawable
                        )
                        adView.iconView?.visibility = View.VISIBLE
                    }
                    currentNativeAd.let {
                        adView.setNativeAd(it)
                    }
                    frameLayout.removeAllViews()
                    frameLayout.addView(adView)
                }
            }

            override fun nativeAdFailed(loadAdError: LoadAdError) {
                Log.e(LOG, loadAdError.message)
            }

            override fun nativeAdValidate(string: String) {
                Log.e(LOG, string)
            }

        })

    }

    fun populateNativeWithOutMediaAd(
        adIcon: Int,
        adHeadLine: Int,
        adBody: Int,
        adCallToActionButton: Int,
        nativeListener: NativeCallback
    ) {
        LoadNativeAd(activity, nativeAdId, config,buildType, frameLayout).loadNativeAd(object : NativeListener {
            override fun nativeAdLoaded(currentNativeAd: NativeAd?) {
                if (currentNativeAd != null){
                    adView.headlineView = adView.findViewById(adHeadLine)
                    adView.bodyView = adView.findViewById(adBody)
                    adView.callToActionView = adView.findViewById(adCallToActionButton)
                    adView.iconView = adView.findViewById(adIcon)
                    if (currentNativeAd.headline == null){
                        (adView.headlineView as TextView).visibility = View.INVISIBLE
                    }else {
                        (adView.headlineView as TextView).visibility = View.VISIBLE
                        (adView.headlineView as TextView).text = currentNativeAd.headline
                    }

                    if (currentNativeAd.body == null) {
                        adView.bodyView?.visibility = View.INVISIBLE
                    } else {
                        adView.bodyView?.visibility = View.VISIBLE
                        (adView.bodyView as TextView).text = currentNativeAd.body
                    }

                    if (currentNativeAd.callToAction == null) {
                        adView.callToActionView?.visibility = View.INVISIBLE
                    } else {
                        adView.callToActionView?.visibility = View.VISIBLE
                        (adView.callToActionView as AppCompatButton).text = currentNativeAd.callToAction
                    }

                    if (currentNativeAd.icon == null) {
                        adView.iconView?.visibility = View.GONE
                    } else {
                        (adView.iconView as ImageView).setImageDrawable(
                            currentNativeAd.icon?.drawable
                        )
                        adView.iconView?.visibility = View.VISIBLE
                    }


                    currentNativeAd.let {
                        adView.setNativeAd(it)
                    }
                    frameLayout.removeAllViews()
                    frameLayout.addView(adView)
                    nativeListener.nativeAdCallLoaded(currentNativeAd)
                }
            }
            override fun nativeAdFailed(loadAdError: LoadAdError) {
                Log.e(LOG, loadAdError.message)
                frameLayout.visibility = View.GONE
                nativeListener.nativeAdCallFailed(loadAdError)
            }

            override fun nativeAdValidate(string: String) {
                Log.e(LOG, string)
                nativeListener.nativeAdCallValidate(string)
            }

        })

    }

    fun populateNativeAdMedia(
        adIcon: Int,
        adHeadLine: Int,
        adBody: Int,
        adCallToActionButton: Int,
        adMediaView: Int?,
        nativeListener: NativeCallback
    ) {
        Log.e(LOG, "FunctionCall")

        LoadNativeAd(activity, nativeAdId, config,buildType, frameLayout).loadNativeAd(object : NativeListener {
            override fun nativeAdLoaded(currentNativeAd: NativeAd?) {
                if (currentNativeAd != null){
                    adView.headlineView = adView.findViewById(adHeadLine)
                    adView.bodyView = adView.findViewById(adBody)
                    adView.callToActionView = adView.findViewById(adCallToActionButton)
                    adView.iconView = adView.findViewById(adIcon)

                    val mediaView: com.google.android.gms.ads.nativead.MediaView =
                        adView.findViewById(adMediaView?:return)
                    mediaView.visibility = View.VISIBLE
                    adView.mediaView = mediaView

                    if (currentNativeAd.headline == null){
                        (adView.headlineView as TextView).visibility = View.INVISIBLE
                    }else {
                        (adView.headlineView as TextView).visibility = View.VISIBLE
                        (adView.headlineView as TextView).text = currentNativeAd.headline
                    }

                    if (currentNativeAd.body == null) {
                        adView.bodyView?.visibility = View.INVISIBLE
                    } else {
                        adView.bodyView?.visibility = View.VISIBLE
                        (adView.bodyView as TextView).text = currentNativeAd.body
                    }

                    if (currentNativeAd.callToAction == null) {
                        adView.callToActionView?.visibility = View.INVISIBLE
                    } else {
                        adView.callToActionView?.visibility = View.VISIBLE
                        (adView.callToActionView as AppCompatButton).text = currentNativeAd.callToAction
                    }

                    if (currentNativeAd.icon == null) {
                        adView.iconView?.visibility = View.GONE
                    } else {
                        (adView.iconView as ImageView).setImageDrawable(
                            currentNativeAd.icon?.drawable
                        )
                        adView.iconView?.visibility = View.VISIBLE
                    }


                    currentNativeAd.let {
                        adView.setNativeAd(it)
                    }
                    frameLayout.removeAllViews()
                    frameLayout.addView(adView)
                    nativeListener.nativeAdCallLoaded(currentNativeAd)
                }
            }

            override fun nativeAdFailed(loadAdError: LoadAdError) {
                Log.e(LOG, loadAdError.message)
                frameLayout.removeAllViews()
                nativeListener.nativeAdCallFailed(loadAdError)

            }

            override fun nativeAdValidate(string: String) {
                Log.e(LOG, string)
                nativeListener.nativeAdCallValidate(string)

            }

        })

    }
}