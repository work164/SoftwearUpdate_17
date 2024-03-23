package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.widget.FrameLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.app.update.softwareupdatekkappsstudio.IntroActivity
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.ads.LoadAndShowAds.loadNativeLow


class PrivacyFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_privacy)
        findViewById<AppCompatButton>(R.id.accept_btn).setOnClickListener {
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
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
        findViewById<TextView>(R.id.privacyText)?.text = spannable
        onBackPressedDispatcher.addCallback(
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("onBackPressed", "Privacy: ")
                }

            })
        loadNativeLow(
            findViewById<FrameLayout>(R.id.privacyNativeAd),
            getString(R.string.native_id),
            true
        )

    }


}