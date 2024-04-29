package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppsAdapter
import com.app.update.softwareupdatekkappsstudio.adapter.AppsUninstallAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSystemAppBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.model.SystemModel
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executors

class SystemAppFragment : Fragment(), HomeClick {
    private lateinit var binding: FragmentSystemAppBinding

    private val executorService = Executors.newSingleThreadExecutor()

    private val appsList: MutableList<AppInfo> = ArrayList()
    private var appsAdapter: AppsAdapter? = null


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

        binding = FragmentSystemAppBinding.inflate(inflater, container, false)

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_systemAppsFragment_to_appProFragment)
        }

        binding.appsRecyclerView.layoutManager = LinearLayoutManager(activity)
        appsList.clear()
        appsAdapter = AppsAdapter(this, appsList, requireActivity())
        binding.appsRecyclerView.adapter = appsAdapter
        loadInstalledApps()
        loadAds()
        return binding.root
    }


    private fun loadInstalledApps() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val packageManager = activity?.packageManager
                val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
                for (app in apps ?: return@launch) {
                    val packageName = app.packageName
                    // Check if it's a user app OR a desired system app
                    if (app.flags and ApplicationInfo.FLAG_SYSTEM > 0 || AppsUninstallFragment.DESIRED_SYSTEM_APPS.contains(
                            packageName
                        )

                    ) {
                        val appName = packageManager.getApplicationLabel(app) as String
                        val appIcon = packageManager.getApplicationIcon(app)
                        if (isAvailableOnPlayStore(packageName)) {
                            appsList.add(AppInfo(appName, appIcon, packageName))
                        }
                    }
                }
                // Sort apps alphabetically by their names
                appsList.sortWith(Comparator { o1: AppInfo, o2: AppInfo ->
                    o1.appName.compareTo(
                        o2.appName,
                        ignoreCase = true
                    )
                })
                CoroutineScope(Dispatchers.Main).launch {
                    binding.textView.text = "Total Apps: ${appsList.size}"
                    appsAdapter?.notifyDataSetChanged()
                    binding.loadingApps.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isAvailableOnPlayStore(packageName: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        val activities = activity?.packageManager?.queryIntentActivities(intent, 0)
        return activities?.isNotEmpty() ?: return false
    }

    override fun onUninstallItemClick(name: String, packegeName: String, position: Int) {

        findNavController().navigate(
            R.id.action_systemAppsFragment_to_appDetailFragment,
            Bundle().apply {
                putString("appPackageName", packegeName)
            })

    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.systemAppsNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "native_system_apps"
            ).setAdCallerName("native_system_apps")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_system_apps,
                    binding.systemAppsNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.systemAppsNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.systemAppsNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "banner_system_apps"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.banner_system_apps, // give remote config here
                                adsView = binding.systemAppsNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.systemAppsNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.systemAppsNativeAdOrBanner.visibility = View.GONE
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
                if (Constants.fullscreen_system_app_back) Constants.fullscreen_system_app_back else Constants.fullscreen_system_app_details,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.systemAppsNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_system_app_back"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_system_app_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}