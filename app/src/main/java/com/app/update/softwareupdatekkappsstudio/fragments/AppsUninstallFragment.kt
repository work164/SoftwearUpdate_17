package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppsUninstallAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppInstalledBinding
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppsUninstallBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class AppsUninstallFragment : Fragment(), HomeClick {
    //    private var textView: TextView? = null
//    private var loadingApps: ProgressBar? = null
//    private var appsRecyclerView: RecyclerView? = null
    private val appsList: MutableList<AppInfo> = ArrayList()
    private var appsAdapter: AppsUninstallAdapter? = null
    private lateinit var binding: FragmentAppsUninstallBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()


                }
            })

        binding = FragmentAppsUninstallBinding.inflate(inflater, container, false)

        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_appUninstallFragment_to_appProFragment)
        }
        loadAds()

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        textView = view.findViewById(R.id.textView)
//        appsRecyclerView = view.findViewById(R.id.appsRecyclerView)
//        loadingApps = view.findViewById(R.id.loadingApps)
        binding.appsRecyclerView.layoutManager = LinearLayoutManager(activity)
        appsAdapter = AppsUninstallAdapter(this, appsList, requireActivity())
        binding.appsRecyclerView.adapter = appsAdapter
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                appsList.clear()
                val packageManager = activity?.packageManager
                val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
                for (app in apps ?: return@launch) {
                    val packageName = app.packageName
                    // Check if it's a user app OR a desired system app
                    if (app.flags and ApplicationInfo.FLAG_SYSTEM == 0 || DESIRED_SYSTEM_APPS.contains(
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
//                    binding.textView?.text = "Total Apps: " + appsList.size
                    appsAdapter?.notifyDataSetChanged()
                    binding.loadingApps?.visibility = View.GONE
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


    companion object {
        val DESIRED_SYSTEM_APPS: List<String> = mutableListOf(
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


    override fun onUninstallItemClick(name: String, packegeName: String, position: Int) {

        findNavController().navigate(
            R.id.action_appUninstallFragment_to_appDetailFragment,
            Bundle().apply {
                putString("appPackageName", packegeName)
            })
        InterstitialAdUtils(
            requireActivity(),
            "SystemUpdate"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_uninstall_app_details),
            Constants.val_fullscreen_uninstall_app_details,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )

        /* val builder = AlertDialog.Builder(
             requireContext()
         )
         builder.setTitle("Uninstall App")
         builder.setMessage("Do you want to uninstall $name?")
         builder.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
             val packageUri = Uri.parse("package:$packegeName")
             val uninstallIntent = Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri)
             startActivity(uninstallIntent)
             appsAdapter?.notifyDataSetChanged()
         }
         builder.setNegativeButton("Cancel", null)
         builder.show()*/



        super.onUninstallItemClick(name, packegeName, position)
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.uninstallNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "NativeWithOutMediaBinding"
            ).setAdCallerName("NativeWithOutMediaBinding")
                .loadNativeAd(
                    getString(R.string.val_native_uninstall),
                    Constants.val_native_uninstall,
                    binding.uninstallNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.uninstallNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.uninstallNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "banner_uninstall")
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_uninstall), // give ad id here
                                remoteConfig = Constants.val_banner_uninstall, // give remote config here
                                adsView = binding.uninstallNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.uninstallNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.uninstallNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "fullscreen_uninstall_app_back").loadInterstitialAd(
                getString(R.string.val_fullscreen_uninstall_app_details),
                if (Constants.val_fullscreen_uninstall_app_back) Constants.val_fullscreen_uninstall_app_back else Constants.val_fullscreen_uninstall_app_details,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding.uninstallNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_uninstall_app_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_uninstall_app_details),
            Constants.val_fullscreen_uninstall_app_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}