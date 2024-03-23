package com.app.update.softwareupdatekkappsstudio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.app.update.softwareupdatekkappsstudio.adapter.OnboardingPagerAdapter
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.loadNativeLow
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.loadNativeLowWithOutMedia
import com.app.update.softwareupdatekkappsstudio.model.OnboardingItem
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class IntroActivity : AppCompatActivity() {

    private var viewPager: ViewPager? = null
    private var dotsLayout: SpringDotsIndicator? = null
    private var onboardingPagerAdapter: OnboardingPagerAdapter? = null
    private var onboardingItems: ArrayList<OnboardingItem> = arrayListOf()
    private var btnStart: AppCompatButton? = null
    private var btnNext: AppCompatButton? = null
    private var btnSkip: TextView? = null
    private var progressbarlottie: LottieAnimationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)


        try {
            onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                        Log.d("onBackPressed", "Intro: ")

                    }

                })

            viewPager = findViewById(R.id.viewPager)
            btnSkip = findViewById(R.id.btnSkip)
            dotsLayout = findViewById(R.id.indicatorLayout)
            btnNext = findViewById(R.id.btnNext)


            showOnboarding()
            btnNext?.setOnClickListener {

                if (viewPager?.currentItem!! < onboardingItems.size - 1) {
                    viewPager?.currentItem = viewPager?.currentItem!! + 1
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({ showStartButton() }, 50)
                }
            }
            btnSkip?.setOnClickListener {
                Handler(Looper.getMainLooper()).postDelayed({ showStartButton() }, 50)
            }

            val nativeContainer = findViewById<FrameLayout>(R.id.flNativeContainer)

            loadNativeLowWithOutMedia(nativeContainer, getString(R.string.native_id), true)
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }


    private fun showOnboarding() {


        try {
            btnSkip?.visibility = View.VISIBLE
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

            Log.d("woringPager", "${onboardingItems.size}")
            // Set up ViewPager and Dots
            onboardingPagerAdapter = OnboardingPagerAdapter(supportFragmentManager, onboardingItems)
            viewPager?.adapter = onboardingPagerAdapter
            viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {

                }

                override fun onPageScrollStateChanged(state: Int) {}
            })
            dotsLayout?.attachTo(viewPager ?: return)

            setOnboardingComplete()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun setOnboardingComplete() {

        val preferences = getSharedPreferences(StartActivity.PREFERENCES_FILE, MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(StartActivity.ONBOARDING_COMPLETE, true)
        editor.apply()


    }

    private fun showStartButton() {
        try {
            setOnboardingComplete()
            btnStart?.visibility = View.VISIBLE
            progressbarlottie?.visibility = View.VISIBLE
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}