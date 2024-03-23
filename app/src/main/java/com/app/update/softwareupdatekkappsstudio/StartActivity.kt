package com.app.update.softwareupdatekkappsstudio

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.util.StatsLog.logEvent
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.loadBannerLow
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.loadFullScreenLow
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.showFullScreenLow
import com.app.update.softwareupdatekkappsstudio.ads.consent_message.GoogleMobileAdsConsentManager
import com.app.update.softwareupdatekkappsstudio.fragments.PrivacyFragment
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.intertesialAds.AdMobFullScreenListener
import com.sample.adsdk.intertesialAds.FullScreenAdListener
import com.sample.adsdk.types.AdTypes

class StartActivity : AppCompatActivity() {

    var btnStart: AppCompatButton? = null
    private var termsTextView: TextView? = null
    private var animation_view: ImageView? = null
    private var progressbarlottie: LottieAnimationView? = null
    private var intentGo: Intent? = null

    private lateinit var googleMobileAdsConsentManager: GoogleMobileAdsConsentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)


        try {
            onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        Log.d("onBackPressed", "handleOnBackPressed: ")
                    }

                })

            googleMobileAdsConsentManager =
                GoogleMobileAdsConsentManager.getInstance(applicationContext)

            googleMobileAdsConsentManager.gatherConsent(this) { error ->
                Log.d("messageError", "onCreate: ${error?.message}")


                if (googleMobileAdsConsentManager.canRequestAds) {
                    loadAds()
                } else {
                    loadAds()
                    //  btnStart?.visibility = View.VISIBLE
                }


            }

            if (googleMobileAdsConsentManager.canRequestAds) {
                loadAds()
            } else {
                loadAds()

            }


            termsTextView =
                findViewById(R.id.tvterms) // Make sure you give your TextView an ID in the XML
            termsTextView?.setOnClickListener {

                // Handle Privacy Policy click
                try {
                    startActivity(
                        Intent(
                            "android.intent.action.VIEW",
                            Uri.parse("https://unseenmessage.blogspot.com/2023/09/app-updater-privacy-policy.html")
                        )
                    )
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            progressbarlottie = findViewById(R.id.progressbarlottie)
            animation_view = findViewById(R.id.animation_view)
            btnStart = findViewById(R.id.btnStart)

            intentGo = if (isFirstTime) {
                Intent(this@StartActivity, PrivacyFragment::class.java)

            } else {
                Intent(this@StartActivity, HomeActivity::class.java)

            }
            val fullText = "By Continuing, You agree to terms of service and\nPrivacy Policy"
            val spannable = SpannableString(fullText)
            val startTerm = fullText.indexOf("terms of service")
            val endTerm = startTerm + "terms of service".length
            val startPrivacy = fullText.indexOf("Privacy Policy")
            val endPrivacy = startPrivacy + "Privacy Policy".length
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.link_color)),
                startTerm,
                endTerm,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(this, R.color.link_color)),
                startPrivacy,
                endPrivacy,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            termsTextView?.text = spannable
            findViewById<View>(R.id.btnStart).setOnClickListener {
                startActivity(intentGo)
                finish()
                showAds()

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun loadAds() {
        if (Constant.isNetworkAvailable(this)) {
            loadFullScreenLow(
                getString(R.string.admob_splash_fullscreen),
                true,
                object : FullScreenAdListener {
                    override fun adAlreadyLoaded() {
                        super.adAlreadyLoaded()
                        btnStart?.visibility = View.VISIBLE
                    }

                    override fun adFailed() {
                        super.adFailed()
                        btnStart?.visibility = View.VISIBLE
                    }

                    override fun adLoaded() {
                        super.adLoaded()
                        btnStart?.visibility = View.VISIBLE
                    }

                    override fun adValidate() {
                        btnStart?.visibility = View.VISIBLE
                        super.adValidate()
                    }
                })
        } else {
            btnStart?.visibility = View.VISIBLE
        }


        val admobbannercontainer = findViewById<LinearLayout>(R.id.admobbannercontainer)
        loadBannerLow(
            admobbannercontainer,
            AdTypes.Adaptive.toString(),
            getString(R.string.admob_banner_id),
            true
        )
    }


    private fun showAds() {
        showFullScreenLow(getString(R.string.admob_splash_fullscreen), true,
            object : AdMobFullScreenListener {
                override fun fullScreenAdNotAvailable() {


                }

                override fun fullScreenAdFailedToShow() {


                }

                override fun fullScreenAdDismissed() {


                }

                override fun fullScreenAdShow() {}
            })
    }

    private val isFirstTime: Boolean
        private get() {
            val preferences = getSharedPreferences(PREFERENCES_FILE, MODE_PRIVATE)
            return !preferences.getBoolean(ONBOARDING_COMPLETE, false)
        }

    companion object {
        const val PREFERENCES_FILE = "onboarding_preferences"
        const val ONBOARDING_COMPLETE = "onboarding_complete"
    }


}