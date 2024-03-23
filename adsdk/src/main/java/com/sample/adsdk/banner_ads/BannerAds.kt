package com.sample.adsdk.banner_ads

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import com.sample.adsdk.banner_ads.createAdaptiveAd
import com.sample.adsdk.banner_ads.createBannerAd
import com.sample.adsdk.banner_ads.createMediumRectangleBannerAd
import com.sample.adsdk.banner_ads.loadCollapsibleBanner

import com.sample.adsdk.billing.GoogleBillingProduct
import com.sample.adsdk.callbacks.BannerAdCallbacks
import com.sample.adsdk.constants.Constant.getHeightAndWidth
import com.sample.adsdk.constants.Constant.getTestIds
import com.sample.adsdk.constants.Constant.isDebug
import com.sample.adsdk.constants.Constant.isNetworkAvailable
import com.sample.adsdk.constants.Constant.regexPattern
import com.sample.adsdk.types.AdTypes
import com.google.android.gms.ads.AdView
import com.google.android.material.snackbar.Snackbar

/**
 * Created by
 * @Author: Hamza ,
 * @Company: Integer Technologies ,
 * @Email: devhamza090@gmail.com ,
 * on 15/3/2023 , Fri .
 *
 *
 */

class BannerAds(
    internal val activity: Activity?,
    internal val view: ViewGroup,
    internal var bannerProductionId: String?,
    internal val adSize: String,
    buildType: Boolean,
    var remoteValue: Boolean
) {
    var collapseAbleAdView: AdView? = null
    var adViewAdaptive: AdView? = null
    var mediumRectangleAd: AdView? = null
    var simpleBannerAd: AdView? = null
    internal val bannerLogs = "bannerLogs"

    private var viewHeight: Int = 0
    private var viewWidth: Int = 0

    init {
        val regex = Regex(regexPattern)
        val isValid = regex.matches(bannerProductionId.toString())

        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            isDebug() && buildType -> {
                //TestIds
                if (!getTestIds().contains(bannerProductionId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            !isDebug() && buildType -> {
                //TestIds
                if (!getTestIds().contains(bannerProductionId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }

            isDebug() && !buildType -> {
                if (getTestIds().contains(bannerProductionId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }

            !isDebug() && !buildType -> {
                if (getTestIds().contains(bannerProductionId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }

        view.visibility = View.VISIBLE
        viewHeight = getHeightAndWidth(view).first
        viewWidth = getHeightAndWidth(view).second

    }

    /**
     * User AdSizes class for getting adsize
     *
     * **/
    fun loadBanner(callbacks: BannerAdCallbacks?) {
        //TODO: In app purchase check, remote config check, payment check
        if (isNetworkAvailable(activity) && remoteValue && !GoogleBillingProduct(activity ?: return,
                object : GoogleBillingProduct.SuccessPurchase {
                    override fun onSuccessPurchase() {
                    }

                }).checkPurchased(
                activity
            )
        ) {
            Log.d(bannerLogs, "loadAdaptiveBanner : bannerId : $bannerProductionId")
            Log.d(bannerLogs, "loadAdaptiveBanner : remote : $remoteValue")
            val viewTreeObserver: ViewTreeObserver = view.viewTreeObserver
            if (viewTreeObserver.isAlive) {
                viewTreeObserver.addOnGlobalLayoutListener(object :
                    ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        val density: Float? = activity.resources?.displayMetrics?.density
                        val dpHeight: Float = view.measuredHeight / density!!
                        val dpWidth: Float = view.measuredWidth / density
                        viewHeight = dpHeight.toInt()
                        viewWidth = dpWidth.toInt()

                        Log.e(bannerLogs, viewHeight.toString())
                        Log.e(bannerLogs, viewWidth.toString())
                        when (adSize) {
                            AdTypes.BANNER.toString() -> {
                                if (viewHeight < 50 || viewWidth < 320) {
                                    if (isDebug()) {
                                        throw IllegalStateException("Incorrect view size, view height size must be greater or equal to 50dp and width must be 350dp")
                                    }
                                } else {
                                    createBannerAd(callbacks)
                                }
                            }

                            AdTypes.MEDIUM_RECTANGLE.toString() -> {
                                if (viewHeight < 250 || viewWidth < 300) {
                                    if (isDebug()) {
                                        throw IllegalStateException("Incorrect view size, view height size must be greater or equal to 250dp and width must be 300dp")
                                    }
                                } else {
                                    createMediumRectangleBannerAd(callbacks)
                                }
                            }

                            AdTypes.Collapse.toString() -> {
                                if (viewHeight < 50) {
                                    if (isDebug()) {
                                        throw IllegalStateException("Incorrect view size, view size must be greater or equal to 50dp")
                                    }
                                } else {
                                    loadCollapsibleBanner(callbacks)
                                }
                            }

                            AdTypes.Adaptive.toString() -> {
                                createAdaptiveAd(callbacks)
                            }

                            else -> {
                                if (isDebug()) {
                                    throw IllegalStateException("Incorrect Ad Size, Use AdSizes.BANNER or AdSizes.MEDIUM_RECTANGLE only")
                                }
                            }
                        }
                        if (viewHeight != 0) view.viewTreeObserver
                            .removeOnGlobalLayoutListener(this)
                    }
                })
            }

        } else {
            view.visibility = View.GONE
            Log.d(bannerLogs, "loadBannerFail : remote : $remoteValue")
            if (isDebug()) {
                Snackbar.make(
                    activity?.window?.decorView?.rootView ?: view,
                    "There is no internet connection available or banner ads remote value is false",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun bannerDestroy() {
        collapseAbleAdView?.destroy()
        adViewAdaptive?.destroy()
        mediumRectangleAd?.destroy()
        simpleBannerAd?.destroy()
    }

    fun bannerResume(){
        collapseAbleAdView?.resume()
        adViewAdaptive?.resume()
        mediumRectangleAd?.resume()
        simpleBannerAd?.resume()
    }
    fun bannerPause(){
        collapseAbleAdView?.pause()
        adViewAdaptive?.pause()
        mediumRectangleAd?.pause()
        simpleBannerAd?.pause()
    }
}