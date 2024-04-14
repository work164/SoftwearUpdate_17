package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.BroadcastReceiver
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.practice.WiFiLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL
import java.util.Scanner
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class WifiDetailsFragment : Fragment() {
    private var wifiManager: WifiManager? = null
    private var wifiLiveData: WiFiLiveData? = null
    private val isUserInitiatedChange = true
    private var textview_public_ip: TextView? = null
    private var textview_gateway_ip: TextView? = null
    private var connectivityManager: ConnectivityManager? = null
    private var WiFiCheck: NetworkInfo? = null
    private val isHandlerRunning = false
    private var publicIPFetched: String? = null
    private var siteReachable = false
    private var fab_update: FloatingActionButton? = null
//    private var WiFiConnectivityReceiver: BroadcastReceiver? = null
    private var textview_mac: TextView? = null
    private var textview_dns1: TextView? = null
    private var textview_dvs2: TextView? = null
    var runnableIP: PublicIPRunnable? = null
    var currentThread: Thread? = null
    var info_mac_addr: String? = null
    var info_gateway: String? = null
    var info_dns1: String? = null
    var info_dns2: String? = null
    var bottomNavView: BottomNavigationView? = null
    private val backgroundExecutor: Executor = Executors.newSingleThreadExecutor()
    private val mainThreadHandler = Handler(Looper.getMainLooper())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wifi_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })





        bottomNavView = activity?.findViewById(R.id.bottomNavView)
        val bundle = arguments
        if (bundle != null) {
            info_gateway = bundle.getString("info_gateway")
            info_mac_addr = bundle.getString("info_mac_addr")
            info_dns1 = bundle.getString("info_dns1")
            info_dns2 = bundle.getString("info_dns2")

            // Use the retrieved data as needed
        }
        view.findViewById<ImageFilterView>(R.id.backDevice).setOnClickListener {
            if (bottomNavView != null) {
                bottomNavView?.visibility = View.VISIBLE
            }
            val fragmentManager = fragmentManager

            // Check if there is a fragment in the back stack
            if (fragmentManager!!.backStackEntryCount > 0) {
                // Pop the back stack to return to the previous fragment
                fragmentManager.popBackStack()
            } else {
                // Handle the case where there is no fragment in the back stack
                // For example, you might want to display a message to the user
            }
        }
        wifiManager =
            activity?.applicationContext?.getSystemService(Context.WIFI_SERVICE) as WifiManager
        //        ToggleButton toggleButton = view.findViewById(R.id.toggleWifi);
        textview_public_ip = view.findViewById(R.id.textview_public_ip)
        textview_mac = view.findViewById(R.id.textview_mac)
        textview_dns1 = view.findViewById(R.id.textview_dns1)
        textview_dvs2 = view.findViewById(R.id.textview_dvs2)
        textview_mac?.text = info_mac_addr
        textview_dns1?.text = info_dns1
        textview_dvs2?.text = info_dns2
        textview_gateway_ip = view.findViewById(R.id.textview_gateway_ip_value)
        textview_gateway_ip?.text = info_gateway
        fab_update =
            view.findViewById(R.id.fab_update_ip) // Assuming you have a FloatingActionButton in your layout
        initConnectivityCheck()
        //        initCopyableText();
        initOnClickListeners()
        wifiLiveData = WiFiLiveData(activity)
        runnableIP = PublicIPRunnable()

//        currentThread = new Thread(runnableIP);
//        currentThread.start();
        backgroundExecutor.execute(runnableIP)


//        new Thread(runnableIP).start();
    }

    private fun checkWiFiConnectivity(shouldStartHandlerThread: Boolean) {
        connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        WiFiCheck = connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        // Rest of the code remains the same
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
//        WiFiConnectivityReceiver = WiFiConnectivityReceiver()
        // Registering the receiver on the Fragment's hosting Activity
//        requireActivity().registerReceiver(WiFiConnectivityReceiver, filter)
    }

/*
    inner class WiFiConnectivityReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            checkWiFiConnectivity(true)
        }
    }
*/

    private fun initConnectivityCheck() {
        connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        WiFiCheck = connectivityManager!!.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        // Rest of the code remains the same
    }

    private fun initCopyableText() {
        textview_public_ip!!.setOnLongClickListener { v: View? ->
            copyToClipboard(
                getString(R.string.public_ip_address),
                textview_public_ip!!.text.toString()
            )
            true
        }
    }

    inner class PublicIPRunnable : Runnable {
        override fun run() {
            val url = "https://api.ipify.org"
            siteReachable = isReachable(url)
            if (isAdded) { // Check if fragment is attached to the activity
                if (siteReachable) {
                    publicIPFetched = publicIPAddress
                } else {
                    publicIPFetched = getString(R.string.na)
                }
            }

            // Post the results on the UI thread
            mainThreadHandler.post {
                if (isAdded) {
                    textview_public_ip!!.text =
                        String.format(getString(R.string.your_ip), publicIPFetched)
                    fab_update!!.isEnabled = true
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (activity != null) {
//            activity?.unregisterReceiver(WiFiConnectivityReceiver)
        }
    }

    private fun initOnClickListeners() {
        fab_update?.setOnClickListener { v: View? ->
            fab_update?.isEnabled = false
            val runnableIP: PublicIPRunnable = PublicIPRunnable()
            Thread(runnableIP).start()
        }
    }

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

    private fun copyToClipboard(label: String, text: String) {
        val cbm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText(label, text)
        cbm.setPrimaryClip(clip)
        Toast.makeText(
            context,
            getString(R.string.copied_to_clipboard) + ": " + text,
            Toast.LENGTH_SHORT
        ).show()
    }

    fun handleBackPressed(): Boolean {
        val fragmentManager = fragmentManager
        if (fragmentManager!!.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            if (bottomNavView != null) {
                bottomNavView!!.visibility = View.VISIBLE
            }
            return true // Indicate that back press was handled
        }
        return false // Indicate that back press was not handled
    }

    val onBackPressedCallback: OnBackPressedCallback =
        object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                val fragmentManager = fragmentManager
                if (fragmentManager!!.backStackEntryCount > 0) {
                    fragmentManager.popBackStack()
                    if (bottomNavView != null) {
                        bottomNavView!!.visibility = View.VISIBLE
                    }
                } else {
                    // If there are no more fragments to pop back from the back stack,
                    // Disable this callback and allow the activity's onBackPressed to be called
                    isEnabled = false
//                    requireActivity().onBackPressed()
                }
            }
        }

  /*  override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.isFocusableInTouchMode = true
        view?.requestFocus()
        view?.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (bottomNavView != null) {
                        bottomNavView!!.visibility = View.VISIBLE
                    }

                    // Get the FragmentManager
                    val fragmentManager = fragmentManager

                    // Check if there is a fragment in the back stack
                    if (fragmentManager!!.backStackEntryCount > 0) {
                        // Pop the back stack to return to the previous fragment
                        fragmentManager.popBackStack()
                    } else {
                        // Handle the case where there is no fragment in the back stack
                        // For example, you might want to display a message to the user
                    }
                    return@OnKeyListener true
                }
            }
            false
        })
    }*/
}