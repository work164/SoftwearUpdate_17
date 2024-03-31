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
    private lateinit var binding:FragmentHomeBinding
    private var mList = arrayListOf(
        HomeViewModel(R.drawable.ic_home_scan,"Scan Apps","4/30"),
        HomeViewModel(R.drawable.ic_home_install,"Installed Apps","Total 70"),
        HomeViewModel(R.drawable.ic_home_uninstall,"Uninstall Apps","Total 70"),
        HomeViewModel(R.drawable.ic_home_system,"System Apps","70"),
        HomeViewModel(R.drawable.ic_home_app_usage,"App Usage","365day, 24hur,32mint"),
        HomeViewModel(R.drawable.ic_device_info,"Device Info","Android 13"),
        HomeViewModel(R.drawable.ic_home_system_update,"System Update","4/30"),
        HomeViewModel(R.drawable.ic_home_wifi,"Wifi Status","on/off"),
        HomeViewModel(R.drawable.ic_home_mobile_sensor,"Mobile Sensor","Info"),
        HomeViewModel(R.drawable.ic_home_app_restore,"App Restore","30"),
        HomeViewModel(R.drawable.ic_home_battery,"Battery Info","50%"),
        HomeViewModel(R.drawable.ic_home_android_version,"Android Version","Details")
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.layoutManager = GridLayoutManager(requireContext(),2)
        binding.recyclerView.adapter = HomeAdapter(mList)
    }

}