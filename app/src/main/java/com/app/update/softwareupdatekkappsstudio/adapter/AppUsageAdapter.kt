package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bot.box.appusage.model.AppData
import bot.box.appusage.utils.UsageUtils
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.ItemAppUsageBinding
import com.bumptech.glide.Glide
import java.io.File

class AppUsageAdapter(val context: Context, val onItemClick: (String) -> Unit) :
    RecyclerView.Adapter<AppUsageAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemAppUsageBinding) : RecyclerView.ViewHolder(binding.root)

    private val appArrayList: ArrayList<AppData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAppUsageBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return appArrayList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = appArrayList[position]

        holder.binding.run {
            tvAppName.text = item.mName
//            tvAppVersion.text = item.
            tvLaunches.text = "Launches: " + item.mCount.toString()
            tvAppUsageTime.text = UsageUtils.humanReadableMillis(item.mUsageTime)
            Glide.with(context)
                .load(UsageUtils.parsePackageIcon(item.mPackageName, R.mipmap.ic_launcher))
                .into(ifvIcon)


            //total lunch
            //total lunch
            val pm = context.packageManager
            var applicationInfo: ApplicationInfo? = null
            try {
                applicationInfo = pm.getApplicationInfo(item.mPackageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            val appName = applicationInfo!!.loadLabel(context.packageManager).toString()
            val file = File(applicationInfo.publicSourceDir)
            val size = file.length().toInt()
//            holder.appNAme.setText(appName)


        }
        holder.itemView.setOnClickListener {
//            openSetting(item.mPackageName)
            onItemClick(item.mPackageName)
        }
    }

    fun setUpArrayList(arrayList: List<AppData>) {
        appArrayList.clear()
        appArrayList.addAll(arrayList)
        notifyDataSetChanged()
    }

    private fun openSetting(packageName: String) {
        try {
            //Open the specific App Info page:
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:$packageName")
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            //e.printStackTrace();

            //Open the generic Apps page:
            val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
            context.startActivity(intent)
        }
    }
}