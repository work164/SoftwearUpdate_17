package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentBatteryInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class BatteryInfoFragment : Fragment() {
    private lateinit var binding:FragmentBatteryInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()

                }
            })
        binding = FragmentBatteryInfoBinding.inflate(inflater,container,false)

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }

        loadAds()
        return binding.root
    }
    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.batteryNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "batteryNativeAdOrBanner"
            ).setAdCallerName("batteryNativeAdOrBanner")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_battery_info,
                    binding.batteryNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,

                    null,
                    null,
                    adFailed = {
                        binding.batteryNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.batteryNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "batteryNativeAdOrBanner")
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.banner_battery_info, // give remote config here
                                adsView = binding.batteryNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.batteryNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.batteryNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "batteryNativeAdOrBanner").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                 Constants.fullscreen_battery_details_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.batteryNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "batteryNativeAdOrBanner"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_battery_details_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}