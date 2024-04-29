package com.app.update.softwareupdatekkappsstudio.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.MyApp
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.database.Word
import com.app.update.softwareupdatekkappsstudio.database.WordViewModel
import com.app.update.softwareupdatekkappsstudio.database.WordViewModelFactory
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppDetailsBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.utils.Constants.fullscreen_all_details_back
import com.app.update.softwareupdatekkappsstudio.utils.toSizeString
import com.bumptech.glide.Glide
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppDetailsFragment : Fragment() {


    private var binding: FragmentAppDetailsBinding? = null
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }
    private val appPackageName by lazy {
        arguments?.getString("appPackageName")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAppDetailsBinding.inflate(inflater, container, false)


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    showAd()

                }
            })

        binding?.backDevice?.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding?.btnInAppForwardDevice?.setOnClickListener {
            findNavController().navigate(R.id.action_appDetailFragment_to_appProFragment)
        }
        getAppDetailsByPackageName(appPackageName ?: "", requireContext().packageManager)
        setUpClickListeners()

        loadAds()
        // Inflate the layout for this fragment
        return binding?.root
    }

    private fun setUpClickListeners() {

        binding?.run {
            ifvAppDetailLaunch.setOnClickListener {
                launchApp(
                    appPackageName ?: return@setOnClickListener,
                    requireContext().packageManager,
                    requireContext()
                )
            }
            ifvAppDetailShare.setOnClickListener {
                shareAppDetails(requireContext(), appPackageName ?: return@setOnClickListener)

            }
            ifvAppDetailUninstall.setOnClickListener {
                uninstallApp(appPackageName ?: return@setOnClickListener)
            }
            tvViewOnPlayStore.setOnClickListener {
                openPlayStore(requireContext(), appPackageName ?: return@setOnClickListener)
            }
        }
    }

    private fun openPlayStore(context: Context, packageName: String) {
        try {
            val rateUs = Intent(Intent.ACTION_VIEW)
            rateUs.data =
                Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            context.startActivity(rateUs)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getAppDetailsByPackageName(packageName: String, packageManager: PackageManager) {
        try {
            val applicationInfo: ApplicationInfo? =
                packageManager.getApplicationInfo(packageName, 0)
            if (applicationInfo != null) {
                val appName: String = packageManager.getApplicationLabel(applicationInfo).toString()
                val appIcon = packageManager.getApplicationIcon(applicationInfo)
                val appSize = File(applicationInfo.sourceDir).length() // Size in bytes
                val packageInfo = packageManager.getPackageInfo(packageName, 0)
                val appVersion = packageInfo.versionName
                val lastUpdateTime = packageInfo.lastUpdateTime

                // Convert last update time to a readable format (e.g., Date object)
                val lastUpdateDate = Date(lastUpdateTime)

                binding?.run {
                    Glide.with(this@AppDetailsFragment).load(appIcon).into(ifvAppIcon)
                    tvAppName.text = appName
                    tvAppSize.text = appSize.toSizeString()
                    tvVersion.text = appVersion
                    tvLastUpdateValue.text = formatDate(lastUpdateDate)

                }

            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun launchApp(packageName: String, packageManager: PackageManager, context: Context) {
        val intent = packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } else {
            // App not found or unable to launch
            Toast.makeText(context, "Unable to launch app", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareAppDetails(context: Context, packageName: String) {
        val appDetailsIntent = Intent(Intent.ACTION_SEND)
        appDetailsIntent.type = "text/plain"
        val appLink = "https://play.google.com/store/apps/details?id=$packageName"
        val shareMessage = "Check out this app: $appLink"
        appDetailsIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
        context.startActivity(Intent.createChooser(appDetailsIntent, "Share via"))
    }

    // Function to uninstall app
    private fun uninstallApp(packageName: String) {
        try {
            val packageUri = Uri.parse("package:$packageName")
            val uninstallIntent = Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri)
            uninstallIntent.putExtra(Intent.EXTRA_RETURN_RESULT, true)
            startActivityForResult(uninstallIntent, UNINSTALL_REQUEST_CODE)
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == UNINSTALL_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                // Uninstallation successful
                val word = Word("reply","reply","reply","reply",)
                wordViewModel.insert(word)
                findNavController().popBackStack()
            } else {
                // Uninstallation failed or user canceled
                // Handle accordingly (optional)
            }
        }
    }

    private fun formatDate(date: Date): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return sdf.format(date)
    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding?.appDetailsNativeAdOrBanner?.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "appDetailsNativeAdOrBanner"
            ).setAdCallerName("appDetailsNativeAdOrBanner")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_app_details,
                    binding?.appDetailsNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    bindAdSystemUpdate.adMedia,
                    null,
                    adFailed = {
                        binding?.appDetailsNativeAdOrBanner?.visibility = View.GONE
                    },
                    adValidate = {
                        binding?.appDetailsNativeAdOrBanner?.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "appDetailsNativeAdOrBanner"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = Constants.banner_app_details, // give remote config here
                                adsView = binding?.appDetailsNativeAdOrBanner
                                    ?: return@loadNativeAd, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding?.appDetailsNativeAdOrBanner?.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding?.appDetailsNativeAdOrBanner?.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "appDetailsNativeAdOrBanner").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                fullscreen_all_details_back,
                adAlreadyLoaded = {

                },
                adLoaded = {


                },
                adFailed = {


                },
                adValidate = {},
            )
        } else {
            binding?.appDetailsNativeAdOrBanner?.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "appDetailsNativeAdOrBanner"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
              Constants.fullscreen_all_details_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}

const val UNINSTALL_REQUEST_CODE = 1