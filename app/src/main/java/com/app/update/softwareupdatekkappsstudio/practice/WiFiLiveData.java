package com.app.update.softwareupdatekkappsstudio.practice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;

import androidx.lifecycle.LiveData;

public class WiFiLiveData extends LiveData<Boolean> {

    private  Context context   = null;
    private  WifiManager wifiManager = null;

    private final BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


            try {
                if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                    int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
                    setValue(wifiState == WifiManager.WIFI_STATE_ENABLED);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    };

    public WiFiLiveData(Context context) {
        try {
            this.context = context.getApplicationContext();
            this.wifiManager = (WifiManager) this.context.getSystemService(Context.WIFI_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActive() {
        super.onActive();
        try {
            IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
            context.registerReceiver(wifiStateReceiver, intentFilter);

            // Initial State
            if (wifiManager != null) {
                setValue(wifiManager.isWifiEnabled());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (context != null && wifiManager != null) {
            context.unregisterReceiver(wifiStateReceiver);
        }
    }
}
