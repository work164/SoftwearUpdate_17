package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.HomeAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentHomeBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel

class HomeFragment : Fragment(), HomeClick {
    private lateinit var binding: FragmentHomeBinding
    private var mList = arrayListOf(
        HomeViewModel(R.drawable.ic_home_scan, "Scan Apps", "", 0),
        HomeViewModel(R.drawable.ic_home_install, "Installed Apps", "", 1),
        HomeViewModel(R.drawable.ic_home_uninstall, "Uninstall Apps", "", 2),
        HomeViewModel(R.drawable.ic_home_system, "System Apps", "", 3),
        HomeViewModel(R.drawable.ic_home_app_usage, "App Usage", "", 4),
        HomeViewModel(R.drawable.ic_device_info, "Device Info", "", 5),
        HomeViewModel(R.drawable.ic_home_system_update, "System Update", "", 6),
        HomeViewModel(R.drawable.ic_home_wifi, "Wifi Status", "", 7),
        HomeViewModel(R.drawable.ic_home_mobile_sensor, "Mobile Sensor", "", 8),
        HomeViewModel(R.drawable.ic_home_app_restore, "App Restore", "", 9),
        HomeViewModel(R.drawable.ic_home_battery, "Battery Info", "", 10),
        HomeViewModel(R.drawable.ic_home_android_version, "Android Version", "", 11)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        with(binding) {
            setting.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
            }
            giftHome.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_appProFragment)

            }
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = HomeAdapter(requireContext(), mList, this)
    }

    override fun onItemClick(name: String, position: Int) {
        Log.i("TAGHome", "onItemClick: $name position $position")
        when (position) {
            0 -> {
                findNavController().navigate(R.id.action_homeFragment_to_scanFragment)

            }

            1 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appCounterFragment)

            }

            2 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appUninstallFragment)

            }

            3 -> {
                findNavController().navigate(R.id.action_homeFragment_to_systemAppsFragment)

            }

            4 -> {
                findNavController().navigate(R.id.action_homeFragment_to_appUsageFragment)

            }

            5 -> {
                findNavController().navigate(R.id.action_homeFragment_to_deviceInfoFragment)
//                findNavController().navigate(R.id.action_homeFragment_to_deviceInformationFragment)

            }

            6 -> {
                findNavController().navigate(R.id.action_homeFragment_to_androidUpdateFragment)

            }

            7 -> {
                findNavController().navigate(R.id.action_homeFragment_to_wifiFragment)

            }

            8 -> {
                findNavController().navigate(R.id.action_homeFragment_to_sensorFragment)

            }

            9 -> {
                findNavController().navigate(R.id.action_homeFragment_to_restoreAppsFragment)

            }

            10 -> {
                findNavController().navigate(R.id.action_homeFragment_to_batteryInfoFragment)

            }

            11 -> {
                findNavController().navigate(R.id.action_homeFragment_to_androidVersionFragment)

            }

            else -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingFragment)

            }


        }



        super.onItemClick(name, position)
    }

}