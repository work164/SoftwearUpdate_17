package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentPrivacyBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.utils.Constants.banner_privacy
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.native_ad.NativeAdUtils


class PrivacyFragment : Fragment() {

    private lateinit var binding: FragmentPrivacyBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentPrivacyBinding.inflate(inflater, container, false)

        binding.acceptBtn.setOnClickListener {
            findNavController().navigate(R.id.action_privacyFragment_to_introFragment)
        }
        val fullText = "By Continuing, You agree to terms of service and\nPrivacy Policy"
        val spannable = SpannableString(fullText)
        val startTerm = fullText.indexOf("terms of service")
        val endTerm = startTerm + "terms of service".length
        val startPrivacy = fullText.indexOf("Privacy Policy")
        val endPrivacy = startPrivacy + "Privacy Policy".length
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.link_color)),
            startTerm,
            endTerm,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(requireContext(), R.color.link_color)),
            startPrivacy,
            endPrivacy,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.privacyText.text = spannable
        requireActivity().onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(false) {
                override fun handleOnBackPressed() {
                    Log.d("onBackPressed", "Privacy: ")
                }

            })

        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.privacyNativeAdOrBanner.visibility = View.VISIBLE
            val bindAdPrivacy = NativeWithMediaBinding.inflate(layoutInflater)
            NativeAdUtils(requireActivity().application, "Privacy").setAdCallerName("Privacy")
                .loadNativeAd(
                    getString(R.string.native_id),
                    Constants.native_privacy,
                    binding.privacyNativeAdOrBanner,
                    bindAdPrivacy.root,
                    bindAdPrivacy.adAppIcon,
                    bindAdPrivacy.adHeadline,
                    bindAdPrivacy.adBody,
                    bindAdPrivacy.adCallToAction,
                    bindAdPrivacy.adMedia,
                    null,
                    adFailed = {
                        binding.privacyNativeAdOrBanner.visibility = View.GONE
                    },
                    adValidate = {
                        binding.privacyNativeAdOrBanner.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "Privacy")
                            .loadBanner(
                                adsKey = getString(R.string.admob_banner_id), // give ad id here
                                remoteConfig = banner_privacy, // give remote config here
                                adsView = binding.privacyNativeAdOrBanner, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.privacyNativeAdOrBanner.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.privacyNativeAdOrBanner.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )

        } else {
            binding.privacyNativeAdOrBanner.visibility = View.GONE
        }


        return binding.root
    }


}