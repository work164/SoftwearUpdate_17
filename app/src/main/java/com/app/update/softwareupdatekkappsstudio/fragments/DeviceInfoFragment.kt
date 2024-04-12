package com.app.update.softwareupdatekkappsstudio.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.info.PhoneDetail

class DeviceInfoFragment : Fragment() {
    var tvCompanyName: TextView? = null
    var tvDeviceName: TextView? = null
    var tvDeviceName1: TextView? = null
    var tvDeviceModel: TextView? = null
    var tvDeviceManufacturer: TextView? = null
    var tvDevice: TextView? = null
    var tvBoard: TextView? = null
    var tvHardware: TextView? = null
    var tvBrand: TextView? = null
    var tvGSFId: TextView? = null
    var tvGAdvertisingId: TextView? = null
    var tvDeviceId: TextView? = null
    var tvHardwareSerial: TextView? = null
    var tvBuildFingerPrint: TextView? = null
    var tvDeviceType: TextView? = null
    var tvNetworkOperator: TextView? = null
    var tvNetworkType: TextView? = null
    var tvWifiMacAddress: TextView? = null
    var tvUsbDebugging: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })



        val view = inflater.inflate(R.layout.fragment_device_info, container, false)
        val phoneDetail = PhoneDetail(requireContext())
        tvCompanyName = view.findViewById(R.id.tvCompanyName)
        tvDeviceName = view.findViewById(R.id.tvDeviceName)
        tvDeviceName1 = view.findViewById(R.id.tvDeviceName1)
        tvDeviceModel = view.findViewById(R.id.tvDeviceModel)
        tvDeviceManufacturer = view.findViewById(R.id.tvDeviceManufacturer)
        tvDevice = view.findViewById(R.id.tvDevice)
        tvBoard = view.findViewById(R.id.tvBoard)
        tvHardware = view.findViewById(R.id.tvHardware)
        tvBrand = view.findViewById(R.id.tvBrand)
        tvGSFId = view.findViewById(R.id.tvGSFId)
        tvGAdvertisingId = view.findViewById(R.id.tvGAdvertisingId)
        tvDeviceId = view.findViewById(R.id.tvDeviceId)
        tvHardwareSerial = view.findViewById(R.id.tvHardwareSerial)
        tvBuildFingerPrint = view.findViewById(R.id.tvBuildFingerPrint)
        tvDeviceType = view.findViewById(R.id.tvDeviceType)
        tvNetworkOperator = view.findViewById(R.id.tvNetworkOperator)
        tvNetworkType = view.findViewById(R.id.tvNetworkType)
        tvWifiMacAddress = view.findViewById(R.id.tvWifiMacAddress)
        tvUsbDebugging = view.findViewById(R.id.tvUsbDebugging)
        tvCompanyName?.text = phoneDetail.deviceManufacturer
        tvDeviceName?.text = phoneDetail.deviceModel
        tvDeviceName1?.text = phoneDetail.deviceModel
        tvDeviceModel?.text = phoneDetail.deviceModel
        tvDeviceManufacturer?.text = phoneDetail.deviceManufacturer
        tvDevice?.text = phoneDetail.deviceModel
        tvBoard?.text = phoneDetail.boardName
        tvHardware?.text = phoneDetail.hardwareName
        tvBrand?.text = phoneDetail.deviceManufacturer
        tvGSFId?.text = phoneDetail.gsFrameworkId
        tvGAdvertisingId?.text = phoneDetail.advertisingId
        tvDeviceId?.text = phoneDetail.deviceId
        tvHardwareSerial?.text = phoneDetail.hardwareSerial
        tvBuildFingerPrint?.text = phoneDetail.buildFingerprint
        tvDeviceType?.text = phoneDetail.deviceType
        tvNetworkOperator?.text = phoneDetail.networkOperatorName
        tvNetworkType?.text = phoneDetail.networkType
        tvWifiMacAddress?.text = phoneDetail.wifiMacAddress
        if (phoneDetail.isUsbDebuggingEnabled) tvUsbDebugging?.text = "on" else tvUsbDebugging?.text =
            "off"
        return view
    }
}