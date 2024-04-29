package com.app.update.softwareupdatekkappsstudio

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSettingsBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class FragmentSettings : Fragment() {

    private var settingsBinding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()

                }
            })
        settingsBinding = FragmentSettingsBinding.inflate(inflater, container, false)


        settingsBinding?.backDevice?.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }

        settingsBinding?.btnInAppForwardDevice?.setOnClickListener {
            findNavController().navigate(R.id.action_settingFragment_to_appProFragment)
        }
        loadAds()
        return settingsBinding?.root!!
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            settingsBinding?.settingsNativeAdOrBanner?.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "native_setting"
            ).setAdCallerName("native_setting")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.val_native_setting,
                    settingsBinding?.settingsNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        settingsBinding?.settingsNativeAdOrBanner?.visibility = View.GONE
                    },
                    adValidate = {
                        settingsBinding?.settingsNativeAdOrBanner?.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "banner_setting"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.val_banner_setting, // give remote config here
                                adsView = settingsBinding?.settingsNativeAdOrBanner
                                    ?: return@loadNativeAd, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    settingsBinding?.settingsNativeAdOrBanner?.visibility =
                                        View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    settingsBinding?.settingsNativeAdOrBanner?.visibility =
                                        View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(
                requireActivity(),
                "fullscreen_system_app_details"
            ).loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                Constants.val_fullscreen_setting_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            settingsBinding?.settingsNativeAdOrBanner?.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_setting_back"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.val_fullscreen_setting_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}
