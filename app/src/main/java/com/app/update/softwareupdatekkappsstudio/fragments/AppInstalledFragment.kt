package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.MyApp
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppsAdapter
import com.app.update.softwareupdatekkappsstudio.adapter.UninstalledAppAdapter
import com.app.update.softwareupdatekkappsstudio.database.WordViewModel
import com.app.update.softwareupdatekkappsstudio.database.WordViewModelFactory
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppInstalledBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections
import java.util.concurrent.Executors

class AppInstalledFragment : Fragment(), HomeClick {

    private val executorService = Executors.newSingleThreadExecutor()
    private val appsList: MutableList<AppInfo> = ArrayList()
    private var appsAdapter: AppsAdapter? = null
    private lateinit var binding: FragmentAppInstalledBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAppInstalledBinding.inflate(inflater, container, false)



        loadAds()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    firebaseAnalytics(
                        "app_installed_press_back",
                        "app_installed_press_back"
                    )
                    findNavController().popBackStack()
                    showAd()

                }
            })

        binding.backDevice.setOnClickListener {
            firebaseAnalytics(
                "app_installed_click_ui_back",
                "app_installed_click_ui_back"
            )
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            firebaseAnalytics(
                "app_installed_click_pro",
                "app_installed_click_pro"
            )
            findNavController().navigate(R.id.action_appCounterFragment_to_appProFragment)
        }
        appsAdapter = AppsAdapter(this,appsList, requireActivity())
        binding.appsRecyclerView.layoutManager = LinearLayoutManager(activity)

        binding.appsRecyclerView.adapter = appsAdapter
        showNumberOfInstalledApps()
        CoroutineScope(Dispatchers.IO).launch {
            loadInstalledAppsInBackground()
        }
    }

    private fun loadInstalledAppsInBackground() {
        val future = executorService.submit<List<AppInfo>> { loadInstalledApps() }
        try {
            val loadedApps = future.get()
            appsList.clear()
            appsList.addAll(loadedApps)

            // Update the UI on the main thread
            CoroutineScope(Dispatchers.Main).launch {
                binding.textView.text = "Total Apps: ${appsList.size}"
                appsAdapter?.notifyDataSetChanged()
                binding.loadingApps.visibility =  View.GONE

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUninstallItemClick(name: String, packegeName: String, position: Int) {
        firebaseAnalytics(
            "app_installed_click_item",
            "app_installed_click_item"
        )
        findNavController().navigate(R.id.action_appCounterFragment_to_appDetailFragment, Bundle().apply {
            putString("appPackageName", packegeName)
        })

        InterstitialAdUtils(
            requireActivity(),

            "fullscreen_install_app_details"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_install_app_details),
            Constants.val_fullscreen_install_app_details,
            fullScreenAdShow = {
                firebaseAnalytics(
                    "app_installed_interstitial_failed",
                    "app_installed_interstitial_failed"
                )
            },
            fullScreenAdDismissed = {
                firebaseAnalytics(
                    "app_installed_interstitial_dismissed",
                    "app_installed_interstitial_dismissed"
                )
            },
            fullScreenAdFailedToShow = {
                firebaseAnalytics(
                    "app_installed_interstitial_failed_to_show",
                    "app_installed_interstitial_failed_to_show"
                )
            },
            fullScreenAdNotAvailable = {},
        )

        /*
         val intent = Intent(Intent.ACTION_VIEW)
         intent.data = Uri.parse("market://details?id=$packegeName")
         intent.setPackage("com.android.vending") // Explicitly set the Play Store app to handle the intent
         startActivity(intent)
         */
        super.onUninstallItemClick(name, packegeName, position)
    }
    private fun loadInstalledApps(): List<AppInfo> {
        val appsList: MutableList<AppInfo> = ArrayList()
        val packageManager = activity?.packageManager
        val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
        for (app in apps ?: Collections.emptyList()) {
            val packageName = app.packageName
            // Check if it's a user app OR a desired system app
            if (app.flags and ApplicationInfo.FLAG_SYSTEM == 0 || DESIRED_SYSTEM_APPS.contains(packageName)) {
                val appName = packageManager?.getApplicationLabel(app) as String
                val appIcon = packageManager.getApplicationIcon(app)
                if (isAvailableOnPlayStore(packageName)) {
                    appsList.add(AppInfo(appName, appIcon, packageName))
                }
            }
        }

        // Sort apps alphabetically by their names
        appsList.sortWith { o1: AppInfo, o2: AppInfo ->
            o1.appName.compareTo(
                o2.appName,
                ignoreCase = true
            )
        }
        return appsList
    }

    private fun isAvailableOnPlayStore(packageName: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        val activities = activity?.packageManager?.queryIntentActivities(intent, 0)
        return activities?.isNotEmpty() ?: false
    }


    private fun showNumberOfInstalledApps() {

    }
    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.installAppsNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "installAppsNativeAdOrBanner"
            ).setAdCallerName("installAppsNativeAdOrBanner")
                .loadNativeAd(
                    getString(R.string.val_native_install),
                    Constants.val_native_install,
                    binding.installAppsNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        firebaseAnalytics(
                            "app_installed_native_failed",
                            "app_installed_native_failed"
                        )
                        binding.installAppsNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.installAppsNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "installAppsNativeAdOrBanner")
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_install), // give ad id here
                                remoteConfig = Constants.val_banner_install, // give remote config here
                                adsView = binding.installAppsNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {firebaseAnalytics(
                                    "app_installed_banner_failed",
                                    "app_installed_banner_failed"
                                )
                                    binding.installAppsNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {firebaseAnalytics(
                                    "app_installed_banner_loaded",
                                    "app_installed_banner_loaded"
                                )}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.installAppsNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adLoaded = {
                        firebaseAnalytics(
                            "app_installed_native_loaded",
                            "app_installed_native_loaded"
                        )
                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "installAppsNativeAdOrBanner").loadInterstitialAd(
                getString(R.string.val_fullscreen_install_app_details),
                if (Constants.val_fullscreen_install_app_back) Constants.val_fullscreen_install_app_back else Constants.val_fullscreen_install_app_details,
                adAlreadyLoaded = {

                },
                adLoaded = {
                    firebaseAnalytics(
                        "app_installed_interstitial_loaded",
                        "app_installed_interstitial_loaded"
                    )

                },
                adFailed = {
                    firebaseAnalytics(
                        "app_installed_interstitial_failed",
                        "app_installed_interstitial_failed"
                    )

                },
                adValidate = {},
            )
        } else {
            binding.installAppsNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_install_app_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_install_app_details),
            Constants.val_fullscreen_install_app_back,
            fullScreenAdShow = {
                firebaseAnalytics(
                    "app_installed_interstitial_failed",
                    "app_installed_interstitial_failed"
                )
            },
            fullScreenAdDismissed = {
                firebaseAnalytics(
                    "app_installed_interstitial_dismissed",
                    "app_installed_interstitial_dismissed"
                )
            },
            fullScreenAdFailedToShow = {
                firebaseAnalytics(
                    "app_installed_interstitial_failed_to_show",
                    "app_installed_interstitial_failed_to_show"
                )
            },
            fullScreenAdNotAvailable = {},
        )
    }
    companion object {
        private val DESIRED_SYSTEM_APPS: List<String> = mutableListOf(
            "com.google.android.googlequicksearchbox",  // Google
            "com.facebook.katana",  // Facebook
            "com.google.android.youtube",  // YouTube
            "com.google.android.apps.docs",  // Docs
            "com.google.android.apps.photos",  // Photos
            "com.google.android.gm",  // Gmail
            "com.google.android.apps.drive",  // Drive
            "com.android.chrome",  // Google Chrome
            "com.google.android.apps.maps",  // Google Maps
            "com.google.android.apps.weather" // Weather - Note: this package might differ based on the actual Weather app
        )
    }
}