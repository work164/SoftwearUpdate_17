package com.app.update.softwareupdatekkappsstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.update.softwareupdatekkappsstudio.practice.WiFiLiveData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class WifiDetailsActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private WiFiLiveData wifiLiveData; // Ensure this is compatible with Activities or adjust accordingly
    private boolean isUserInitiatedChange = true;

    private TextView textview_public_ip, textview_gateway_ip;
    private ConnectivityManager connectivityManager;
    private NetworkInfo WiFiCheck;
    private boolean isHandlerRunning;
    private String publicIPFetched;
    private boolean siteReachable;
    private FloatingActionButton fab_update;
    private BroadcastReceiver WiFiConnectivityReceiver;
    private TextView textview_mac, textview_dns1, textview_dvs2;
    private PublicIPRunnable runnableIP;

    private String info_mac_addr;
    private String info_gateway;
    private String info_dns1;
    private String info_dns2;
    private BottomNavigationView bottomNavView;

    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_wifi_details); // Consider renaming this XML to activity_wifi_details

//        bottomNavView = findViewById(R.id.bottomNavView);

        if (getIntent().getExtras() != null) {
            info_gateway = getIntent().getStringExtra("info_gateway");
            info_mac_addr = getIntent().getStringExtra("info_mac_addr");
            info_dns1 = getIntent().getStringExtra("info_dns1");
            info_dns2 = getIntent().getStringExtra("info_dns2");
        }

        findViewById(R.id.imageView2).setOnClickListener(view -> {
//            if (bottomNavView != null) {
//                bottomNavView.setVisibility(View.VISIBLE);
//            }
            finish();
        });

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        textview_public_ip = findViewById(R.id.textview_public_ip);
        textview_mac = findViewById(R.id.textview_mac);
        textview_dns1 = findViewById(R.id.textview_dns1);
        textview_dvs2 = findViewById(R.id.textview_dvs2);

        textview_mac.setText(info_mac_addr);
        textview_dns1.setText(info_dns1);
        textview_dvs2.setText(info_dns2);

        textview_gateway_ip = findViewById(R.id.textview_gateway_ip_value);
        textview_gateway_ip.setText(info_gateway);
        fab_update = findViewById(R.id.fab_update_ip);

        initConnectivityCheck();
        initOnClickListeners();

        wifiLiveData = new WiFiLiveData(this); // Check if this is compatible with Activity

        runnableIP = new PublicIPRunnable();
        backgroundExecutor.execute(runnableIP);
    }

    private void checkWiFiConnectivity(Boolean shouldStartHandlerThread) {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        WiFiConnectivityReceiver = new WiFiConnectivityReceiver();
        registerReceiver(WiFiConnectivityReceiver, filter);
    }

    public class WiFiConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkWiFiConnectivity(true);
        }
    }

    private void initConnectivityCheck() {
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
    }

    private void initOnClickListeners() {
        fab_update.setOnClickListener(v -> {
            fab_update.setEnabled(false);
            PublicIPRunnable runnableIP = new PublicIPRunnable();
            backgroundExecutor.execute(runnableIP);
        });
    }

    class PublicIPRunnable implements Runnable {
        @Override
        public void run() {
            String url = "https://api.ipify.org";
            siteReachable = isReachable(url);

            if (siteReachable) {
                publicIPFetched = getPublicIPAddress();
            } else {
                publicIPFetched = getString(R.string.na);
            }

            mainThreadHandler.post(() -> {
                textview_public_ip.setText(String.format(getString(R.string.your_ip), publicIPFetched));
                fab_update.setEnabled(true);
            });
        }
    }

    private String getPublicIPAddress() {
        String publicIP = "";
        try {
            InputStream apiInputStream = new URL("https://api.ipify.org").openStream();
            Scanner scanner = new Scanner(apiInputStream, "UTF-8").useDelimiter("\\A");
            publicIP = scanner.next();
            apiInputStream.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return publicIP;
    }

    private boolean isReachable(String url) {
        boolean reachable;
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();
            reachable = connection.getResponseCode() == 200;
            connection.disconnect();
        } catch (Exception e) {
            reachable = false;
        }
        return reachable;
    }

    private void copyToClipboard(String label, String text) {
        ClipboardManager cbm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        cbm.setPrimaryClip(clip);
        Toast.makeText(this, getString(R.string.copied_to_clipboard) + ": " + text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        if (bottomNavView != null) {
            bottomNavView.setVisibility(View.VISIBLE);
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(WiFiConnectivityReceiver);
    }
}
