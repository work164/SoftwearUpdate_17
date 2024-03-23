package com.app.update.softwareupdatekkappsstudio.info;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;

import java.util.UUID;

public class PhoneDetail {

    private Context context;
    private TelephonyManager telephonyManager;

    public PhoneDetail(Context context) {
        this.context = context;
        telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    public String getDeviceModel() {
        return Build.MODEL;
    }

    public String getDeviceIMEI() {
        if (telephonyManager != null) {
            return telephonyManager.getDeviceId();
        }
        return null;
    }

    public String getDeviceSoftwareVersion() {
        if (telephonyManager != null) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                return telephonyManager.getDeviceSoftwareVersion();
            }

        }
        return null;
    }
    public String getAndroidVersionName() {
        String versionName = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            versionName = Build.VERSION.RELEASE;
        }
        return versionName;
    }
    public String getNetworkOperatorName() {
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }
    public String getBoardName() {
        return Build.BOARD;
    }

    public String getHardwareName() {
        return Build.HARDWARE;
    }
    public String getGSFrameworkId() {
        String gsfId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (TextUtils.isEmpty(gsfId) || gsfId.equals("9774d56d682e549c")) {
            return null;
        } else {
            return gsfId;
        }
    }
    public String getAdvertisingId() {
        try {
            AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
            return adInfo.getId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getDeviceId() {
        String uniqueId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (uniqueId == null) {
            uniqueId = generateUUID();
        }
        return uniqueId;
    }

    private String generateUUID() {
        return UUID.randomUUID().toString();
    }
    public String getHardwareSerial() {
        return Build.SERIAL;
    }
    public String getBuildFingerprint() {
        return Build.FINGERPRINT;
    }
    public String getDeviceType() {
        int screenSize = context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            return "Tablet";
        } else if (screenSize == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            return "Phone";
        } else {
            return "Unknown";
        }
    }

    public String getWifiMacAddress() {
        if (context.checkCallingOrSelfPermission(android.Manifest.permission.ACCESS_WIFI_STATE) == PackageManager.PERMISSION_GRANTED) {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                if (wifiInfo != null) {
                    return wifiInfo.getMacAddress();
                }
            }
        }
        return null;
    }
    public String getBluetoothMacAddress() {
//        if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED) {
//            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
//            if (bluetoothAdapter != null) {
//                return bluetoothAdapter.getAddress();
//            }
//        }
        return null;
    }

    public boolean isUsbDebuggingEnabled() {
        int adbEnabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ADB_ENABLED, 0);
        return adbEnabled == 1;
    }
    public String getNetworkType() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                int type = networkInfo.getType();
                if (type == ConnectivityManager.TYPE_WIFI) {
                    return "Wi-Fi";
                } else if (type == ConnectivityManager.TYPE_MOBILE) {
                    return "Mobile Data";
                }
            }
        }
        return "No Network";
    }
}