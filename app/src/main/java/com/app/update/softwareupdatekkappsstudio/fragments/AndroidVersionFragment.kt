package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAndroidVersionBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class AndroidVersionFragment : Fragment() {
    private lateinit var binding: FragmentAndroidVersionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    firebaseAnalytics(
                        "android_version_press_back",
                        "android_version_press_back"
                    )
                    findNavController().popBackStack()
                    showAd()

                }
            })

        binding = FragmentAndroidVersionBinding.inflate(inflater, container, false)

        binding.backDevice.setOnClickListener {
            firebaseAnalytics(
                "android_version_ui_click_back",
                "android_version_ui_click_back"
            )
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            firebaseAnalytics(
                "android_version_click_pro",
                "android_version_click_pro"
            )
            findNavController().navigate(R.id.action_androidVersionFragment_to_appProFragment)
        }

        loadAds()
        return binding.root
    }

    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.androidVersionsNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdandroidVersionsNativeAdOrBanner =
                NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "androidVersionsNativeAdOrBanner"
            ).setAdCallerName("androidVersionsNativeAdOrBanner")
                .loadNativeAd(
                    getString(R.string.val_native_android_details),
                    Constants.val_native_android_details,
                    binding.androidVersionsNativeAdOrBanner,
                    bindAdandroidVersionsNativeAdOrBanner.root,
                    bindAdandroidVersionsNativeAdOrBanner.adAppIcon,
                    bindAdandroidVersionsNativeAdOrBanner.adHeadline,
                    bindAdandroidVersionsNativeAdOrBanner.adBody,
                    bindAdandroidVersionsNativeAdOrBanner.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        firebaseAnalytics(
                            "android_version_native_failed",
                            "android_version_native_failed"
                        )
                        binding.androidVersionsNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.androidVersionsNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "androidVersionsNativeAdOrBanner"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_android_details), // give ad id here
                                remoteConfig = Constants.val_banner_android_details, // give remote config here
                                adsView = binding.androidVersionsNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    firebaseAnalytics(
                                        "android_version_banner_failed",
                                        "android_version_banner_failed"
                                    )
                                    binding.androidVersionsNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {
                                    firebaseAnalytics(
                                        "android_version_banner_loaded",
                                        "android_version_banner_loaded"
                                    )
                                }, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.androidVersionsNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adLoaded = {
                        firebaseAnalytics(
                            "android_version_native_loaded",
                            "android_version_native_loaded"
                        )
                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(
                requireActivity(),
                "androidVersionsNativeAdOrBanner"
            ).loadInterstitialAd(
                getString(R.string.val_fullscreen_android_details_back),
                Constants.val_fullscreen_android_details_back,
                adAlreadyLoaded = {

                },
                adLoaded = {
                    firebaseAnalytics(
                        "android_version_interstitial_loaded",
                        "android_version_interstitial_loaded"
                    )

                },
                adFailed = {
                    firebaseAnalytics(
                        "android_version_interstitial_failed",
                        "android_version_interstitial_failed"
                    )

                },
                adValidate = {},
            )
        } else {
            binding.androidVersionsNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "androidVersionsNativeAdOrBanner"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_android_details_back),
            Constants.val_fullscreen_android_details_back,
            fullScreenAdShow = {
                firebaseAnalytics(
                    "android_version_interstitial_show",
                    "android_version_interstitial_show"
                )
            },
            fullScreenAdDismissed = {
                firebaseAnalytics(
                    "android_version_interstitial_dismiss",
                    "android_version_interstitial_dismiss"
                )
            },
            fullScreenAdFailedToShow = {
                firebaseAnalytics(
                    "android_version_interstitial_failed_to_show",
                    "android_version_interstitial_failed_to_show"
                )
            },
            fullScreenAdNotAvailable = {},
        )
    }
}