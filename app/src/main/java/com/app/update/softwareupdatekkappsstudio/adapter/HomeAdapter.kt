package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.databinding.ItemHomeBinding
import com.app.update.softwareupdatekkappsstudio.listeners.HomeClick
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel

class HomeAdapter(val context: Context, private var mList:ArrayList<HomeViewModel>,val homeClick: HomeClick) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemHomeBinding.inflate(LayoutInflater.from(context), parent, false).apply {
            return ViewHolder(this)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeModel = mList[position]
        with(holder.binding) {
            homeImage.setImageResource(homeModel.image)
            homeText1.text = homeModel.featureName
            itemCount.text = homeModel.text
            holder.itemView.setOnClickListener {
                homeClick.onItemClick(homeModel.featureName,position)
            }


        }


    }

    inner class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root)


}