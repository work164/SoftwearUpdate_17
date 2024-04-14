package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.AppInfo
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.bumptech.glide.Glide

class AppsAdapter(private val homeClick: HomeClick,private val appsList: List<AppInfo>, private val context: Context) :
    RecyclerView.Adapter<AppsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_app_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentApp = appsList[position]
        holder.appName.text = currentApp.appName
        Glide.with(context).load(currentApp.appIcon).into(holder.appIcon)
        //        holder.appIcon.setImageDrawable(currentApp.getAppIcon());
        holder.checkButton.setOnClickListener {
            homeClick.onUninstallItemClick(currentApp.appName,currentApp.packageName,position)

        }
    }

    override fun getItemCount(): Int {
        return appsList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var appIcon: ImageView
        var appName: TextView
        var checkButton: AppCompatButton

        init {
            appIcon = itemView.findViewById(R.id.appIcon)
            appName = itemView.findViewById(R.id.appName)
            checkButton = itemView.findViewById(R.id.checkButton)
        }
    }
}