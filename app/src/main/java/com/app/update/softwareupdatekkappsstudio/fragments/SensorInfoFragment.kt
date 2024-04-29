package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSensorInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils


class SensorInfoFragment : Fragment() {
    private lateinit var binding: FragmentSensorInfoBinding


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
        binding = FragmentSensorInfoBinding.inflate(inflater, container, false)

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_sensorFragment_to_appProFragment)
        }
        loadAds()
        return binding.root
    }

    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.sensorNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "native_mobile_sensor"
            ).setAdCallerName("native_mobile_sensor")
                .loadNativeAd(
                    getString(R.string.val_native_mobile_sensor),
                    Constants.val_native_mobile_sensor,
                    binding.sensorNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.sensorNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.sensorNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "banner_mobile_sensor"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_mobile_sensor), // give ad id here
                                remoteConfig = Constants.val_banner_mobile_sensor, // give remote config here
                                adsView = binding.sensorNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.sensorNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.sensorNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(
                requireActivity(),
                "fullscreen_mobile_sensor_back"
            ).loadInterstitialAd(
                getString(R.string.val_fullscreen_device_sensor_back),
                Constants.val_fullscreen_mobile_sensor_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.sensorNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_mobile_sensor_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_device_sensor_back),
            Constants.val_fullscreen_mobile_sensor_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }

}