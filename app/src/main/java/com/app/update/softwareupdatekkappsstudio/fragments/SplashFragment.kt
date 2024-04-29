package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSplashBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.model.RemoteModel
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.advert.PurchasePrefs
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.consentform.AdmobConsentForm
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import com.example.adssdk.remote_config.RemoteConfiguration
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var countdownTimer: CountDownTimer? = null
    private var shouldStart = false
    private var isPause = false
    private var isWaitForNative = true
    private var splashTime = 20000L
    private var purchasePrefs: PurchasePrefs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        purchasePrefs = PurchasePrefs(requireContext())

        try {
            requireActivity().onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(false) {
                    override fun handleOnBackPressed() {
                        Log.d("onBackPressed", "handleOnBackPressed: ")
                    }
                })
            binding.startButton.setOnClickListener {
                val fromSplashBundle = Bundle()
                fromSplashBundle.putString("from", "SplashFragment")
                findNavController().navigate(
                    R.id.action_splashFragment_to_languageFragment,
                    fromSplashBundle
                )
            }


            if (AppUtils.isNetworkAvailable(requireContext())) {
                splashTime = 20000L
                AdmobConsentForm.getInstance(requireActivity()).build()
                    .loadAndShowAdmobConsentForm {
                        showAndMove(splashTime)
                    }
            } else {
                splashTime = 4000L
                showAndMove(splashTime)
            }

            if (AppUtils.isNetworkAvailable(requireContext())) {

                RemoteConfiguration(requireActivity()).initializeConfig(
                    R.xml.remote_config_defaults,
                    "ads_update",
                    onSuccess = {
                        val data = Gson().fromJson(it, RemoteModel::class.java)
                         Constants.val_fullscreen_splash = data.val_fullscreen_splash?:true
                         Constants.val_fullscreen_language_from_app = data.val_fullscreen_language_from_app?:true
                         Constants.val_fullscreen_intro = data.val_fullscreen_intro?:true
                         Constants.val_fullscreen_main_menu_scan_apps = data.val_fullscreen_main_menu_scan_apps?:true
                         Constants.val_fullscreen_main_install_apps = data.val_fullscreen_main_install_apps?:true
                         Constants.val_fullscreen_main_uninstall = data.val_fullscreen_main_uninstall?:true
                         Constants.val_fullscreen_main_system_apps = data.val_fullscreen_main_system_apps?:true
                         Constants.val_fullscreen_main_app_usage = data.val_fullscreen_main_app_usage?:true
                         Constants.val_fullscreen_main_device_info = data.val_fullscreen_main_device_info?:true
                         Constants.val_fullscreen_main_system_update = data.val_fullscreen_main_system_update?:true
                         Constants.val_fullscreen_main_settings = data.val_fullscreen_main_settings?:true
                         Constants.val_fullscreen_main_sensor = data.val_fullscreen_main_sensor?:true
                         Constants.val_fullscreen_main_restore_apps = data.val_fullscreen_main_restore_apps?:true
                         Constants.val_fullscreen_main_battery = data.val_fullscreen_main_battery?:true
                         Constants.val_fullscreen_main_android_versions = data.val_fullscreen_main_android_versions?:true
                         Constants.val_fullscreen_main_load = data.val_fullscreen_main_load?:true
                         Constants.val_fullscreen_scan_back = data.val_fullscreen_scan_back?:true
                         Constants.val_fullscreen_scan_app_details = data.val_fullscreen_scan_app_details?:true
                         Constants.val_fullscreen_update_back = data.val_fullscreen_update_back?:true
                         Constants.val_fullscreen_update_app_details = data.val_fullscreen_update_app_details?:true
                         Constants.val_fullscreen_install_app_back = data.val_fullscreen_install_app_back?:true
                         Constants.val_fullscreen_install_app_details = data.val_fullscreen_install_app_details?:true
                         Constants.val_fullscreen_uninstall_app_back = data.val_fullscreen_uninstall_app_back?:true
                         Constants.val_fullscreen_uninstall_app_details = data.val_fullscreen_uninstall_app_details?:true
                         Constants.val_fullscreen_system_app_back = data.val_fullscreen_system_app_back?:true
                         Constants.val_fullscreen_system_app_details = data.val_fullscreen_system_app_details?:true
                         Constants.val_fullscreen_app_usage_back = data.val_fullscreen_app_usage_back?:true
                         Constants.val_fullscreen_app_usage_details = data.val_fullscreen_app_usage_details?:true
                         Constants.val_fullscreen_system_update_back = data.val_fullscreen_system_update_back?:true
                         Constants.val_fullscreen_system_update_details = data.val_fullscreen_system_update_details?:true
                         Constants.val_fullscreen_app_restore_back = data.val_fullscreen_app_restore_back?:true
                         Constants.val_fullscreen_app_restore_details = data.val_fullscreen_app_restore_details?:true
                         Constants.val_fullscreen_mobile_sensor_back = data.val_fullscreen_mobile_sensor_back?:true
                         Constants.val_fullscreen_android_details_back = data.val_fullscreen_android_details_back?:true
                         Constants.val_fullscreen_battery_details_back = data.val_fullscreen_battery_details_back?:true
                         Constants.val_fullscreen_device_details_back = data.val_fullscreen_device_details_back?:true
                         Constants.val_fullscreen_all_details_back = data.val_fullscreen_all_details_back?:true
                         Constants.val_fullscreen_setting_back = data.val_fullscreen_setting_back?:true
                         Constants.val_native_splash_top = data.val_native_splash_top?:true
                         Constants.val_native_splash_bottom = data.val_native_splash_bottom?:true
                         Constants.val_native_intro = data.val_native_intro?:true
                         Constants.val_native_language = data.val_native_language?:true
                         Constants.val_native_privacy = data.val_native_privacy?:true
                         Constants.val_native_main_menu_bottom = data.val_native_main_menu_bottom?:true
                         Constants.val_native_main_menu_top = data.val_native_main_menu_top?:true
                         Constants.val_native_setting = data.val_native_setting?:true
                         Constants.val_native_scan = data.val_native_scan?:true
                         Constants.val_native_scan_apps = data.val_native_scan_apps?:true
                         Constants.val_native_app_details = data.val_native_app_details?:true
                         Constants.val_native_uninstall = data.val_native_uninstall?:true
                         Constants.val_native_install = data.val_native_install?:true
                         Constants.val_native_system_apps = data.val_native_system_apps?:true
                         Constants.val_native_app_usage = data.val_native_app_usage?:true
                         Constants.val_native_device_details = data.val_native_device_details?:true
                         Constants.val_native_system_update = data.val_native_system_update?:true
                         Constants.val_native_mobile_sensor = data.val_native_mobile_sensor?:true
                         Constants.val_native_apps_restore = data.val_native_apps_restore?:true
                         Constants.val_native_battery_info = data.val_native_battery_info?:true
                         Constants.val_native_android_details = data.val_native_android_details?:true
                         Constants.val_native_exit = data.val_native_exit?:true
                         Constants.val_banner_intro = data.val_banner_intro?:true
                         Constants.val_banner_language = data.val_banner_language?:true
                         Constants.val_banner_privacy = data.val_banner_privacy?:true
                         Constants.val_banner_main_menu_bottom = data.val_banner_main_menu_bottom?:true
                         Constants.val_banner_main_menu_top = data.val_banner_main_menu_top?:true
                         Constants.val_banner_setting = data.val_banner_setting?:true
                         Constants.val_banner_scan = data.val_banner_scan?:true
                         Constants.val_banner_scan_apps = data.val_banner_scan_apps?:true
                         Constants.val_banner_app_details = data.val_banner_app_details?:true
                         Constants.val_banner_install = data.val_banner_install?:true
                         Constants.val_banner_uninstall = data.val_banner_uninstall?:true
                         Constants.val_banner_system_apps = data.val_banner_system_apps?:true
                         Constants.val_banner_app_usage = data.val_banner_app_usage?:true
                         Constants.val_banner_device_details = data.val_banner_device_details?:true
                         Constants.val_banner_system_update = data.val_banner_system_update?:true
                         Constants.val_banner_mobile_sensor = data.val_banner_mobile_sensor?:true
                         Constants.val_banner_apps_restore = data.val_banner_apps_restore?:true
                         Constants.val_banner_battery_info = data.val_banner_battery_info?:true
                         Constants.val_banner_android_details = data.val_banner_android_details?:true
                    },
                    onFailure = {

                    })
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }

    private fun showAndMove(timeIn: Long) {
        if (!shouldStart && AppUtils.isNetworkAvailable(requireContext())) {
            if (Constants.val_native_splash_bottom) binding.nativeSplash.visibility = View.GONE
            else binding.nativeSplashBottom.visibility = View.GONE
            val bindAdSplash = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(requireActivity().application, "Splash").setAdCallerName("Splash")
                .loadNativeAd(
                    if (Constants.val_native_splash_bottom) getString(R.string.native_id) else getString(R.string.native_id),
                    if (Constants.val_native_splash_bottom) Constants.val_native_splash_bottom else Constants.val_native_splash_top,
                    if (Constants.val_native_splash_bottom) binding.nativeSplashBottom else binding.nativeSplash,
                    bindAdSplash.root,
                    bindAdSplash.adAppIcon,
                    bindAdSplash.adHeadline,
                    bindAdSplash.adBody,
                    bindAdSplash.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        isWaitForNative = false
                        binding.nativeSplashBottom.visibility = View.INVISIBLE
                        binding.nativeSplash.visibility = View.INVISIBLE
                    },
                    adValidate = {
                        isWaitForNative = false
                        binding.nativeSplashBottom.visibility = View.INVISIBLE
                        binding.nativeSplash.visibility = View.INVISIBLE

                    },
                    adClicked = {
                        isWaitForNative = false

                    },
                    adImpression = {
                        isWaitForNative = false

                    }
                )
            InterstitialAdUtils(requireActivity(), "splash").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                Constants.val_fullscreen_splash,
                adAlreadyLoaded = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }


                },
                adLoaded = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }

                },
                adFailed = {


                },
                adValidate = {


                },
            )
        } else {
            isWaitForNative = false
        }
        shouldStart = true

        countdownTimer = object : CountDownTimer(timeIn, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                splashTime = millisUntilFinished
            }

            override fun onFinish() {
                if (!isPause) {

                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }
                }

            }

        }
        countdownTimer?.start()

    }

    private fun moveNext() {
        val fromSplashBundle = Bundle()
        fromSplashBundle.putString("from", "SplashFragment")
        if (purchasePrefs?.getBoolean("isFirstTime") != true) {
            findNavController().navigate(
                R.id.action_splashFragment_to_languageFragment,
                fromSplashBundle
            )
        } else {
            findNavController().navigate(
                R.id.action_splashFragment_to_homeFragment
            )
        }
        InterstitialAdUtils(
            requireActivity(),
            "splash"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.val_fullscreen_splash,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }

    override fun onPause() {
        Log.d("TAGSplash", "cancel.")
        firebaseAnalytics(
            "splashOnPause",
            "splashOnPause-->In splash"
        )
        isPause = true
        countdownTimer?.cancel()
        super.onPause()
    }

    override fun onResume() {
        isPause = false
        if (shouldStart) {
            showAndMove(splashTime)
        }
        super.onResume()
    }

    override fun onDestroy() {
        countdownTimer?.cancel()
        isPause = true
        Log.d("TAGSplash", "onDestroy.")
        firebaseAnalytics(
            "splashOnDestroy",
            "splashOnDestroy-->In splash"
        )
        super.onDestroy()
    }

}