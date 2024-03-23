package com.sample.adsdk.native_ad

import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd

interface NativeCallback {
    fun nativeAdCallLoaded(currentNativeAd: NativeAd?)
    fun nativeAdCallFailed(loadAdError: LoadAdError)
    fun nativeAdCallValidate(string: String)
}