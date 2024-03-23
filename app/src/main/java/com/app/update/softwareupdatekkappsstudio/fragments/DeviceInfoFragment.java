package com.app.update.softwareupdatekkappsstudio.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.update.softwareupdatekkappsstudio.R;
import com.app.update.softwareupdatekkappsstudio.info.PhoneDetail;


public class DeviceInfoFragment extends Fragment {
    TextView tvCompanyName,tvDeviceName,tvDeviceName1,tvDeviceModel,tvDeviceManufacturer,tvDevice,tvBoard,tvHardware,tvBrand;
    TextView tvGSFId,tvGAdvertisingId,tvDeviceId,tvHardwareSerial,tvBuildFingerPrint,tvDeviceType,tvNetworkOperator,tvNetworkType,tvWifiMacAddress,tvUsbDebugging;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_device_info, container, false);
        PhoneDetail phoneDetail=new PhoneDetail(requireContext());
        tvCompanyName= view.findViewById(R.id.tvCompanyName);
        tvDeviceName= view.findViewById(R.id.tvDeviceName);
        tvDeviceName1= view.findViewById(R.id.tvDeviceName1);
        tvDeviceModel= view.findViewById(R.id.tvDeviceModel);
        tvDeviceManufacturer= view.findViewById(R.id.tvDeviceManufacturer);
        tvDevice= view.findViewById(R.id.tvDevice);
        tvBoard= view.findViewById(R.id.tvBoard);
        tvHardware= view.findViewById(R.id.tvHardware);
        tvBrand= view.findViewById(R.id.tvBrand);
        tvGSFId= view.findViewById(R.id.tvGSFId);
        tvGAdvertisingId= view.findViewById(R.id.tvGAdvertisingId);
        tvDeviceId= view.findViewById(R.id.tvDeviceId);
        tvHardwareSerial= view.findViewById(R.id.tvHardwareSerial);
        tvBuildFingerPrint= view.findViewById(R.id.tvBuildFingerPrint);
        tvDeviceType= view.findViewById(R.id.tvDeviceType);
        tvNetworkOperator= view.findViewById(R.id.tvNetworkOperator);
        tvNetworkType= view.findViewById(R.id.tvNetworkType);
        tvWifiMacAddress= view.findViewById(R.id.tvWifiMacAddress);
        tvUsbDebugging= view.findViewById(R.id.tvUsbDebugging);
        tvCompanyName.setText(phoneDetail.getDeviceManufacturer());
        tvDeviceName.setText(phoneDetail.getDeviceModel());
        tvDeviceName1.setText(phoneDetail.getDeviceModel());
        tvDeviceModel.setText(phoneDetail.getDeviceModel());
        tvDeviceManufacturer.setText(phoneDetail.getDeviceManufacturer());
        tvDevice.setText(phoneDetail.getDeviceModel());
        tvBoard.setText(phoneDetail.getBoardName());
        tvHardware.setText(phoneDetail.getHardwareName());
        tvBrand.setText(phoneDetail.getDeviceManufacturer());
        tvGSFId.setText(phoneDetail.getGSFrameworkId());
        tvGAdvertisingId.setText(phoneDetail.getAdvertisingId());
        tvDeviceId.setText(phoneDetail.getDeviceId());
        tvHardwareSerial.setText(phoneDetail.getHardwareSerial());
        tvBuildFingerPrint.setText(phoneDetail.getBuildFingerprint());
        tvDeviceType.setText(phoneDetail.getDeviceType());
        tvNetworkOperator.setText(phoneDetail.getNetworkOperatorName());
        tvNetworkType.setText(phoneDetail.getNetworkType());
        tvWifiMacAddress.setText(phoneDetail.getWifiMacAddress());
        if (phoneDetail.isUsbDebuggingEnabled())
            tvUsbDebugging.setText("on");
        else
            tvUsbDebugging.setText("off");

        return view;
    }
}