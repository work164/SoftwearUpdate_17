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
import com.app.update.softwareupdatekkappsstudio.model.OnboardingItem

class IntroFragment : Fragment() {

    private var onboardingPagerAdapter: OnboardingPagerAdapter? = null
    private var onboardingItems: ArrayList<OnboardingItem> = arrayListOf()

    private lateinit var binding: FragmentIntroBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroBinding.inflate(inflater, container, false)


        try {
            requireActivity().onBackPressedDispatcher.addCallback(
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                        Log.d("onBackPressed", "Intro: ")

                    }

                })



            showOnboarding()
            binding.btnNext.setOnClickListener {

                if (binding.viewPager.currentItem < onboardingItems.size - 1) {
                    binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({ showStartButton() }, 50)
                }
            }
            binding.btnSkip.setOnClickListener {
                Handler(Looper.getMainLooper()).postDelayed({ showStartButton() }, 50)
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
                    if (position < 2) {
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
        try {
            findNavController().navigate(R.id.action_introFragment_to_homeFragment)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}