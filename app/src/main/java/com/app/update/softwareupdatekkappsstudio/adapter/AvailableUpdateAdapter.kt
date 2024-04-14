package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.databinding.ItemAppBinding
import com.app.update.softwareupdatekkappsstudio.model.AppModel
import com.bumptech.glide.Glide


class AvailableUpdateAdapter(val context: Context, val onItemClick: (AppModel) -> Unit) :
    RecyclerView.Adapter<AvailableUpdateAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemAppBinding) : RecyclerView.ViewHolder(binding.root)

    private val appArrayList: ArrayList<AppModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemAppBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return appArrayList.size

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = appArrayList[position]

        holder.binding.run {
            mbClickItem.text = "Update"


            tvAppName.text = item.appName
            tvAppVersion.text = item.versionName
            tvAppSize.text = item.appSize
            Glide.with(context).load(item.icon).into(ifvIcon)

            mbClickItem.setOnClickListener {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                val dataset = Uri.parse("https://play.google.com/store/apps/details?id=" + item.packageName)
                intent.data = dataset
                try {
                    context.startActivity(intent)
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }

    }

    fun setUpArrayList(arrayList: ArrayList<AppModel>) {
        appArrayList.clear()
        appArrayList.addAll(arrayList)
        notifyDataSetChanged()
    }


}