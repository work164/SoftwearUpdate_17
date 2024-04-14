package com.app.update.softwareupdatekkappsstudio.fragments

import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import bot.box.appusage.contract.UsageContracts
import bot.box.appusage.handler.Monitor
import bot.box.appusage.model.AppData
import bot.box.appusage.utils.Duration
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppUsageAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentAppUsageBinding
import com.app.update.softwareupdatekkappsstudio.utils.navigateFragment


class AppUsageFragment : Fragment(), UsageContracts.View {
    private var binding: FragmentAppUsageBinding? = null
    private var onResumeCount = 0
    private val appUsageAdapter by lazy {
        AppUsageAdapter(requireContext(), onItemClick = {
            navigateFragment(
                R.id.appUsageFragment,
                R.id.action_appUsageFragment_to_appDetailFragment,
                Bundle().apply {
                    putString("appPackageName", it)
                }
            )
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackWithAd()
                }
            })
        // Inflate the layout for this fragment
        binding = FragmentAppUsageBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isAccessGranted()) {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            startActivity(intent)
        }
        setUpRecyclerView()
        setUpClickListeners()
    }

    private fun setUpClickListeners() {
        binding?.run {
            tvToday.setOnClickListener {
                setDuration(it)
            }
            tvYesterday.setOnClickListener {
                setDuration(it)
            }
            tvThisWeek.setOnClickListener {
                setDuration(it)
            }
            tvThisMonth.setOnClickListener {
                setDuration(it)
            }
            backDevice.setOnClickListener {
                onBackWithAd()
            }
        }
    }

    private fun setUpRecyclerView() {

        binding?.run {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = appUsageAdapter
        }
    }

    private fun isAccessGranted(): Boolean {
        return try {
            val packageManager: PackageManager = requireContext().packageManager
            val applicationInfo = packageManager.getApplicationInfo(requireContext().packageName, 0)
            val appOpsManager =
                requireContext().getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
            var mode = 0
            mode = appOpsManager.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                applicationInfo.uid, applicationInfo.packageName
            )
            mode == AppOpsManager.MODE_ALLOWED
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    override fun onResume() {
        super.onResume()


        if (Monitor.hasUsagePermission()) {
            Monitor.scan().getAppLists(this).fetchFor(Duration.TODAY)
        } else {
            if (onResumeCount >= 1) {
                findNavController().popBackStack()
                return
            }
            onResumeCount++
            Monitor.requestUsagePermission()
        }
    }

    override fun showProgress() {
        binding?.progressBar?.visibility = View.VISIBLE
        binding?.recyclerView?.visibility = View.GONE

    }

    override fun hideProgress() {
        binding?.progressBar?.visibility = View.GONE
        binding?.recyclerView?.visibility = View.VISIBLE


    }

    override fun getUsageData(usageData: List<AppData>, mTotalUsage: Long, duration: Int) {
        if (usageData.isNotEmpty()) {
            //call adapter for populate list
            appUsageAdapter.setUpArrayList(usageData)
        }
    }

    private fun setDuration(view: View) {
        when (view.id) {
            R.id.tvToday -> {
                Monitor.scan().getAppLists(this).fetchFor(Duration.TODAY)

                binding?.run {
                    tvToday.setBackgroundResource(R.drawable.bg_app_usage_selected)
                    tvYesterday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisWeek.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisMonth.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                }
            }

            R.id.tvYesterday -> {
                Monitor.scan().getAppLists(this).fetchFor(Duration.YESTERDAY)

                binding?.run {
                    tvToday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvYesterday.setBackgroundResource(R.drawable.bg_app_usage_selected)
                    tvThisWeek.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisMonth.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                }
            }

            R.id.tvThisWeek -> {
                Monitor.scan().getAppLists(this).fetchFor(Duration.WEEK)

                binding?.run {
                    tvToday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvYesterday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisWeek.setBackgroundResource(R.drawable.bg_app_usage_selected)
                    tvThisMonth.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                }
            }

            R.id.tvThisMonth -> {
                Monitor.scan().getAppLists(this).fetchFor(Duration.MONTH)

                binding?.run {
                    tvToday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvYesterday.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisWeek.setBackgroundResource(R.drawable.bg_app_usage_un_selected)
                    tvThisMonth.setBackgroundResource(R.drawable.bg_app_usage_selected)
                }
            }
        }
    }

    fun onBackWithAd() {
        findNavController().popBackStack()

    }


}