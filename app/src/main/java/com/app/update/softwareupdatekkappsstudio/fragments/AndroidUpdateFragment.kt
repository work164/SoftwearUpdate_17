package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAndroidUpdateBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class AndroidUpdateFragment : Fragment() {
    private lateinit var binding: FragmentAndroidUpdateBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAndroidUpdateBinding.inflate(inflater, container, false)



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()

                }
            })

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_androidUpdateFragment_to_appProFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOSNext.setOnClickListener {
            try {
                openSystemUpdateSettings()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        loadAds()
    }

    private fun openSystemUpdateSettings() {
        try {
            requireActivity().startActivityForResult(
                Intent("android.settings.SYSTEM_UPDATE_SETTINGS"),
                0
            )
        } catch (e: SecurityException) {
            e.printStackTrace()
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.updateNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "SystemUpdate"
            ).setAdCallerName("SystemUpdate")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_system_update,
                    binding.updateNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    bindAdSystemUpdate.adMedia,
                    null,
                    adFailed = {
                        binding.updateNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.updateNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "SystemUpdate")
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.banner_system_update, // give remote config here
                                adsView = binding.updateNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.updateNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.updateNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "Language").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                if (Constants.fullscreen_system_update_details) Constants.fullscreen_system_update_details else Constants.fullscreen_system_update_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.updateNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "SystemUpdate"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_system_update_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}