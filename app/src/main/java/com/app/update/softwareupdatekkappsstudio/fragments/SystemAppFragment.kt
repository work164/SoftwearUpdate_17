package com.app.update.softwareupdatekkappsstudio.fragments
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentSystemAppBinding
import com.app.update.softwareupdatekkappsstudio.model.SystemModel
import java.io.File

class SystemAppFragment : Fragment() {
    private lateinit var binding:FragmentSystemAppBinding
    var appList:ArrayList<ApplicationInfo> = ArrayList()
    val installAppInfo:ArrayList<ApplicationInfo> = ArrayList()
    val systemAppFragment:ArrayList<ApplicationInfo> = ArrayList()
    var code = ""
    private lateinit var listOfApps:ArrayList<ApplicationInfo>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {



        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })


        binding = FragmentSystemAppBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = arguments
        if (bundle == null){
            Toast.makeText(requireContext(),"sorry no bundle found",Toast.LENGTH_SHORT).show()
        }else{
            code = bundle.getString("code")!!
            binding.appMainText.text = bundle.getString("check")
        }
    }
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

}