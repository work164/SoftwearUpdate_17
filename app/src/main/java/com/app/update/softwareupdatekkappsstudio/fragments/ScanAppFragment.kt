package com.app.update.softwareupdatekkappsstudio.fragments

import android.app.Dialog
import android.app.ProgressDialog
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentScanAppBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithMediaBinding
import com.app.update.softwareupdatekkappsstudio.databinding.NativeWithOutMediaBinding
import com.app.update.softwareupdatekkappsstudio.model.AppModel
import com.app.update.softwareupdatekkappsstudio.utils.Constants
import com.app.update.softwareupdatekkappsstudio.view_model.ViewModelclass
import com.bumptech.glide.Glide
import com.example.adssdk.advert.firebaseAnalytics
import com.example.adssdk.banner_ads.BannerAdUtils
import com.example.adssdk.constants.AppUtils
import com.example.adssdk.intertesialAds.InterstitialAdUtils
import com.example.adssdk.native_ad.NativeAdUtils
import com.google.android.material.snackbar.Snackbar
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.EOFException
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ScanAppFragment : Fragment() {
    private lateinit var binding: FragmentScanAppBinding
    private lateinit var allAppList: ArrayList<ApplicationInfo>
    lateinit var newupdatedDate: Date
    lateinit var lastModifiedDate: Date
    private var appDataList = ArrayList<AppModel>()
    lateinit var viewModel: ViewModelclass
    private val launchableInstalledApps: MutableList<ApplicationInfo> = ArrayList()
    private var exit = false
    private var wait = false
    var i = 0
    var z = 1
    lateinit var progressDialog: Dialog

    companion object {
        val updateAvailableList: ArrayList<AppModel> = ArrayList()
    }

    private val blinkAnimation by lazy {
        AnimationUtils.loadAnimation(requireContext(), R.anim.blink_animation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    backPress()
                }
            })


        binding = FragmentScanAppBinding.inflate(inflater, container, false)
        loadAds()
        updateAvailableList.clear()

        viewModel = ViewModelProvider(this)[ViewModelclass::class.java]
        binding.tvScanning.startAnimation(blinkAnimation)
        progressDialog = Dialog(requireContext())
        progressDialog.setContentView(R.layout.loading_dialog)
        progressDialog.show()
        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    systemAndDownloadedAppsSeparator()
                } catch (e: PackageManager.NameNotFoundException) {
                    e.printStackTrace()
                }
                requireActivity().runOnUiThread {
                    if (checkInternetAvailability()) {
                        appDataList = viewModel.getInto(requireActivity(), allAppList)
                        PrepareAppsData().execute()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "You are not connected to Internet",
                            Toast.LENGTH_LONG
                        ).show()
                        findNavController().popBackStack()
                    }
                    progressDialog.dismiss()
                }
            }
        }
        thread.start()
        return binding.root
    }

    private fun systemAndDownloadedAppsSeparator() {

        allAppList = ArrayList()
        val flag =
            PackageManager.GET_META_DATA or PackageManager.GET_SHARED_LIBRARY_FILES or PackageManager.GET_UNINSTALLED_PACKAGES
        val listOfAllapps = requireContext().packageManager.getInstalledApplications(flag)
        for (list in listOfAllapps) {
            if (requireContext().packageManager.getLaunchIntentForPackage(list.packageName) != null) {
                //If you're here, then this is a launch-able app//
                launchableInstalledApps.add(list);
            }
        }
        for (apInfo in launchableInstalledApps) {

            allAppList.add(apInfo)


        }


    }

    private fun checkInternetAvailability(): Boolean {
        try {
            val cm =
                requireContext().getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            if (activeNetwork != null) {
                if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                    if (activeNetwork.state == NetworkInfo.State.CONNECTED) {
                        return true

                    }
                } else if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                    if (activeNetwork.state == NetworkInfo.State.CONNECTED) {
                        return true

                    }
                }
            }
        } catch (e: Exception) {
            Snackbar.make(
                requireActivity().findViewById(android.R.id.content),
                e.toString(),
                Snackbar.LENGTH_LONG
            ).setActionTextColor(
                Color.RED
            ).show()
        }


        return false
    }

    private fun backPress() {
        if (checkInternetAvailability()) {
            pause()
            AlertDialog.Builder(requireContext()).apply {
                setTitle("Please confirm.")
                setMessage("Are you want to stop scanning update?")

                setPositiveButton("Stop") { _, _ ->
                    // if user press yes, then finish the current activity
                    stop()
                }

                setNegativeButton("No") { _, _ ->
                    // if user press no, then return the activity
                    resume()
                }

                setCancelable(false)
            }.create().show()
        } else {
            findNavController().popBackStack()
        }

    }

    private fun convertLongToTime(time: Long): String {

        val format = SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = time
        return format.format(calendar.time)
    }

    private fun stop() {

        exit = true
        if (findNavController().currentDestination?.id == R.id.scanAppsFragment) {
            onBackWithAd()
        }
    }

    private fun pause() {
        wait = true
    }

    private fun resume() {
        wait = false
    }

    private fun checkUpdate() {

        val run = Runnable {

            while (allAppList.isNotEmpty() && (i <= (allAppList.size - 1)) && !exit) {
                try {
                    println("hello for while")
                    if (!wait) {
                        println("hello not wait")
                        if (i != z) {
                            z = i

                            val time = requireContext().packageManager.getPackageInfo(
                                appDataList[i].packageName,
                                0
                            ).lastUpdateTime

                            val lastModifiedTime = convertLongToTime(time)

                            lastModifiedDate =
                                SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).parse(
                                    lastModifiedTime
                                )!!


                            requireActivity().runOnUiThread {
                                binding?.run {

                                    try {
                                        tvUpdateFoundValue.text =
                                            updateAvailableList.size.toString()
                                        tvCheckingUpdateValue.text =
                                            i.toString() + "/" + (allAppList.size - 1)
                                        checkingUpdate.max = allAppList.size - 1
                                        checkingUpdate.progress = i

                                        Glide.with(requireContext()).load(appDataList[i].icon)
                                            .into(ifvAppIcon)
                                        tvAppName.text = appDataList[i].appName
                                    } catch (e: IndexOutOfBoundsException) {
                                        e.printStackTrace()
                                    } catch (e: java.lang.Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            }

                            val statuscode = CheckAppLiveOnPlayStore(appDataList[i]).execute().get()
                            Log.e("status code", statuscode)
                            if (statuscode.toInt() == 200) {

                                VersionCracker(appDataList[i]).execute()

                            } else {
                                i++
                            }
                        }
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }

            if (exit) {
//                if (findNavController().currentDestination?.id == R.id.scanAppsFragment){
//                    findNavController().popBackStack()
//                }
            } else {
                requireActivity().runOnUiThread {

                    if (isVisible) {
                        findNavController().navigate(R.id.action_scanFragment_to_availableAppsUpdateFragment)

                        InterstitialAdUtils(
                            requireActivity(),
                            "fullscreen_scan_app_details"
                        ).showInterstitialAd(
                            getString(R.string.val_fullscreen_scan_app_details),
                            Constants.val_fullscreen_scan_app_details,
                            fullScreenAdShow = {
                                firebaseAnalytics(
                                    "scan_app_interstitial_failed",
                                    "scan_app_interstitial_failed"
                                )
                            },
                            fullScreenAdDismissed = {
                                firebaseAnalytics(
                                    "scan_app_interstitial_dismissed",
                                    "scan_app_interstitial_dismissed"
                                )
                            },
                            fullScreenAdFailedToShow = {
                                firebaseAnalytics(
                                    "scan_app_interstitial_failed_to_show",
                                    "scan_app_interstitial_failed_to_show"
                                )
                            },
                            fullScreenAdNotAvailable = {},
                        )
                    }
                }

            }

        }

        val objBgThread = Thread(run)
        objBgThread.start()
        //UAlist.value = updateAvailableList

    }

    inner class PrepareAppsData() : AsyncTask<Void, Void, String>() {
        val progressDialog = ProgressDialog(requireContext())
        override fun doInBackground(vararg params: Void?): String? {


            return null
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog.setTitle("wait ")
            progressDialog.setMessage("Preparing apps data, please wait")
            progressDialog.show()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            checkUpdate()
        }
    }

    inner class CheckAppLiveOnPlayStore(val app: AppModel) :
        AsyncTask<String, String, String>() {


        override fun doInBackground(vararg p0: String?): String? {
            var responseStatus: Int? = null
            try {
                val conn = URL("https://play.google.com/store/apps/details?id=${app.packageName}")
                    .openConnection() as HttpURLConnection
                // conn.useCaches = false
                conn.connect()
                responseStatus = conn.responseCode
                conn.disconnect()
            } catch (e: EOFException) {
                e.printStackTrace()
            } catch (e: java.net.ConnectException) {
                e.printStackTrace()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }


            // Log.e("status",status.toString())

            return responseStatus.toString()
        }
    }

    inner class VersionCracker(val app: AppModel) : AsyncTask<String, String, String>() {


        override fun doInBackground(vararg p0: String?): String? {

            try {
                val document: Document? =
                    Jsoup.connect("https://play.google.com/store/apps/details?id=${app.packageName}")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                // Log.e("helloo", "helloo")
                if (document != null) {

                    val element: Elements = document.getElementsByClass("lXlx5")
                    //  Log.e("element", element.toString())
                    for (ele in element) {
                        if (ele.siblingElements() != null) {
                            val sibElemets: Elements = ele.siblingElements()
                            for (sibElemet in sibElemets) {
                                val newUpdatedDate = sibElemet.text()
                                //Log.e("newupdatedDate1222", newUpdatedDate)
                                // val formatter =
                                // DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH)
                                newupdatedDate =
                                    SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH).parse(
                                        newUpdatedDate
                                    )!!

                                // Log.e("newupdatedDate", newupdatedDate.toString())


                                if (lastModifiedDate < newupdatedDate) {
                                    updateAvailableList.add(app)
                                    i++

                                } else {
                                    i++
                                }

                            }
                        }
                    }

                }
            } catch (e: EOFException) {
                e.printStackTrace()
            } catch (e: java.net.ConnectException) {
                e.printStackTrace()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }

            return null
        }


    }

    private fun onBackWithAd() {
        findNavController().popBackStack()
        showAd()

    }


    private fun loadAds() {
        if (AppUtils.isNetworkAvailable(requireContext())) {
            binding.nativeAdScan.visibility = View.VISIBLE
            val bindAdSystemUpdate = NativeWithOutMediaBinding.inflate(layoutInflater)
            NativeAdUtils(
                requireActivity().application,
                "native_scan"
            ).setAdCallerName("native_scan")
                .loadNativeAd(
                    getString(R.string.val_native_scan),
                    Constants.val_native_scan,
                    binding.nativeAdScan,
                    bindAdSystemUpdate.root,
                    bindAdSystemUpdate.adAppIcon,
                    bindAdSystemUpdate.adHeadline,
                    bindAdSystemUpdate.adBody,
                    bindAdSystemUpdate.adCallToAction,
                    null,
                    null,
                    adFailed = {
                        binding.nativeAdScan.visibility = View.GONE
                    },
                    adValidate = {
                        binding.nativeAdScan.visibility = View.VISIBLE
                        BannerAdUtils(activity = requireActivity(), screenName = "banner_scan")
                            .loadBanner(
                                adsKey = getString(R.string.val_banner_scan), // give ad id here
                                remoteConfig = Constants.val_banner_scan, // give remote config here
                                adsView = binding.nativeAdScan, //give your frameLayout here
                                onAdClicked = {}, //if ad clicked you will receive this callback
                                onAdFailedToLoad = {
                                    binding.nativeAdScan.visibility = View.GONE
                                }, // if ad failed to load you will receive this callback
                                onAdImpression = {}, // if ad impression will receive this callback
                                onAdLoaded = {}, // if ad loaded you will receive this callback
                                onAdOpened = {}, // if ad opened you will receive this callback
                                onAdValidate = {
                                    binding.nativeAdScan.visibility = View.GONE
                                }) //if remote off or no internet or user is premium user you will receive callback here

                    },
                    adClicked = {

                    },
                    adImpression = {

                    }
                )
            InterstitialAdUtils(requireActivity(), "fullscreen_scan_back").loadInterstitialAd(
                getString(R.string.val_fullscreen_scan_app_details),
                if (Constants.val_fullscreen_scan_back) Constants.val_fullscreen_scan_back else Constants.val_fullscreen_scan_app_details,
                adAlreadyLoaded = {

                },
                adLoaded = {
                    firebaseAnalytics(
                        "scan_app_interstitial_loaded",
                        "scan_app_interstitial_loaded"
                    )

                },
                adFailed = {
                    firebaseAnalytics(
                        "scan_app_interstitial_failed",
                        "scan_app_interstitial_failed"
                    )

                },
                adValidate = {

                },
            )
        } else {
            binding.nativeAdScan.visibility = View.GONE
        }
    }

    private fun showAd() {
        InterstitialAdUtils(
            requireActivity(),
            "fullscreen_scan_back"
        ).showInterstitialAd(
            getString(R.string.val_fullscreen_scan_app_details),
            Constants.val_fullscreen_scan_back,
            fullScreenAdShow = {},
            fullScreenAdDismissed = {},
            fullScreenAdFailedToShow = {},
            fullScreenAdNotAvailable = {},
        )
    }

}