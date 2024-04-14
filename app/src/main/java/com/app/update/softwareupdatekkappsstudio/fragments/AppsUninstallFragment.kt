package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppsUninstallAdapter
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Collections

class AppsUninstallFragment : Fragment(), HomeClick {
    private var textView: TextView? = null
    private var loadingApps: ProgressBar? = null
    private var appsRecyclerView: RecyclerView? = null
    private val appsList: MutableList<AppInfo> = ArrayList()
    private var appsAdapter: AppsUninstallAdapter? = null
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
        return inflater.inflate(R.layout.fragment_apps_uninstall, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView = view.findViewById(R.id.textView)
        appsRecyclerView = view.findViewById(R.id.appsRecyclerView)
        loadingApps = view.findViewById(R.id.loadingApps)
        appsRecyclerView?.layoutManager = LinearLayoutManager(activity)
        appsAdapter = AppsUninstallAdapter(this, appsList, requireActivity())
        appsRecyclerView?.adapter = appsAdapter
        loadInstalledApps()
    }

    private fun loadInstalledApps() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val packageManager = activity?.packageManager
                val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
                for (app in apps ?: return@launch) {
                    val packageName = app.packageName
                    // Check if it's a user app OR a desired system app
                    if (app.flags and ApplicationInfo.FLAG_SYSTEM == 0 || DESIRED_SYSTEM_APPS.contains(
                            packageName
                        )
                    ) {
                        val appName = packageManager.getApplicationLabel(app) as String
                        val appIcon = packageManager.getApplicationIcon(app)
                        if (isAvailableOnPlayStore(packageName)) {
                            appsList.add(AppInfo(appName, appIcon, packageName))
                        }
                    }
                }
                // Sort apps alphabetically by their names
                appsList.sortWith(Comparator { o1: AppInfo, o2: AppInfo ->
                    o1.appName.compareTo(
                        o2.appName,
                        ignoreCase = true
                    )
                })
                CoroutineScope(Dispatchers.Main).launch {
                    textView?.text = "Total Apps: " + appsList.size
                    appsAdapter?.notifyDataSetChanged()
                    loadingApps?.visibility = View.GONE
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isAvailableOnPlayStore(packageName: String): Boolean {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
        val activities = activity?.packageManager?.queryIntentActivities(intent, 0)
        return activities?.isNotEmpty() ?: return false
    }


    companion object {
         val DESIRED_SYSTEM_APPS: List<String> = mutableListOf(
            "com.google.android.googlequicksearchbox",  // Google
            "com.facebook.katana",  // Facebook
            "com.google.android.youtube",  // YouTube
            "com.google.android.apps.docs",  // Docs
            "com.google.android.apps.photos",  // Photos
            "com.google.android.gm",  // Gmail
            "com.google.android.apps.drive",  // Drive
            "com.android.chrome",  // Google Chrome
            "com.google.android.apps.maps",  // Google Maps
            "com.google.android.apps.weather" // Weather - Note: this package might differ based on the actual Weather app
        )
    }


    override fun onUninstallItemClick(name: String, packegeName: String, position: Int) {

        findNavController().navigate(R.id.action_appUninstallFragment_to_appDetailFragment, Bundle().apply {
            putString("appPackageName", packegeName)
        })


       /* val builder = AlertDialog.Builder(
            requireContext()
        )
        builder.setTitle("Uninstall App")
        builder.setMessage("Do you want to uninstall $name?")
        builder.setPositiveButton("OK") { dialog: DialogInterface?, which: Int ->
            val packageUri = Uri.parse("package:$packegeName")
            val uninstallIntent = Intent(Intent.ACTION_UNINSTALL_PACKAGE, packageUri)
            startActivity(uninstallIntent)
            appsAdapter?.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancel", null)
        builder.show()*/



        super.onUninstallItemClick(name, packegeName, position)
    }
}