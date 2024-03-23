package com.app.update.softwareupdatekkappsstudio.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.app.update.softwareupdatekkappsstudio.R;

public class WifiActivity extends AppCompatActivity {
    private WifiManager wifiManager;
    private WiFiLiveData wifiLiveData;
    private boolean isUserInitiatedChange = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        ToggleButton toggleButton = findViewById(R.id.toggleWifi);

        wifiLiveData = new WiFiLiveData(this);

        wifiLiveData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean wifiState) {
                isUserInitiatedChange = false;  // Disable user check for programmatic change
                toggleButton.setChecked(wifiState);
            }
        });


        // Set initial state based on current WiFi state
        if (wifiManager != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            toggleButton.setChecked(wifiManager.isWifiEnabled());
        }

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isUserInitiatedChange) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    } else {
                        if (wifiManager != null) {
                            wifiManager.setWifiEnabled(isChecked);
//                            Toast.makeText(WifiActivity.this, isChecked ? "WiFi ON" : "WiFi OFF", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                isUserInitiatedChange = true;
            }
        });
    }
}