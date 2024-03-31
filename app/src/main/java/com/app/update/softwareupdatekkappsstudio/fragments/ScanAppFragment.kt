package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Context.CONNECTIVITY_SERVICE
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.getSystemService
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentScanAppBinding
import com.google.android.material.snackbar.Snackbar

class ScanAppFragment : Fragment() {
    private lateinit var binding:FragmentScanAppBinding
    private lateinit var wholeAppList: ArrayList<ApplicationInfo>
    val forLaunchInstalledApps: MutableList<ApplicationInfo> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScanAppBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun scanAllApplications(){
        wholeAppList = ArrayList()
        val flag1 = PackageManager.GET_META_DATA or
                PackageManager.GET_SHARED_LIBRARY_FILES or
                PackageManager.GET_UNINSTALLED_PACKAGES
        val listOfWholeApps = requireContext().packageManager.getInstalledApplications(flag1)
        for (list in listOfWholeApps) {
            if (requireContext().packageManager.
                getLaunchIntentForPackage(list.packageName) != null
                ) {
                forLaunchInstalledApps.add(list)
            }
        }
        for (apInfo in forLaunchInstalledApps){
            if (apInfo.flags and ApplicationInfo.FLAG_SYSTEM > 0) {
                wholeAppList.add(apInfo)
            }
        }
    }
    private fun forCheckInternet(): Boolean {
        try {
            val cm1 = requireContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val onNetwork = cm1.activeNetworkInfo
            if (onNetwork != null){
                if (onNetwork.type==ConnectivityManager.TYPE_WIFI){
                    if (onNetwork.state == NetworkInfo.State.CONNECTED){
                        return true
                    }
                } else if (onNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    if (onNetwork.state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
        } catch (e:Exception) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                e.toString(),
                Snackbar.LENGTH_LONG
            ).setActionTextColor(
                Color.RED
            ).show()
        }
        return false
    }


}