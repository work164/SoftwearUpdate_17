package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.LanguageAdaptor
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentLanguageBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.utils.Constants.banner_language
import com.app.update.softwareupdatekkappsstudio.utils.Constants.fullscreen_language_from_app
import com.app.update.softwareupdatekkappsstudio.utils.Constants.native_language
import com.app.update.softwareupdatekkappsstudio.utils.setLocale
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LanguageFragment : Fragment() {
    private lateinit var binding: FragmentLanguageBinding
    private var languageCode = "en"

    private val languageAdaptor by lazy {
        LanguageAdaptor(requireContext(), "en", onItemClick = {
            languageCode = it.languageCode
        })
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (arguments?.getString("from") != "SplashFragment") {
                        findNavController().popBackStack()
                        showAd()
                    }
                }
            })

        binding = FragmentLanguageBinding.inflate(inflater, container, false)

        setUpClickListeners()
        setUpRecyclerView()
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.languageNativeOrBanner.visibility = View.VISIBLE
            val bindAdLanguage = NativeWithMediaBinding.inflate(layoutInflater)
            NativeAdUtils(requireActivity().application, "Language").setAdCallerName("Language")
                .loadNativeAd(
                    getString(R.string.native_id),
                    native_language,
                    binding.languageNativeOrBanner,
                    bindAdLanguage.root,
                    bindAdLanguage.adAppIcon,
                    bindAdLanguage.adHeadline,
                    bindAdLanguage.adBody,
                    bindAdLanguage.adCallToAction,
                    bindAdLanguage.adMedia,
                    null,
                    adFailed = {
                        binding.languageNativeOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.languageNativeOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "Language")
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = banner_language, // give remote config here
                                adsView = binding.languageNativeOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.languageNativeOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.languageNativeOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )

        } else {
            binding.languageNativeOrBanner.visibility = View.GONE
        }

        return binding.root
    }

    private fun setUpClickListeners() {
        //TODO Back press Ads review
        binding.run {
            ifvBack.apply {
                if (arguments?.getString("from") != "SplashFragment") {
                    visibility = View.VISIBLE
                    InterstitialAdUtils(requireActivity(), "Language").loadInterstitialAd(
                        getString(R.string.admob_splash_fullscreen),
                        fullscreen_language_from_app,
                        adAlreadyLoaded = {

                        },
                        adLoaded = {


                        },
                        adFailed = {


                        },
                        adValidate = {},
                    )
                } else {
                    visibility = View.GONE
                }
                setOnClickListener {
                    findNavController().popBackStack()
                    showAd()

                }
            }
            binding.changeLanguage.setOnClickListener {
                //sharedPreference.isLanguageCode = languageCode
                setLocale(requireContext(), languageCode)
                if (arguments?.getString("from") == "SplashFragment") {
                    findNavController().navigate(R.id.action_languageFragment_to_privacyFragment)

                } else {
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.rvLanguage.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvLanguage.adapter = languageAdaptor
    }

    fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "Language"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            fullscreen_language_from_app,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }

}