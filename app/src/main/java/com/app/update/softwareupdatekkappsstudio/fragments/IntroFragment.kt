package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.OnboardingPagerAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentIntroBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.model.OnboardingItem
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.example.adssdk.advert.PurchasePrefs
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils

class IntroFragment : Fragment() {

    private var onboardingPagerAdapter: OnboardingPagerAdapter? = null
    private var onboardingItems: ArrayList<OnboardingItem> = arrayListOf()

    private lateinit var binding: FragmentIntroBinding
    private var purchasePrefs: PurchasePrefs? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)
        firebaseAnalytics(
            "intro_on_create_view",
            "intro_on_create_view"
        )

        try {
            purchasePrefs = PurchasePrefs(requireContext())

            requireActivity().onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(false) {
                    override fun handleOnBackPressed() {

                        Log.d("onBackPressed", "Intro: ")

                    }

                })



            showOnboarding()
            binding.btnNext.setOnClickListener {

                if (binding.viewPager.currentItem < onboardingItems.size - 1) {
                    binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                } else {
                    showStartButton()
                    firebaseAnalytics(
                        "intro_click_next",
                        "intro_click_next"
                    )
                    Log.d("onBackPressed", "Intro:next ")

                }
            }
            binding.btnSkip.setOnClickListener {
                showStartButton()
                firebaseAnalytics(
                    "intro_click_skip",
                    "intro_click_skip"
                )
                Log.d("onBackPressed", "Intro:skip ")


            }

            if (AppUtils.isNetworkAvailable(requireContext())) {
                binding.introNativeAdOrBanner.visibility = View.VISIBLE
                val bindAdIntro = NativeWithOutMediaBinding.inflate(layoutInflater)
                NativeAdUtils(requireActivity().application, "Intro").setAdCallerName("Intro")
                    .loadNativeAd(
                        getString(R.string.val_native_intro),
                        Constants.val_native_intro,
                        binding.introNativeAdOrBanner,
                        bindAdIntro.root,
                        bindAdIntro.adAppIcon,
                        bindAdIntro.adHeadline,
                        bindAdIntro.adBody,
                        bindAdIntro.adCallToAction,
                        null,
                        null,
                        adFailed = {
                            binding.introNativeAdOrBanner.visibility = View.GONE
                            firebaseAnalytics(
                                "intro_native_failed",
                                "intro_native_failed"
                            )
                        },
                        adValidate = {
                            firebaseAnalytics(
                                "intro_native_validate",
                                "intro_native_validate"
                            )
                            binding.introNativeAdOrBanner.visibility = View.VISIBLE
                            BannerAdUtils(activity = requireActivity(), screenName = "Intro")
                                .loadBanner(
                                    adsKey = getString(R.string.val_banner_intro), // give ad id here
                                    remoteConfig = Constants.val_banner_intro, // give remote config here
                                    adsView = binding.introNativeAdOrBanner, //give your frameLayout here
                                    onAdClicked = {}, //if ad clicked you will receive this callback
                                    onAdFailedToLoad = {
                                        binding.introNativeAdOrBanner.visibility = View.GONE
                                    }, // if ad failed to load you will receive this callback
                                    onAdImpression = {}, // if ad impression will receive this callback
                                    onAdLoaded = {}, // if ad loaded you will receive this callback
                                    onAdOpened = {}, // if ad opened you will receive this callback
                                    onAdValidate = {
                                        binding.introNativeAdOrBanner.visibility = View.GONE
                                    }) //if remote off or no internet or user is premium user you will receive callback here

                        },
                        adClicked = {

                        },
                        adImpression = {

                        }
                    )
                InterstitialAdUtils(requireActivity(), "Language").loadInterstitialAd(
                    getString(R.string.val_fullscreen_intro),
                    Constants.val_fullscreen_intro,
                    adAlreadyLoaded = {
                        firebaseAnalytics(
                            "intro_interstitial_alre_loaded",
                            "intro_interstitial_alre_loaded"
                        )
                    },
                    adLoaded = {
                        firebaseAnalytics(
                            "intro_interstitial_loaded",
                            "intro_interstitial_loaded"
                        )

                    },
                    adFailed = {

                        firebaseAnalytics(
                            "intro_interstitial_failed",
                            "intro_interstitial_failed"
                        )
                    },
                    adValidate = {

                    },
                )
            } else {
                binding.introNativeAdOrBanner.visibility = View.GONE
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }


        return binding.root
    }


    private fun showOnboarding() {


        try {
            binding.btnSkip.visibility = View.VISIBLE
            onboardingItems = ArrayList()
            onboardingItems.add(
                OnboardingItem(
                    R.drawable.ic_frag1, """
         Easily all mobile apps Update in your
         Mobile Phone.
         """.trimIndent()
                )
            )
            onboardingItems.add(
                OnboardingItem(
                    R.drawable.ic_frag2, """
         Easily all mobile apps Installing in your
         Mobile Phone.
         """.trimIndent()
                )
            )
            onboardingItems.add(
                OnboardingItem(
                    R.drawable.ic_frag3, """
         Easily your mobile System Update your
         Mobile Phone.
         """.trimIndent()
                )
            )
            onboardingItems.add(
                OnboardingItem(
                    R.drawable.ic_frag4,
                    "Easily check your mobile System is Update."
                )
            )

            Log.d("workingPager", "${onboardingItems.size}")
            // Set up ViewPager and Dots
            onboardingPagerAdapter = OnboardingPagerAdapter(childFragmentManager, onboardingItems)
            binding.viewPager.adapter = onboardingPagerAdapter
            binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    if (position < 3) {
                        binding.btnNext.text = "Next"
                    } else {
                        binding.btnNext.text = "Let’s Start"


                    }

                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
            binding.indicatorLayout.attachTo(binding.viewPager)

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    private fun showStartButton() {

            findNavController().navigate(R.id.action_introFragment_to_homeFragment)
            purchasePrefs?.putBoolean("isFirstTime", true)
            showAd()

    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "Intro"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_intro),
            Constants.val_fullscreen_intro,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }
}