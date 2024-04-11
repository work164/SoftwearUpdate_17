package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.airbnb.lottie.LottieAnimationView
import com.app.update.softwareupdatekkappsstudio.HomeActivity
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)


        try {
            requireActivity().onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        Log.d("onBackPressed", "handleOnBackPressed: ")
                    }

                })


            binding.startButton.setOnClickListener {
                val fromSplashBundle = Bundle()
                fromSplashBundle.putString("from","SplashFragment")
               findNavController().navigate(R.id.action_splashFragment_to_languageFragment,fromSplashBundle)


            }
        } catch (e: Exception) {
            e.printStackTrace()
        }



        return binding.root
    }

}