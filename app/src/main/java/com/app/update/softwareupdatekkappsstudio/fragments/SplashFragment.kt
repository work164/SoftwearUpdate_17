package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSplashBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.utils.Constants.fullscreen_splash
import com.app.update.softwareupdatekkappsstudio.utils.Constants.native_splash_bottom
import com.app.update.softwareupdatekkappsstudio.utils.Constants.native_splash_top
import com.example.adssdk.advert.PurchasePrefs
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.consentform.AdmobConsentForm
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    private var countdownTimer: CountDownTimer? = null
    private var shouldStart = false
    private var isPause = false
    private var isWaitForNative = true
    private var splashTime = 20000L
    private var purchasePrefs: PurchasePrefs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        purchasePrefs = PurchasePrefs(requireContext())

        try {
            requireActivity().onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        Log.d("onBackPressed", "handleOnBackPressed: ")
                    }
                })
            binding.startButton.setOnClickListener {
                val fromSplashBundle = Bundle()
                fromSplashBundle.putString("from", "SplashFragment")
                findNavController().navigate(
                    R.id.action_splashFragment_to_languageFragment,
                    fromSplashBundle
                )
            }


            if (AppUtils.isNetworkAvailable(requireContext())) {
                splashTime = 20000L
                AdmobConsentForm.getInstance(requireActivity()).build()
                    .loadAndShowAdmobConsentForm {
                        showAndMove(splashTime)
                    }
            } else {
                splashTime = 4000L
                showAndMove(splashTime)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return binding.root
    }

    private fun showAndMove(timeIn: Long) {
        if (!shouldStart && AppUtils.isNetworkAvailable(requireContext())) {
            if (native_splash_bottom) binding.nativeSplash.visibility = View.GONE
            else binding.nativeSplashBottom.visibility = View.GONE
            val bindAdSplash = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(requireActivity().application, "Splash").setAdCallerName("Splash")
                .loadNativeAd(
                    if (native_splash_bottom) getString(R.string.native_id) else getString(R.string.native_id),
                    if (native_splash_bottom) native_splash_bottom else native_splash_top,
                    if (native_splash_bottom) binding.nativeSplashBottom else binding.nativeSplash,
                    bindAdSplash.root,
                    bindAdSplash.adAppIcon,
                    bindAdSplash.adHeadline,
                    bindAdSplash.adBody,
                    bindAdSplash.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        isWaitForNative = false
                        binding.nativeSplashBottom.visibility = View.INVISIBLE
                        binding.nativeSplash.visibility = View.INVISIBLE
                    },
                    adValidate = {
                        isWaitForNative = false
                        binding.nativeSplashBottom.visibility = View.INVISIBLE
                        binding.nativeSplash.visibility = View.INVISIBLE

                    },
                    adClicked = {
                        isWaitForNative = false

                    },
                    adImpression = {
                        isWaitForNative = false

                    }
                )
            InterstitialAdUtils(requireActivity(), "splash").loadInterstitialAd(
                getString(R.string.admob_splash_fullscreen),
                fullscreen_splash,
                adAlreadyLoaded = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }


                },
                adLoaded = {
                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }

                },
                adFailed = {


                },
                adValidate = {


                },
            )
        } else {
            isWaitForNative = false
        }
        shouldStart = true

        countdownTimer = object : CountDownTimer(timeIn, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                splashTime = millisUntilFinished
            }

            override fun onFinish() {
                if (!isPause) {

                    CoroutineScope(Dispatchers.Main).launch {
                        if (isWaitForNative) {
                            delay(8000)
                            if (isWaitForNative) {
                                delay(5000)
                            }
                        }
                        moveNext()
                    }
                }

            }

        }
        countdownTimer?.start()

    }

    private fun moveNext() {
        val fromSplashBundle = Bundle()
        fromSplashBundle.putString("from", "SplashFragment")
        if (purchasePrefs?.getBoolean("isFirstTime") == true) {
            findNavController().navigate(
                R.id.action_splashFragment_to_languageFragment,
                fromSplashBundle
            )
        } else {
            findNavController().navigate(
                R.id.action_splashFragment_to_homeFragment
            )
        }
        InterstitialAdUtils(
            requireActivity(),
            "splash"
        ).showInterstitialAd(
            getString(R.string.admob_splash_fullscreen),
            fullscreen_splash,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }

    override fun onPause() {
        Log.d("TAGSplash", "cancel.")
        firebaseAnalytics(
            "splashOnPause",
            "splashOnPause-->In splash"
        )
        isPause = true
        countdownTimer?.cancel()
        super.onPause()
    }

    override fun onResume() {
        isPause = false
        if (shouldStart) {
            showAndMove(splashTime)
        }
        super.onResume()
    }

    override fun onDestroy() {
        countdownTimer?.cancel()
        isPause = true
        Log.d("TAGSplash", "onDestroy.")
        firebaseAnalytics(
            "splashOnDestroy",
            "splashOnDestroy-->In splash"
        )
        super.onDestroy()
    }

}