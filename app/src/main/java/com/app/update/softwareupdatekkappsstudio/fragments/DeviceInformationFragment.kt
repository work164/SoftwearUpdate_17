package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentDeviceInformationBinding

class DeviceInformationFragment : Fragment() {
    private lateinit var binding: FragmentDeviceInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeviceInformationBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.deviceName.text = Build.DEVICE
        binding.deviceModel.text = Build.MODEL
        binding.deviceManufacture.text = Build.MANUFACTURER
        binding.deviceHardware.text = Build.HARDWARE
        binding.deviceBoard.text=Build.BOARD
        binding.deviceId.text = Build.ID
        binding.device.text = Build.BRAND
    }


}