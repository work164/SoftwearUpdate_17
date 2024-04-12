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


class PrivacyFragment : Fragment() {







    private lateinit var binding: FragmentPrivacyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    Log.d("onBackPressed", "Privacy: ")
                }

            })


        return binding.root
    }


}