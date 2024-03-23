package com.app.update.softwareupdatekkappsstudio.fragments;

import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.WIFI_SERVICE;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.app.update.softwareupdatekkappsstudio.R;
import com.app.update.softwareupdatekkappsstudio.WifiDetailsActivity;
import com.app.update.softwareupdatekkappsstudio.practice.SharedPreferencesManager;
import com.app.update.softwareupdatekkappsstudio.practice.WiFiLiveData;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class WifiFragment extends Fragment {
    private WifiManager wifiManager;
    private WiFiLiveData wifiLiveData;
    CardView wifidetailsbtn;
    private boolean isUserInitiatedChange = true;

    private TextView textview_ssid;
    private ConnectivityManager connectivityManager;
    private NetworkInfo WiFiCheck;
    private boolean isHandlerRunning;
    String info_gateway_ip = "";
    private String publicIPFetched;
    String info_dns1 = "";
    String info_dns2 = "";
    private DhcpInfo dhcpInfo;
    private boolean siteReachable;
    private FloatingActionButton fab_update;
    private BroadcastReceiver WiFiConnectivityReceiver;
    String info_ssid = "";
    private Handler infoHandler;
    private HandlerThread infoHandlerThread;
    private WifiInfo wifiInfo;

    private int keyCardFreqFormatted = 1000;
    String info_mac_addr = "";
    BottomNavigationView bottomNavView;
    String info_network_speed = "";
    TextView textview_network_speed_value;
    ToggleButton toggleButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wifi, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(WIFI_SERVICE);
        keyCardFreqFormatted = Integer.parseInt(new SharedPreferencesManager(getActivity()).retrieveString("card_update_freq", "1000"));
        bottomNavView = getActivity().findViewById(R.id.bottomNavView);
        textview_network_speed_value = getActivity().findViewById(R.id.textview_network_speed_value);

        toggleButton = view.findViewById(R.id.toggleWifii);

        textview_ssid = view.findViewById(R.id.textview_ssid_value);
        wifidetailsbtn = view.findViewById(R.id.wifidetailsbtn);


        wifidetailsbtn.setOnClickListener(view1 -> {
            try {
                Intent intent = new Intent(getContext(), WifiDetailsActivity.class);
                intent.putExtra("info_gateway", info_gateway_ip);
                intent.putExtra("info_mac_addr", info_mac_addr);
                intent.putExtra("info_dns1", info_dns1);
                intent.putExtra("info_dns2", info_dns2);

                startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        textview_ssid.setText(info_ssid);

        initConnectivityCheck();


        wifiLiveData = new WiFiLiveData(getActivity());

        wifiLiveData.observe(getActivity(), wifiState -> {
            if (isAdded() && !isDetached()) {
                try {
                    isUserInitiatedChange = false;
                    toggleButton.setChecked(wifiState);
                } catch (java.lang.IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });


        if (wifiManager != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            toggleButton.setChecked(wifiManager.isWifiEnabled());
            if (wifiManager.isWifiEnabled()) {
                toggleButton.setBackgroundResource(R.drawable.toggle_on);
            } else {
                toggleButton.setBackgroundResource(R.drawable.toggle_off);
            }
        }

        toggleButton.setOnCheckedChangeListener((compoundButton, isChecked) -> {

            try {
                if (isChecked) {
                    toggleButton.setBackgroundResource(R.drawable.toggle_on);
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                } else {
                    toggleButton.setBackgroundResource(R.drawable.toggle_off);
                    showWifiSettingsInstructionDialog();
                }
            } catch (java.lang.IllegalStateException e) {
                e.printStackTrace();
            }
        });

//        initCopyableText();
    }


    private void showWifiSettingsInstructionDialog() {


        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Warning");
            builder.setMessage("if you turn off Wi-Fi, Your internet connectivity will be gone .");
            builder.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            builder.setNegativeButton("Cancel", null);
            builder.show();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void checkWiFiConnectivity(Boolean shouldStartHandlerThread) {


        try {
            connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


            if (WiFiCheck.isConnected()) {
                if (shouldStartHandlerThread) {
                    if (!isHandlerRunning) {
                        startInfoHandlerThread();
                        startInfoHandler();
                    }
                }
            } else {

                if (isHandlerRunning) {
                    stopInfoHandler();
                    stopInfoHandlerThread();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Rest of the code remains the same
    }

    private void stopInfoHandlerThread() {
        try {
            if (infoHandlerThread != null) {
                infoHandlerThread.quit();
                infoHandlerThread = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopInfoHandler() {
        try {
            if (infoHandler != null) {
                infoHandler.removeCallbacksAndMessages(infoRunnable);
                isHandlerRunning = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startInfoHandlerThread() {
        try {
            infoHandlerThread = new HandlerThread("BackgroundInfoHandlerThread", android.os.Process.THREAD_PRIORITY_BACKGROUND);
            infoHandlerThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void startInfoHandler() {
        try {
            infoHandler = new Handler(infoHandlerThread.getLooper());
            infoHandler.post(infoRunnable);
            isHandlerRunning = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final Runnable infoRunnable = new Runnable() {
        @Override
        public void run() {
            getAllNetworkInformation();
            if (isAdded() && getActivity() != null) { // Check if the fragment is added to an activity
                getActivity().runOnUiThread(() -> textview_ssid.setText(info_ssid));
            }
            infoHandler.postDelayed(infoRunnable, keyCardFreqFormatted);
        }
    };


    private void getAllNetworkInformation() {
        try {
            if (isAdded()) {
                wifiManager = (WifiManager) requireActivity().getApplicationContext().getSystemService(WIFI_SERVICE);
                wifiInfo = wifiManager.getConnectionInfo();
                dhcpInfo = wifiManager.getDhcpInfo();
                String dns1 = intToIp(dhcpInfo.dns1);
                String dns2 = intToIp(dhcpInfo.dns2);
                String ssid = wifiInfo.getSSID();
                int networkSpeed = 0;
                int TXLinkSpd = 0;
                int RXLinkSpd = 0;


                if (Build.VERSION.SDK_INT >= 29) {
                    TXLinkSpd = wifiInfo.getTxLinkSpeedMbps();
                    RXLinkSpd = wifiInfo.getRxLinkSpeedMbps();
                } else {
                    networkSpeed = wifiInfo.getLinkSpeed();
                }

                String gatewayIp = getGatewayIP();

                info_gateway_ip = gatewayIp;


                String macAddr;
                if (Build.VERSION.SDK_INT > 29) {
                    macAddr = getString(R.string.na);
                } else {
                    macAddr = getMACAddress();
                }
                info_mac_addr = macAddr;


                if (ssid.equals("<unknown ssid>")) {
                    info_ssid = getString(R.string.na);
                } else {
                    info_ssid = "Connected to " + ssid.replaceAll("^\"|\"$", "");
                }


                info_dns1 = dns1;
                info_dns2 = dns2;


                if (Build.VERSION.SDK_INT >= 29) {
                    final String info_network_speed = RXLinkSpd + " / " + TXLinkSpd + " Mbps";

                    // Get the main handler associated with the main Looper
                    Handler mainHandler = new Handler(Looper.getMainLooper());

                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            textview_network_speed_value.setText(info_network_speed);
                        }
                    });
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(WiFiConnectivityReceiver);
        }
    }


    private String getMACAddress() {
        try {
            List<NetworkInterface> allNetworkInterfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : allNetworkInterfaces) {
                if (!networkInterface.getName().equalsIgnoreCase("wlan0"))
                    continue;

                byte[] macBytes = networkInterface.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder macAddressStringBuilder = new StringBuilder();
                for (byte b : macBytes) {
                    macAddressStringBuilder.append(String.format("%02X:", b));
                }

                if (macAddressStringBuilder.length() > 0) {
                    macAddressStringBuilder.deleteCharAt(macAddressStringBuilder.length() - 1);
                }

                return macAddressStringBuilder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    private String getGatewayIP() {


        int gatewayIP = 0;
        try {
            if (!WiFiCheck.isConnected()) {
                return "0.0.0.0";
            }
            wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(WIFI_SERVICE);
            DhcpInfo dhcp = wifiManager.getDhcpInfo();
            gatewayIP = dhcp.gateway;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return intToIp(gatewayIP);
    }

    private String intToIp(int ipInt) {
        return ((ipInt & 0xFF) + "."
                + ((ipInt >> 8) & 0xFF) + "."
                + ((ipInt >> 16) & 0xFF) + "."
                + ((ipInt >> 24) & 0xFF));
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            if (bottomNavView != null) {
                bottomNavView.setVisibility(View.VISIBLE);
            }

            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            WiFiConnectivityReceiver = new WiFiConnectivityReceiver();
            // Registering the receiver on the Fragment's hosting Activity
            requireActivity().registerReceiver(WiFiConnectivityReceiver, filter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class WiFiConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                checkWiFiConnectivity(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void initConnectivityCheck() {
        connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

    }

    class PublicIPRunnable implements Runnable {
        @SuppressLint("StaticFieldLeak")
        @Override
        public void run() {
            new AsyncTask<String, Void, Void>() {
                @Override
                protected Void doInBackground(String[] voids) {
                    String url = "https://api.ipify.org";
                    siteReachable = isReachable(url);
                    if (siteReachable) {
                        publicIPFetched = getPublicIPAddress();
                    } else {
                        publicIPFetched = getString(R.string.na);
                    }
                    return null;
                }

                @SuppressLint("SetTextI18n")
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
//                    textview_public_ip.setText(String.format(getString(R.string.your_ip), publicIPFetched));
                }
            }.execute();

            Handler handlerEnableFAB = new Handler(Looper.getMainLooper());
            handlerEnableFAB.postDelayed(() -> fab_update.setEnabled(true), 5000);
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
        int response_code;

        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(3000);
            connection.connect();
            response_code = connection.getResponseCode();
            connection.disconnect();
            reachable = response_code == 200;
        } catch (Exception e) {
            reachable = false;
        }
        return reachable;
    }


}