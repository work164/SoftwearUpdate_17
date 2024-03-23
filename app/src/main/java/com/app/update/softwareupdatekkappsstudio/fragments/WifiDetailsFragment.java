package com.app.update.softwareupdatekkappsstudio.fragments;

import static android.content.Context.CLIPBOARD_SERVICE;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.update.softwareupdatekkappsstudio.R;
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


public class WifiDetailsFragment extends Fragment {
    private WifiManager wifiManager;
    private WiFiLiveData wifiLiveData;
    private boolean isUserInitiatedChange = true;

    private TextView textview_public_ip,textview_gateway_ip;
    private ConnectivityManager connectivityManager;
    private NetworkInfo WiFiCheck;
    private boolean isHandlerRunning;
    private String publicIPFetched;
    private boolean siteReachable;
    private FloatingActionButton fab_update;
    private BroadcastReceiver WiFiConnectivityReceiver;
    private TextView textview_mac,textview_dns1,textview_dvs2;
    PublicIPRunnable runnableIP;

    Thread currentThread;

    String info_mac_addr;
    String info_gateway;
    String info_dns1;
    String info_dns2;
    BottomNavigationView bottomNavView;

    private final Executor backgroundExecutor = Executors.newSingleThreadExecutor();
    private final Handler mainThreadHandler = new Handler(Looper.getMainLooper());


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wifi_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavView = getActivity().findViewById(R.id.bottomNavView);


        Bundle bundle = getArguments();
        if (bundle != null) {
            info_gateway = bundle.getString("info_gateway");
             info_mac_addr = bundle.getString("info_mac_addr");
             info_dns1 = bundle.getString("info_dns1");
             info_dns2 = bundle.getString("info_dns2");

            // Use the retrieved data as needed
        }

        view.findViewById(R.id.imageView2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (bottomNavView != null) {
                    bottomNavView.setVisibility(View.VISIBLE);
                }

                // Get the FragmentManager
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WifiFragment());
//                FragmentManager fragmentManager = getFragmentManager();
////                fragmentManager.beginTransaction().add(R.id.fragmentContainer, new WifiFragment());
//
//                // Check if there is a fragment in the back stack
//                if (fragmentManager.getBackStackEntryCount() > 0) {
//                    // Pop the back stack to return to the previous fragment
//                    fragmentManager.popBackStack();
//                } else {
//                    Toast.makeText(getActivity(), "Please Wait", Toast.LENGTH_SHORT).show();
//                    // Handle the case where there is no fragment in the back stack
//                    // For example, you might want to display a message to the user
//                }
                FragmentManager fragmentManager = getFragmentManager();

                // Check if there is a fragment in the back stack
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    // Pop the back stack to return to the previous fragment
                    fragmentManager.popBackStack();
                } else {
                    // Handle the case where there is no fragment in the back stack
                    // For example, you might want to display a message to the user
                }


            }
        });

        wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        ToggleButton toggleButton = view.findViewById(R.id.toggleWifi);

        textview_public_ip = view.findViewById(R.id.textview_public_ip);
        textview_mac = view.findViewById(R.id.textview_mac);
        textview_dns1 = view.findViewById(R.id.textview_dns1);
        textview_dvs2 = view.findViewById(R.id.textview_dvs2);


        textview_mac.setText(info_mac_addr);
        textview_dns1.setText(info_dns1);
        textview_dvs2.setText(info_dns2);


        textview_gateway_ip = view.findViewById(R.id.textview_gateway_ip_value);
        textview_gateway_ip.setText(info_gateway);
        fab_update = view.findViewById(R.id.fab_update_ip); // Assuming you have a FloatingActionButton in your layout

        initConnectivityCheck();
//        initCopyableText();

        initOnClickListeners();

        wifiLiveData = new WiFiLiveData(getActivity());

        runnableIP = new PublicIPRunnable();

//        currentThread = new Thread(runnableIP);
//        currentThread.start();

        backgroundExecutor.execute(runnableIP);



//        new Thread(runnableIP).start();

    }


    private void checkWiFiConnectivity(Boolean shouldStartHandlerThread) {
        connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // Rest of the code remains the same
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        WiFiConnectivityReceiver = new WiFiConnectivityReceiver();
        // Registering the receiver on the Fragment's hosting Activity
        requireActivity().registerReceiver(WiFiConnectivityReceiver, filter);
    }

    public class WiFiConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkWiFiConnectivity(true);
        }
    }



    private void initConnectivityCheck() {
        connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        WiFiCheck = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        // Rest of the code remains the same
    }

    private void initCopyableText() {
        textview_public_ip.setOnLongClickListener(v -> {
            copyToClipboard(getString(R.string.public_ip_address), textview_public_ip.getText().toString());
            return true;
        });
    }

    // Remaining methods such as startInfoHandlerThread, startInfoHandler, stopInfoHandler,
    // stopInfoHandlerThread, getPublicIPAddress, isReachable, etc., will remain largely the same,
    // but make sure that you are using the Fragment's context wherever required.

//    @SuppressWarnings("deprecation")
//    class PublicIPRunnable implements Runnable {
//        @SuppressLint("StaticFieldLeak")
//        @Override
//        public void run() {
//            new AsyncTask<String, Void, Void>() {
//                @Override
//                protected Void doInBackground(String[] voids) {
//                    String url = "https://api.ipify.org";
//                    siteReachable = isReachable(url);
//                    if (isAdded()) { // Check if fragment is attached to the activity
//                        if (siteReachable) {
//                            publicIPFetched = getPublicIPAddress();
//                        } else {
//                            publicIPFetched = getString(R.string.na);
//                        }
//                    }
//
//                    return null;
//                }
//
//                @SuppressLint("SetTextI18n")
//                @Override
//                protected void onPostExecute(Void aVoid) {
//                    super.onPostExecute(aVoid);
//                    if (isAdded()) { // Check if fragment is attached to the activity
//                        textview_public_ip.setText(String.format(getString(R.string.your_ip), publicIPFetched));
//                    }
//                }
//            }.execute();
//
//            Handler handlerEnableFAB = new Handler(Looper.getMainLooper());
//            handlerEnableFAB.postDelayed(() -> fab_update.setEnabled(true), 5000);
//        }
//    }

    class PublicIPRunnable implements Runnable {
        @Override
        public void run() {
            String url = "https://api.ipify.org";
            siteReachable = isReachable(url);
            if (isAdded()) { // Check if fragment is attached to the activity
                if (siteReachable) {
                    publicIPFetched = getPublicIPAddress();
                } else {
                    publicIPFetched = getString(R.string.na);
                }
            }

            // Post the results on the UI thread
            mainThreadHandler.post(() -> {
                if (isAdded()) {
                    textview_public_ip.setText(String.format(getString(R.string.your_ip), publicIPFetched));
                    fab_update.setEnabled(true);
                }
            });
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() != null) {
            getActivity().unregisterReceiver(WiFiConnectivityReceiver);
        }
//        if (currentThread != null) {
//            currentThread.interrupt(); // This will stop the thread if it's still running
//        }

    }


    private void initOnClickListeners() {
//        fab_tools.setOnClickListener(v -> {
//            Intent intent_tools = new Intent(MainActivity.this, ToolsActivity.class);
//            startActivity(intent_tools);
//            fam.close(true);
//        });
//
//        fab_settings.setOnClickListener(v -> {
//            Intent intent_settings = new Intent(MainActivity.this, SettingsActivity.class);
//            intent_settings.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(intent_settings);
//            fam.close(true);
//        });

        fab_update.setOnClickListener(v -> {
            fab_update.setEnabled(false);
            PublicIPRunnable runnableIP = new PublicIPRunnable();
            new Thread(runnableIP).start();
        });
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

    private void copyToClipboard(String label, String text) {
        ClipboardManager cbm = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(label, text);
        cbm.setPrimaryClip(clip);
        Toast.makeText(getContext(), getString(R.string.copied_to_clipboard) + ": " + text, Toast.LENGTH_SHORT).show();
    }

    public boolean handleBackPressed() {
        FragmentManager fragmentManager = getFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            if (bottomNavView != null) {
                bottomNavView.setVisibility(View.VISIBLE);
            }
            return true; // Indicate that back press was handled
        }

        return false; // Indicate that back press was not handled
    }


    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true /* enabled by default */) {
        @Override
        public void handleOnBackPressed() {
            FragmentManager fragmentManager = getFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack();
                if (bottomNavView != null) {
                    bottomNavView.setVisibility(View.VISIBLE);
                }
            } else {
                // If there are no more fragments to pop back from the back stack,
                // Disable this callback and allow the activity's onBackPressed to be called
                setEnabled(false);
                requireActivity().onBackPressed();
            }
        }
    };

    public OnBackPressedCallback getOnBackPressedCallback() {
        return onBackPressedCallback;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (bottomNavView != null) {
                            bottomNavView.setVisibility(View.VISIBLE);
                        }

                        // Get the FragmentManager
                        FragmentManager fragmentManager = getFragmentManager();

                        // Check if there is a fragment in the back stack
                        if (fragmentManager.getBackStackEntryCount() > 0) {
                            // Pop the back stack to return to the previous fragment
                            fragmentManager.popBackStack();
                        } else {
                            // Handle the case where there is no fragment in the back stack
                            // For example, you might want to display a message to the user
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }
}