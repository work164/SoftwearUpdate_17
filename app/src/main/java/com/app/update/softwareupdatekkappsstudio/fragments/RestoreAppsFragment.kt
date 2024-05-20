package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.MyApp
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.UninstalledAppAdapter
import com.app.update.softwareupdatekkappsstudio.database.WordViewModel
import com.app.update.softwareupdatekkappsstudio.database.WordViewModelFactory
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentRestoreAppsBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils


class RestoreAppsFragment : Fragment() {

    private lateinit var binding: FragmentRestoreAppsBinding

    private var appsAdapter: UninstalledAppAdapter? = null

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((requireActivity().application as MyApp).repository)
    }

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

        binding = FragmentRestoreAppsBinding.inflate(inflater, container, false)
        binding.backDevice.setOnClickListener {
            findNavController().popBackStack()
            showAd()
        }
        binding.btnInAppForwardDevice.setOnClickListener {
            findNavController().navigate(R.id.action_restoreAppsFragment_to_appProFragment)
        }
        //TODO Ad and Back emd Remaining
        /*
        *
        *  InterstitialAdUtils(
            requireActivity(),
            "SystemUpdate"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            Constants.fullscreen_app_restore_details,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
        *
        * */

        loadAds()
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel.allWords.observe(viewLifecycleOwner) { words ->
            binding.loadingApps.visibility = View.GONE
            if (words.isNotEmpty()) {
                binding.tvEmpty.visibility = View.INVISIBLE
                appsAdapter = UninstalledAppAdapter(onItemClick = {
                    openUrl(requireContext(), "https://play.google.com/store/apps/details?id=$it")
                }, words, requireContext())
                binding.appsRecyclerView.layoutManager = LinearLayoutManager(activity)

                binding.appsRecyclerView.adapter = appsAdapter
            } else {
                binding.tvEmpty.visibility = View.VISIBLE

            }
        }

    }

    fun openUrl(context: Context, url: String) {
        try {
            val shareIntent = Intent(Intent.ACTION_VIEW)
            shareIntent.data = Uri.parse(url)
            context.startActivity(shareIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "No browser found", Toast.LENGTH_SHORT).show()
            e.printStackTrace()

        }
    }

    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.restoreNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "native_apps_restore"
            ).setAdCallerName("native_apps_restore")
                .loadNativeAd(
                    getString(R.string.val_native_apps_restore),
                    Constants.val_native_apps_restore,
                    binding.restoreNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.restoreNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.restoreNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "restoreNativeAdOrBanner"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_apps_restore), // give ad id here
                                remoteConfig = Constants.val_banner_apps_restore, // give remote config here
                                adsView = binding.restoreNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.restoreNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.restoreNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(
                requireActivity(),
                "fullscreen_app_restore_back"
            ).loadInterstitialAd(
                getString(R.string.val_fullscreen_app_restore_details),
                if (Constants.val_fullscreen_app_restore_back) Constants.val_fullscreen_app_restore_back else Constants.val_fullscreen_app_restore_details,
                adAlreadyLoaded = {

                },
                adLoaded = {
                    firebaseAnalytics(
                        "restore_app_interstitial_loaded",
                        "restore_app_interstitial_loaded"
                    )

                },
                adFailed = {
                    firebaseAnalytics(
                        "restore_app_interstitial_failed",
                        "restore_app_interstitial_failed"
                    )

                },
                adValidate = {},
            )
        } else {
            binding.restoreNativeAdOrBanner.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_app_restore_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_app_restore_details),
            Constants.val_fullscreen_app_restore_back,
            fullScreenAdShow = {
                firebaseAnalytics(
                    "restore_app_interstitial_failed",
                    "restore_app_interstitial_failed"
                )
            },
            fullScreenAdDismissed = {
                firebaseAnalytics(
                    "restore_app_interstitial_dismissed",
                    "restore_app_interstitial_dismissed"
                )
            },
            fullScreenAdFailedToShow = {
                firebaseAnalytics(
                    "restore_app_interstitial_failed_to_show",
                    "restore_app_interstitial_failed_to_show"
                )
            },
            fullScreenAdNotAvailable = {},
        )
    }
}