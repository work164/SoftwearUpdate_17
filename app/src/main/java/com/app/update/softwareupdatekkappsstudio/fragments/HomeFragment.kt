package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.HomeAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentHomeBinding
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var mList = arrayListOf(
        HomeViewModel(R.drawable.ic_home_scan, "Scan Apps", ""),
        HomeViewModel(R.drawable.ic_home_install, "Installed Apps", ""),
        HomeViewModel(R.drawable.ic_home_uninstall, "Uninstall Apps", ""),
        HomeViewModel(R.drawable.ic_home_system, "System Apps", ""),
        HomeViewModel(R.drawable.ic_home_app_usage, "App Usage", ""),
        HomeViewModel(R.drawable.ic_device_info, "Device Info", ""),
        HomeViewModel(R.drawable.ic_home_system_update, "System Update", ""),
        HomeViewModel(R.drawable.ic_home_wifi, "Wifi Status", ""),
        HomeViewModel(R.drawable.ic_home_mobile_sensor, "Mobile Sensor", ""),
        HomeViewModel(R.drawable.ic_home_app_restore, "App Restore", ""),
        HomeViewModel(R.drawable.ic_home_battery, "Battery Info", ""),
        HomeViewModel(R.drawable.ic_home_android_version, "Android Version", "")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerView.adapter = HomeAdapter(requireContext(), mList)
    }

}