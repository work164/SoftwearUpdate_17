package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.DhcpInfo
import android.net.NetworkInfo
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Process
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.WifiDetailsActivity
import com.app.update.softwareupdatekkappsstudio.practice.WiFiLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException
import java.net.HttpURLConnection
import java.net.NetworkInterface
import java.net.URL
import java.util.Collections
import java.util.Scanner

class WifiFragment : Fragment() {
    private var wifiManager: WifiManager? = null
    private var wifiLiveData: WiFiLiveData? = null
    var wifidetailsbtn: CardView? = null
    private var isUserInitiatedChange = true
    private var textview_ssid: TextView? = null
    private var connectivityManager: ConnectivityManager? = null
    private var WiFiCheck: NetworkInfo? = null
    private var isHandlerRunning = false
    var info_gateway_ip = ""

    //    private var publicIPFetched: String? = null
    var info_dns1 = ""
    var info_dns2 = ""
    private var dhcpInfo: DhcpInfo? = null

    //    private var siteReachable = false
//    private val fab_update: FloatingActionButton? = null
//    private var WiFiConnectivityReceiver: BroadcastReceiver? = null
    var info_ssid = ""
    private var infoHandler: Handler? = null
    private var infoHandlerThread: HandlerThread? = null
    private var wifiInfo: WifiInfo? = null

    //    private int keyCardFreqFormatted = 1000;
    var info_mac_addr = ""
    var bottomNavView: BottomNavigationView? = null
    var info_network_speed = ""
    var textview_network_speed_value: TextView? = null
    var toggleButton: ToggleButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wifi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wifiManager =
            activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        bottomNavView = activity?.findViewById(R.id.bottomNavView)
        textview_network_speed_value = activity?.findViewById(R.id.textview_network_speed_value)
        toggleButton = view.findViewById(R.id.toggleWifii)
        textview_ssid = view.findViewById(R.id.textview_ssid_value)
        wifidetailsbtn = view.findViewById(R.id.wifidetailsbtn)
        wifidetailsbtn?.setOnClickListener(View.OnClickListener { view1: View? ->
            try {
                val intent = Intent(context, WifiDetailsActivity::class.java)
                intent.putExtra("info_gateway", info_gateway_ip)
                intent.putExtra("info_mac_addr", info_mac_addr)
                intent.putExtra("info_dns1", info_dns1)
                intent.putExtra("info_dns2", info_dns2)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        textview_ssid?.setText(info_ssid)
        initConnectivityCheck()
        wifiLiveData = WiFiLiveData(activity)
        wifiLiveData!!.observe(activity ?: return) { wifiState: Boolean? ->
            if (isAdded && !isDetached) {
                try {
                    isUserInitiatedChange = false
                    toggleButton?.setChecked(wifiState!!)
                } catch (e: IllegalStateException) {
                    e.printStackTrace()
                }
            }
        }
        if (wifiManager != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            toggleButton?.setChecked(wifiManager!!.isWifiEnabled)
            if (wifiManager!!.isWifiEnabled) {
                toggleButton?.setBackgroundResource(R.drawable.toggle_on)
            } else {
                toggleButton?.setBackgroundResource(R.drawable.toggle_off)
            }
        }
        toggleButton?.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { compoundButton: CompoundButton?, isChecked: Boolean ->
            try {
                if (isChecked) {
                    toggleButton?.setBackgroundResource(R.drawable.toggle_on)
                    startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                } else {
                    toggleButton?.setBackgroundResource(R.drawable.toggle_off)
                    showWifiSettingsInstructionDialog()
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        })

//        initCopyableText();
    }

    private fun showWifiSettingsInstructionDialog() {
        try {
            val builder = AlertDialog.Builder(
                activity ?: return
            )
            builder.setTitle("Warning")
            builder.setMessage("if you turn off Wi-Fi, Your internet connectivity will be gone .")
            builder.setPositiveButton("Open Settings") { dialog, which ->
                startActivity(
                    Intent(
                        Settings.ACTION_WIFI_SETTINGS
                    )
                )
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun checkWiFiConnectivity(shouldStartHandlerThread: Boolean) {
        try {
            connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            WiFiCheck = connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
            if (WiFiCheck!!.isConnected) {
                if (shouldStartHandlerThread) {
                    if (!isHandlerRunning) {
                        startInfoHandlerThread()
                        startInfoHandler()
                    }
                }
            } else {
                if (isHandlerRunning) {
                    stopInfoHandler()
                    stopInfoHandlerThread()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Rest of the code remains the same
    }

    private fun stopInfoHandlerThread() {
        try {
            if (infoHandlerThread != null) {
                infoHandlerThread!!.quit()
                infoHandlerThread = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun stopInfoHandler() {
        try {
            if (infoHandler != null) {
                infoHandler?.removeCallbacksAndMessages(infoRunnable)
                isHandlerRunning = false
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startInfoHandlerThread() {
        try {
            infoHandlerThread =
                HandlerThread("BackgroundInfoHandlerThread", Process.THREAD_PRIORITY_BACKGROUND)
            infoHandlerThread!!.start()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun startInfoHandler() {
        try {
            infoHandler = Handler(infoHandlerThread!!.looper)
            infoHandler!!.post(infoRunnable)
            isHandlerRunning = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val infoRunnable: Runnable = Runnable {
        allNetworkInformation
        if (isAdded && activity != null) { // Check if the fragment is added to an activity
            activity?.runOnUiThread { textview_ssid!!.text = info_ssid }
        }
        //            infoHandler.postDelayed(infoRunnable, keyCardFreqFormatted);
    }
    private val allNetworkInformation: Unit
        private get() {
            try {
                if (isAdded) {
                    wifiManager =
                        requireActivity().applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                    wifiInfo = wifiManager!!.connectionInfo
                    dhcpInfo = wifiManager!!.dhcpInfo
                    val dns1 = intToIp(dhcpInfo?.dns1 ?: return)
                    val dns2 = intToIp(dhcpInfo?.dns2 ?: return)
                    val ssid = wifiInfo?.ssid
                    var networkSpeed = 0
                    var TXLinkSpd = 0
                    var RXLinkSpd = 0
                    if (Build.VERSION.SDK_INT >= 29) {
                        TXLinkSpd = wifiInfo?.getTxLinkSpeedMbps() ?: return
                        RXLinkSpd = wifiInfo?.getRxLinkSpeedMbps() ?: return
                    } else {
                        networkSpeed = wifiInfo?.getLinkSpeed() ?: return
                    }
                    val gatewayIp = gatewayIP
                    info_gateway_ip = gatewayIp
                    val macAddr: String
                    macAddr = if (Build.VERSION.SDK_INT > 29) {
                        getString(R.string.na)
                    } else {
                        mACAddress
                    }
                    info_mac_addr = macAddr
                    info_ssid = if (ssid == "<unknown ssid>") {
                        getString(R.string.na)
                    } else {
                        "Connected to " + ssid?.replace("^\"|\"$".toRegex(), "")
                    }
                    info_dns1 = dns1
                    info_dns2 = dns2
                    if (Build.VERSION.SDK_INT >= 29) {
                        val info_network_speed = "$RXLinkSpd / $TXLinkSpd Mbps"

                        // Get the main handler associated with the main Looper
                        val mainHandler = Handler(Looper.getMainLooper())
                        mainHandler.post {
                            textview_network_speed_value!!.text = info_network_speed
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    override fun onPause() {
        super.onPause()
        if (activity != null) {
//            activity?.unregisterReceiver(WiFiConnectivityReceiver?:return)
        }
    }

    private val mACAddress: String
        private get() {
            try {
                val allNetworkInterfaces: List<NetworkInterface> =
                    Collections.list(NetworkInterface.getNetworkInterfaces())
                for (networkInterface in allNetworkInterfaces) {
                    if (!networkInterface.name.equals("wlan0", ignoreCase = true)) continue
                    val macBytes = networkInterface.hardwareAddress ?: return ""
                    val macAddressStringBuilder = StringBuilder()
                    for (b in macBytes) {
                        macAddressStringBuilder.append(String.format("%02X:", b))
                    }
                    if (macAddressStringBuilder.length > 0) {
                        macAddressStringBuilder.deleteCharAt(macAddressStringBuilder.length - 1)
                    }
                    return macAddressStringBuilder.toString()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return ""
        }
    private val gatewayIP: String
        private get() {
            var gatewayIP = 0
            try {
                if (!WiFiCheck!!.isConnected) {
                    return "0.0.0.0"
                }
                wifiManager =
                    activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val dhcp = wifiManager!!.dhcpInfo
                gatewayIP = dhcp.gateway
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return intToIp(gatewayIP)
        }

    private fun intToIp(ipInt: Int): String {
        return ((ipInt and 0xFF).toString() + "."
                + (ipInt shr 8 and 0xFF) + "."
                + (ipInt shr 16 and 0xFF) + "."
                + (ipInt shr 24 and 0xFF))
    }

    override fun onResume() {
        super.onResume()
        try {
            if (bottomNavView != null) {
                bottomNavView!!.visibility = View.VISIBLE
            }
            val filter = IntentFilter()
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
//            WiFiConnectivityReceiver = WiFiConnectivityReceiver()
            // Registering the receiver on the Fragment's hosting Activity
//            requireActivity().registerReceiver(WiFiConnectivityReceiver, filter)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    inner class WiFiConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            try {
                checkWiFiConnectivity(true)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun initConnectivityCheck() {
        connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        WiFiCheck = connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
    }

    /*
        internal inner class PublicIPRunnable : Runnable {
            @SuppressLint("StaticFieldLeak")
            override fun run() {
                object : AsyncTask<String?, Void?, Void?>() {
                    protected override fun doInBackground(voids: Array<String>): Void {
                        val url = "https://api.ipify.org"
                        siteReachable = isReachable(url)
                        if (siteReachable) {
                            publicIPFetched = publicIPAddress
                        } else {
                            publicIPFetched = getString(R.string.na)
                        }
                        return null
                    }

                    @SuppressLint("SetTextI18n")
                    protected override fun onPostExecute(aVoid: Void) {
                        super.onPostExecute(aVoid)
                        //                    textview_public_ip.setText(String.format(getString(R.string.your_ip), publicIPFetched));
                    }
                }.execute()
                val handlerEnableFAB = Handler(Looper.getMainLooper())
                handlerEnableFAB.postDelayed({ fab_update!!.isEnabled = true }, 5000)
            }
        }
    */

    private val publicIPAddress: String
        private get() {
            var publicIP = ""
            try {
                val apiInputStream = URL("https://api.ipify.org").openStream()
                val scanner = Scanner(apiInputStream, "UTF-8").useDelimiter("\\A")
                publicIP = scanner.next()
                apiInputStream.close()
                scanner.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return publicIP
        }

    private fun isReachable(url: String): Boolean {
        var reachable: Boolean
        val response_code: Int
        try {
            val siteURL = URL(url)
            val connection = siteURL.openConnection() as HttpURLConnection
            connection.requestMethod = "GET"
            connection.connectTimeout = 3000
            connection.connect()
            response_code = connection.responseCode
            connection.disconnect()
            reachable = response_code == 200
        } catch (e: Exception) {
            reachable = false
        }
        return reachable
    }
}