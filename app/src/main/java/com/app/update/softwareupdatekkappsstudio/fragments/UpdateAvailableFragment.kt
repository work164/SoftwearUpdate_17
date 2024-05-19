package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AvailableUpdateAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentUpdateAvailableBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.utils.navigateFragment
import com.app.update.softwareupdatekkappsstudio.view_model.ViewModelclass
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils


class UpdateAvailableFragment : Fragment() {
    private var binding: FragmentUpdateAvailableBinding? = null
    lateinit var viewModel: ViewModelclass

    private val appAdapter by lazy {
        AvailableUpdateAdapter(requireContext(), onItemClick = {
            navigateFragment(
                R.id.availableAppsUpdateFragment,
                R.id.action_availableAppsUpdateFragment_to_appDetailFragment,
                Bundle().apply {
                    putString("appPackageName", it.packageName)
                }


                /*
                *   /* val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                val dataset = Uri.parse("https://play.google.com/store/apps/details?id=" + item.packageName)
                intent.data = dataset
                try {
                    context.startActivity(intent)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }*/
                * */
            )

            InterstitialAdUtils(
                requireActivity(),
                "fullscreen_update_app_details"
            ).showInterstitialAd(
                getString(R.string.val_fullscreen_update_app_details),
                Constants.val_fullscreen_update_app_details,
                fullScreenAdShow = {
                    firebaseAnalytics(
                        "update_available_interstitial_failed",
                        "update_available_interstitial_failed"
                    )
                },
                fullScreenAdDismissed = {
                    firebaseAnalytics(
                        "update_available_interstitial_dismissed",
                        "update_available_interstitial_dismissed"
                    )
                },
                fullScreenAdFailedToShow = {
                    firebaseAnalytics(
                        "update_available_interstitial_failed_to_show",
                        "update_available_interstitial_failed_to_show"
                    )
                },
                fullScreenAdNotAvailable = {},
            )
        })
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
        // Inflate the layout for this fragment
        binding = FragmentUpdateAvailableBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ViewModelclass::class.java]
        viewModel.infoList1.value = ScanAppFragment.updateAvailableList
        setUpClickListeners()
        setUpRecyclerView()
        onserveViewModel()
        loadAds()

    }

    private fun onserveViewModel() {
        viewModel.infoList1.observe(viewLifecycleOwner) {
            appAdapter.setUpArrayList(it)
        }
    }

    private fun setUpRecyclerView() {
        binding?.run {
            rvUpdateAvailable.layoutManager = LinearLayoutManager(requireContext())
            rvUpdateAvailable.adapter = appAdapter
        }
    }

    private fun setUpClickListeners() {
        binding?.run {
            backDevice.setOnClickListener {
                findNavController().popBackStack()
                showAd()
            }
            btnInAppForwardDevice.setOnClickListener {
                findNavController().navigate(R.id.action_androidUpdateFragment_to_appProFragment)
            }
        }
    }

    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding?.updatesNativeAdOrBanner?.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "updatesNativeAdOrBanner"
            ).setAdCallerName("updatesNativeAdOrBanner")
                .loadNativeAd(
                    getString(R.string.val_native_scan_apps),
                    Constants.val_native_scan_apps,
                    binding?.updatesNativeAdOrBanner,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding?.updatesNativeAdOrBanner?.visibility = View.GONE
                    },
                    adValidate = {
                        binding?.updatesNativeAdOrBanner?.visibility = View.VISIBLE
                        BannerAdUtils(
                            activity = requireActivity(),
                            screenName = "updatesNativeAdOrBanner"
                        )
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_scan_apps), // give ad id here
                                remoteConfig = Constants.val_banner_scan_apps, // give remote config here
                                adsView = binding?.updatesNativeAdOrBanner
                                    ?: return@loadNativeAd, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding?.updatesNativeAdOrBanner?.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding?.updatesNativeAdOrBanner?.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "fullscreen_update_back").loadInterstitialAd(
                getString(R.string.val_fullscreen_update_app_details),
                if (Constants.val_fullscreen_update_back) Constants.val_fullscreen_update_back else Constants.val_fullscreen_update_app_details,
                adAlreadyLoaded = {

                },
                adLoaded = {
                    firebaseAnalytics(
                        "update_available_interstitial_loaded",
                        "update_available_interstitial_loaded"
                    )

                },
                adFailed = {
                    firebaseAnalytics(
                        "update_available_interstitial_failed",
                        "update_available_interstitial_failed"
                    )

                },
                adValidate = {},
            )
        } else {
            binding?.updatesNativeAdOrBanner?.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_update_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_update_app_details),
            Constants.val_fullscreen_update_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}