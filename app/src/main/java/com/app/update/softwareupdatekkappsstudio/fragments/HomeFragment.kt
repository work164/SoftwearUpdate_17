package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.HomeAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentHomeBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), HomeClick {
    private lateinit var binding: FragmentHomeBinding
    private var mList = arrayListOf(
        HomeViewModel(R.drawable.ic_home_scan, "Scan Apps", "", 0),
        HomeViewModel(R.drawable.ic_home_install, "Installed Apps", "", 1),
        HomeViewModel(R.drawable.ic_home_uninstall, "Uninstall Apps", "", 2),
        HomeViewModel(R.drawable.ic_home_system, "System Apps", "", 3),
        HomeViewModel(R.drawable.ic_home_app_usage, "App Usage", "", 4),
        HomeViewModel(R.drawable.ic_device_info, "Device Info", "", 5),
        HomeViewModel(R.drawable.ic_home_system_update, "System Update", "", 6),
        HomeViewModel(R.drawable.ic_home_wifi, "Wifi Status", "", 7),
        HomeViewModel(R.drawable.ic_home_mobile_sensor, "Mobile Sensor", "", 8),
        HomeViewModel(R.drawable.ic_home_app_restore, "App Restore", "", 9),
        HomeViewModel(R.drawable.ic_home_battery, "Battery Info", "", 10),
        HomeViewModel(R.drawable.ic_home_android_version, "Android Version", "", 11)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            setting.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
                showAd()
            }
            giftHome.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_appProFragment)

            }
        }
        if (AppUtils.isNetworkAvailable(requireContext())) {
            if (Constants.native_main_menu_top) binding.nativeMain.visibility = View.GONE
            else binding.nativeMainBottom.visibility = View.GONE
            val bindAdMainMenu = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(requireActivity().application, "MainMenu").setAdCallerName("MainMenu")
                .loadNativeAd(
                    if (Constants.native_main_menu_top) getString(R.string.native_id) else getString(
                        R.string.native_id
                    ),
                    if (Constants.native_main_menu_top) Constants.native_main_menu_top else Constants.native_main_menu_bottom,
                    if (Constants.native_main_menu_top) binding.nativeMainBottom else binding.nativeMain,
                    bindAdMainMenu.root,
                    bindAdMainMenu.adAppIcon,
                    bindAdMainMenu.adHeadline,
                    bindAdMainMenu.adBody,
                    bindAdMainMenu.adCallToAction,
                    null,
                    null,
                    adFailed = {

                        binding.nativeMainBottom.visibility = View.GONE
                        binding.nativeMain.visibility = View.GONE
                    },
                    adValidate = {
                        if (Constants.banner_main_menu_top) binding.nativeMain.visibility =
                            View.GONE
                        else binding.nativeMainBottom.visibility = View.GONE
                        BannerAdUtils(activity = requireActivity(), screenName = "MainMenu")
                            .loadBanner(
                                adsKey = if (Constants.banner_main_menu_top) getString(R.string.admob_banner_id) else getString(
                                    R.string.admob_banner_id
                                ), // give ad id here
                                remoteConfig = if (Constants.banner_main_menu_top) Constants.banner_main_menu_top else Constants.banner_main_menu_bottom, // give remote config here
                                adsView = if (Constants.banner_main_menu_top) binding.nativeMainBottom else binding.nativeMain, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.nativeMainBottom.visibility = View.GONE
                                    binding.nativeMain.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.nativeMainBottom.visibility = View.GONE
                                    binding.nativeMain.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here


                    },
                    adClicked = {


                    },
                    adImpression = {


                    }
                )
            InterstitialAdUtils(requireActivity(), "MainMenu").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                Constants.fullscreen_main_menu,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {


                },
            )
        } else {
            binding.nativeMainBottom.visibility = View.GONE
            binding.nativeMain.visibility = View.GONE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = HomeAdapter(requireContext(), mList, this)
    }

    override fun onItemClick(name: String, position: Int) {
        Log.i("TAGHome", "onItemClick: $name position $position")
        when (position) {
            0 -> {
                findNavController().navigate(R.id.action_homeFragment_to_scanFragment)
                showAd()
            }

            1 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appInstalledFragment)
                showAd()
            }

            2 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appUninstallFragment)
                showAd()
            }

            3 -> {
                findNavController().navigate(R.id.action_homeFragment_to_systemAppsFragment)
                showAd()
            }

            4 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appUsageFragment)
                showAd()
            }

            5 -> {
                findNavController().navigate(R.id.action_homeFragment_to_deviceInfoFragment)
//                findNavController().navigate(R.id.action_homeFragment_to_deviceInformationFragment)
                showAd()
            }

            6 -> {
                findNavController().navigate(R.id.action_homeFragment_to_androidUpdateFragment)
                showAd()
            }

            7 -> {
                findNavController().navigate(R.id.action_homeFragment_to_wifiFragment)
                showAd()
            }

            8 -> {
                findNavController().navigate(R.id.action_homeFragment_to_sensorFragment)
                showAd()
            }

            9 -> {
                findNavController().navigate(R.id.action_homeFragment_to_restoreAppsFragment)
                showAd()
            }

            10 -> {
                findNavController().navigate(R.id.action_homeFragment_to_batteryInfoFragment)
                showAd()
            }

            11 -> {
                findNavController().navigate(R.id.action_homeFragment_to_androidVersionFragment)
                showAd()
            }

            else -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
                showAd()
            }


        }



        super.onItemClick(name, position)
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "MainMenu"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_main_menu,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}