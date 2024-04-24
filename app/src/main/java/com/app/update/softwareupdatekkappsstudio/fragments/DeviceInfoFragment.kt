package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentBatteryInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentDeviceInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.info.PhoneDetail
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class DeviceInfoFragment : Fragment() {
    private lateinit var binding: FragmentDeviceInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()

                }
            })


        binding = FragmentDeviceInfoBinding.inflate(inflater,container,false)

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_deviceInfoFragment_to_appProFragment)
        }
        val phoneDetail = PhoneDetail(requireContext())
        binding.tvCompanyName.text = phoneDetail.deviceManufacturer
        binding.tvDeviceName.text = phoneDetail.deviceModel
        binding.tvDeviceName1.text = phoneDetail.deviceModel
        binding.tvDeviceModel.text = phoneDetail.deviceModel
        binding.tvDeviceManufacturer.text = phoneDetail.deviceManufacturer
        binding.tvDevice.text = phoneDetail.deviceModel
        binding.tvBoard.text = phoneDetail.boardName
        binding.tvHardware.text = phoneDetail.hardwareName
        binding.tvBrand.text = phoneDetail.deviceManufacturer
        binding.tvGSFId.text = phoneDetail.gsFrameworkId
        binding.tvGAdvertisingId.text = phoneDetail.advertisingId
        binding.tvDeviceId.text = phoneDetail.deviceId
        binding.tvHardwareSerial.text = phoneDetail.hardwareSerial
        binding.tvBuildFingerPrint.text = phoneDetail.buildFingerprint
        binding.tvDeviceType.text = phoneDetail.deviceType
        binding.tvNetworkOperator.text = phoneDetail.networkOperatorName
        binding.tvNetworkType.text = phoneDetail.networkType
        binding.tvWifiMacAddress.text = phoneDetail.wifiMacAddress
        if (phoneDetail.isUsbDebuggingEnabled) binding.tvUsbDebugging.text = "On" else binding.tvUsbDebugging.text = "Off"
        loadAds()
        return binding.root
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.deviceNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "SystemUpdate"
            ).setAdCallerName("SystemUpdate")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_device_details,
                    binding.deviceNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.deviceNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.deviceNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "SystemUpdate")
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.banner_device_details, // give remote config here
                                adsView = binding.deviceNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.deviceNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.deviceNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "Language").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
               Constants.fullscreen_device_details_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.deviceNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "SystemUpdate"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_device_details_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}