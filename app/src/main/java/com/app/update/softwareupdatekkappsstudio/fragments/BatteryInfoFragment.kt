package com.app.update.softwareupdatekkappsstudio.fragments

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentBatteryInfoBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class BatteryInfoFragment : Fragment() {
    private lateinit var binding:FragmentBatteryInfoBinding
    private val batteryStatsReceiver = object : BroadcastReceiver() {
        @SuppressLint("SetTextI18n")
        override fun onReceive(context: Context, intent: Intent) {

            if (Build.VERSION.SDK_INT >= 28) {
                val mBatteryManager =
                    context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
                mBatteryManager.computeChargeTimeRemaining()
            }


            val isPresent = intent.getBooleanExtra("present", false)
            val technology = intent.getStringExtra("technology")
            val plugged = intent.getIntExtra("plugged", -1)
            val scale = intent.getIntExtra("scale", -1)
            val health = intent.getIntExtra("health", 0)
            val status = intent.getIntExtra("status", 0)
            val rawLevel = intent.getIntExtra("level", -1)
            val voltage = intent.getIntExtra("voltage", 0)
            val fastChargeStatus = intent.getBooleanExtra("fastcharge_status", false)
            val temperature = intent.getIntExtra("temperature", 0)
            var level = 0

            if (isPresent) {
                if (rawLevel >= 0 && scale > 0) {
                    level = rawLevel * 100 / scale
                }
                binding.run {
                  //  batteryLevelIntVal.value = level
                    batteryLevelStringVal.text = (+level).toString() + "%"
                    if (technology != null)
                        batteryTypeVal.text = technology
                    else
                        batteryTypeVal.text = "Unknown"

                    batteryPowerSourceVal.text = getPlugTypeResultString(plugged)
                    batteryVoltageVal.text = Constants.STRING_EMPTY + voltage / 1000.0f + " V"
                    batteryStatusVal.text = getStatusResultString(status)
                    batteryHealthVal.text = getHealthResultString(health)
                  //  batteryFastChargingVal.text = Constants.STRING_EMPTY + fastChargeStatus

                    val tempInCelsius = temperature / 10.0f
                    val tempInFahrenheit = 9 / 5 * tempInCelsius + 32.0f

                    batteryTempVal.text =
                        Constants.STRING_EMPTY + tempInCelsius + " C / " + tempInFahrenheit + " F"
                }

            }
        }
    }
    private fun getPlugTypeResultString(plugged: Int): String {
        var plugType = Constants.UNKNOWN_VALUE
        when (plugged) {
            BatteryManager.BATTERY_PLUGGED_AC -> plugType = Constants.AC
            BatteryManager.BATTERY_PLUGGED_USB -> plugType = Constants.USB
        }
        return plugType
    }

    private fun getHealthResultString(health: Int): String {
        var healthString = Constants.UNKNOWN_VALUE

        when (health) {
            BatteryManager.BATTERY_HEALTH_DEAD -> healthString = Constants.DEAD
            BatteryManager.BATTERY_HEALTH_GOOD -> healthString = Constants.GOOD
            BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE -> healthString = Constants.OVERVOLTAGE
            BatteryManager.BATTERY_HEALTH_OVERHEAT -> healthString = Constants.OVERHEAT
            BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE -> healthString =
                Constants.FAILURE
        }
        return healthString
    }


    private fun getStatusResultString(status: Int): String {
        var statusString = Constants.UNKNOWN_VALUE

        when (status) {
            BatteryManager.BATTERY_STATUS_CHARGING -> statusString = Constants.CHARGING
            BatteryManager.BATTERY_STATUS_DISCHARGING -> statusString = Constants.DISCHARGING
            BatteryManager.BATTERY_STATUS_FULL -> statusString = Constants.FULL
            BatteryManager.BATTERY_STATUS_NOT_CHARGING -> statusString = Constants.NOT_CHARGING
        }
        return statusString
    }


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
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_batteryInfoFragment_to_appProFragment)
        }

        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        requireActivity().registerReceiver(batteryStatsReceiver, filter)


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