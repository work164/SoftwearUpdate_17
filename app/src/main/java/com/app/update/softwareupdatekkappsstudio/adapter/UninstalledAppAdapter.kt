package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.database.Word
import com.app.update.softwareupdatekkappsstudio.databinding.ItemUninstalledAppsBinding

class UninstalledAppAdapter(
    private val onItemClick: (packageName: String) -> Unit,
    private val appsList: List<Word>,
    private val context: Context
) :
    RecyclerView.Adapter<UninstalledAppAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUninstalledAppsBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentApp = appsList[position]
        with(holder.binding) {

            appName.text = currentApp.word
            versionName.text = currentApp.vcode

            checkButton.setOnClickListener {
                onItemClick(currentApp.pname)

            }
        }
    }

    override fun getItemCount(): Int {
        return appsList.size
    }

    class ViewHolder(val binding: ItemUninstalledAppsBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}