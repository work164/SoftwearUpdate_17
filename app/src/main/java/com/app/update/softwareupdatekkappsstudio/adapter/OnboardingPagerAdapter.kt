package com.app.update.softwareupdatekkappsstudio.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.app.update.softwareupdatekkappsstudio.fragments.OnboardingFragment
import com.app.update.softwareupdatekkappsstudio.model.OnboardingItem

class OnboardingPagerAdapter(fm: FragmentManager, private val onboardingItems: ArrayList<OnboardingItem>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        Log.d("woringPager", "}" + onboardingItems[position])
        return OnboardingFragment(onboardingItems[position])
    }

    override fun getCount(): Int {
        return 4
    }
}