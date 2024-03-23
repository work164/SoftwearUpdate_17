package com.sample.adsdk.native_ad

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
import com.sample.adsdk.billing.PurchasePrefs
import com.sample.adsdk.callbacks.NativeListener
import com.sample.adsdk.callbacks.OnNativeView
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.constants.Constant.isDebug
import com.sample.adsdk.constants.Constant.pxToDp
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdView
import com.sample.adsdk.R

/**
 * Created by
 * @Author: Hamza ,
 * @Company: Integer Technologies ,
 * @Email: devhamza090@gmail.com ,
 * on 15/3/2023.
 *
 *
 */

class CreateNativeView : LinearLayoutCompat {

    private lateinit var textAd: TextView
    private lateinit var shimmer: ShimmerFrameLayout
    private var nativeAdId: String = ""
    private var adHeight: Float = 0.0f
    private var remoteConfig = true
    var nativeAdView: NativeAdView? = null
    var headlineTextColor: Int? = null
    var bodyTextColor: Int? = null
    var adTextColor: Int? = null
    var actionButtonTextColor: Int? = null
    var actionBtnBackground: Drawable? = null
    var adTextBackground: Drawable? = null
    var frameLayout: ViewGroup? = null
    constructor(context: Context) : super(context) {

    }


    @SuppressLint("CustomViewStyleable")
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.CreateNativeAds)
        nativeAdId = a.getString(R.styleable.CreateNativeAds_native_ad_id).toString()
        val customHeight = a.getDimension(R.styleable.CreateNativeAds_native_ad_height, 50.0f)
        val buildType = a.getString(R.styleable.CreateNativeAds_buildType)

        headlineTextColor = a.getColor(
            R.styleable.CreateNativeAds_setTitleTextColor,
            resources.getColor(R.color.black)
        )
        bodyTextColor = a.getColor(
            R.styleable.CreateNativeAds_setBodyTextColor,
            resources.getColor(R.color.black)
        )
        adTextColor = a.getColor(
            R.styleable.CreateNativeAds_setAdTextColor,
            resources.getColor(R.color.black)
        )
        actionButtonTextColor = a.getColor(
            R.styleable.CreateNativeAds_setActionButtonTextColor,
            resources.getColor(R.color.white)
        )

        actionBtnBackground = a.getDrawable(R.styleable.CreateNativeAds_setActionButtonBackground)
        adTextBackground = a.getDrawable(R.styleable.CreateNativeAds_setAdTextBackground)
        if (buildType == "") {
            throw IllegalArgumentException("buildType is compulsory to add, make sure it is true or false")
        }
        adHeight = pxToDp(customHeight, context)

        val regex = Regex(Constant.regexPattern)
        val isValid = regex.matches(nativeAdId)
        if (!isValid) {
            throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
        }
        when {
            isDebug() && buildType.toBoolean() -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            !isDebug() && buildType.toBoolean() -> {
                //TestIds
                if (!Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                }
            }
            isDebug() && !buildType.toBoolean() -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
            !isDebug() && !buildType.toBoolean() -> {
                if (Constant.getTestIds().contains(nativeAdId)) {
                    throw IllegalArgumentException("Please add production Ad id in release version")
                }
            }
        }
        when (adHeight) {
            in 0f..49.0f -> {
                throw IllegalArgumentException("Your AdView height is greater or equal 50dp")
            }
            in 50.0f..99.0f -> {
                inflateLayout50dp()
            }
            in 100.0f..149.0f -> {
                inflateLayout100dp()

            }
            in 150.0f..199.0f -> {
                inflateLayout150dp()
            }
            in 200.0f..250.0f -> {
                inflateLayout250dp()
            }
            else -> {
                throw IllegalArgumentException("Your AdView height is must be greater then 0dp or less then 250dp")
            }
        }
        LoadNativeAd(context as Activity, nativeAdId, true, buildType.toBoolean(), frameLayout?:return ).loadNativeAd(
            object :
                NativeListener {
                override fun nativeAdLoaded(currentNativeAd: NativeAd?) {
                    if (currentNativeAd != null) {
                        populateUnifiedNativeAdViewMain(
                            currentNativeAd,
                            adHeight,
                            object : OnNativeView {
                                override fun nativeViewFind(
                                    headlineView: TextView,
                                    bodyView: TextView,
                                    ad: TextView
                                ) {

                                }

                            })
                    }
                    removeAllViews()
                    addView(nativeAdView)
                }

                override fun nativeAdFailed(loadAdError: LoadAdError) {

                }

                override fun nativeAdValidate(string: String) {

                }

            })
        a.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun setRemoteConfig(config: Boolean) {
        remoteConfig = config
    }

    private fun setTitleTextColor() {

    }

    fun populateUnifiedNativeAdViewMain(
        nativeAd: NativeAd,
        adHeight: Float,
        onNativeView: OnNativeView
    ) {
        if (nativeAd.headline == null) {
            (nativeAdView?.headlineView as TextView).visibility = View.GONE
        } else {
            (nativeAdView?.headlineView as TextView).visibility = View.VISIBLE
            (nativeAdView?.headlineView as TextView).text = nativeAd.headline
        }

        if (adHeight >= 150.0) {
            nativeAdView?.mediaView?.visibility = View.VISIBLE
            //nativeAdView?.mediaView?.setImageScaleType(ImageView.ScaleType.FIT_XY)
            //adMedia.mediaContent = nativeAd.mediaContent
            nativeAdView?.mediaView = nativeAdView?.findViewById(R.id.ad_media)
        }
        if (nativeAd.body == null) {
            (nativeAdView?.bodyView as TextView).visibility = View.GONE
        } else {
            (nativeAdView?.bodyView as TextView).visibility = View.VISIBLE
            (nativeAdView?.bodyView as TextView).text = nativeAd.body
        }
        if (nativeAd.callToAction == null) {
            (nativeAdView?.callToActionView as Button).visibility = View.INVISIBLE
        } else {
            (nativeAdView?.callToActionView as Button).visibility = View.VISIBLE
            (nativeAdView?.callToActionView as Button).text = nativeAd.callToAction

        }
        if (nativeAd.icon == null) {
            nativeAdView?.iconView?.visibility = View.GONE
        } else {
            (nativeAdView?.iconView as ImageView).setImageDrawable(
                nativeAd.icon?.drawable
            )
            nativeAdView?.iconView?.visibility = View.VISIBLE
        }
        if (nativeAd.starRating == null) {
            (nativeAdView?.starRatingView as RatingBar).visibility = View.INVISIBLE
        } else {
            (nativeAdView?.starRatingView as RatingBar).rating = nativeAd.starRating?.toFloat()!!
            (nativeAdView?.starRatingView as RatingBar).visibility = View.VISIBLE
        }


        nativeAdView?.setNativeAd(nativeAd)

        onNativeView.nativeViewFind(
            (nativeAdView?.headlineView as TextView), (nativeAdView?.bodyView as TextView), textAd
        )
        shimmer.visibility = View.GONE
        shimmer.stopShimmer()
        shimmer.showShimmer(false)
    }

    fun inflateLayout50dp() {
        try {
            removeAllViewsInLayout()
            inflate(context, R.layout.ad_native_view_fifty_dp, this)
            initIds()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun inflateLayout100dp() {
        try {
            removeAllViewsInLayout()
            inflate(context, R.layout.ad_native_view_hundered_dp, this)
            initIds()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun inflateLayout150dp() {
        try {
            removeAllViewsInLayout()
            inflate(context, R.layout.ad_native_view_hundered_fifty_dp, this)
            initIds()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun inflateLayout250dp() {
        try {
            removeAllViewsInLayout()
            inflate(context, R.layout.ad_native_view_two_hundered_fifty_dp, this)
            initIds()
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun initIds() {

        textAd = findViewById(R.id.ad)
        shimmer = findViewById(R.id.shimmer_layout)
        nativeAdView = findViewById(R.id.nativeAdView)
        nativeAdView?.callToActionView = nativeAdView?.findViewById(R.id.ad_call_to_action)
        nativeAdView?.bodyView = nativeAdView?.findViewById(R.id.ad_body)
        nativeAdView?.iconView = nativeAdView?.findViewById(R.id.ad_app_icon)
        nativeAdView?.mediaView = nativeAdView?.findViewById(R.id.ad_media)
        nativeAdView?.headlineView = nativeAdView?.findViewById(R.id.ad_headline)
        nativeAdView?.starRatingView = nativeAdView?.findViewById(R.id.ad_stars)
        if (PurchasePrefs(context).getBoolean(Constant.KEY_IN_APP)) {
            nativeAdView?.visibility = View.GONE
        }
        if (!Constant.isNetworkAvailable(context)) {
            nativeAdView?.visibility = View.GONE
        }
        shimmer.visibility = View.VISIBLE
        shimmer.startShimmer()
        shimmer.showShimmer(true)
        headlineTextColor?.let { (nativeAdView?.headlineView as TextView).setTextColor(it) }
        bodyTextColor?.let { (nativeAdView?.bodyView as TextView).setTextColor(it) }
        actionButtonTextColor?.let { (nativeAdView?.callToActionView as Button).setTextColor(it) }
        adTextColor?.let { textAd.setTextColor(it) }
        (nativeAdView?.callToActionView as Button).background = actionBtnBackground?:resources.getDrawable(
            R.drawable.btn_bg)
        textAd.background = adTextBackground

    }
}