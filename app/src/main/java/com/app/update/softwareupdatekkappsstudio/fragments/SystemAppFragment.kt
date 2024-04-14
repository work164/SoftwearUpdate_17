package com.app.update.softwareupdatekkappsstudio.fragments
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.AppsAdapter
import com.app.update.softwareupdatekkappsstudio.adapter.AppsUninstallAdapter
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSystemAppBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.model.SystemModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.Executors

class SystemAppFragment : Fragment(), HomeClick {
    private lateinit var binding: FragmentSystemAppBinding

    private val executorService = Executors.newSingleThreadExecutor()

    private val appsList: MutableList<AppInfo> = ArrayList()
    private var appsAdapter: AppsAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })


        binding = FragmentSystemAppBinding.inflate(inflater, container, false)


        binding.appsRecyclerView.layoutManager = LinearLayoutManager(activity)
        appsAdapter = AppsAdapter(this,appsList, requireActivity())
        binding.appsRecyclerView.adapter = appsAdapter
        loadInstalledApps()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//            code = bundle.getString("code")!!
//            binding.appMainText.text = bundle.getString("check")

    }

    /*
    private fun systemApp(){
        val allFlag = PackageManager.GET_META_DATA or PackageManager.GET_SHARED_LIBRARY_FILES
        listOfApps = this.requireContext().packageManager.getInstalledApplications(allFlag) as ArrayList<ApplicationInfo>

        for (appInfo in listOfApps){
            if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM>0){
                systemAppFragment.add(appInfo)
            }
            else{
                installAppInfo.add(appInfo)
            }
        }
    }
    private fun getMainModel(): ArrayList<SystemModel> {
        val list: ArrayList<SystemModel> = ArrayList()
        var modelSystem:SystemModel
        if (appList.isNotEmpty()){
            for (info in appList){
                val packageName = info.packageName
                val appName = info.loadLabel(requireContext().packageManager).toString()
                val file= File(info.sourceDir)
                val sizeInByte = file.length()
                val lastUpdate = file.lastModified()
                val sizeApp = (sizeInByte/1024)/1024

                val iconApps:Drawable= info.loadIcon(requireContext().packageManager)
                modelSystem = SystemModel(
                    image =iconApps,
                    appName = appName,
                    packageName = packageName,
                    appSize = "size:$sizeApp MB",
                    appDate = lastUpdate.toString()
                )
                list.add(modelSystem)

            }
        }
        return list
    }
*/
    private fun loadInstalledApps() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val packageManager = activity?.packageManager
                val apps = packageManager?.getInstalledApplications(PackageManager.GET_META_DATA)
                for (app in apps ?: return@launch) {
                    val packageName = app.packageName
                    // Check if it's a user app OR a desired system app
                    if (app.flags and ApplicationInfo.FLAG_SYSTEM >0 || AppsUninstallFragment.DESIRED_SYSTEM_APPS.contains(
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
                    binding.textView.text = "Total Apps: ${appsList.size}"
                    appsAdapter?.notifyDataSetChanged()
                    binding.loadingApps.visibility = View.GONE
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

    override fun onUninstallItemClick(name: String, packegeName: String, position: Int) {

        findNavController().navigate(
            R.id.action_systemAppsFragment_to_appDetailFragment,
            Bundle().apply {
                putString("appPackageName", packegeName)
            })

    }
}