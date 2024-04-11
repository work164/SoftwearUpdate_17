package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.HomeItemBinding
import com.app.update.softwareupdatekkappsstudio.databinding.ItemLanguageBinding
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel
import com.bumptech.glide.Glide

class HomeAdapter(val context: Context, private var mList:ArrayList<HomeViewModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        HomeItemBinding.inflate(LayoutInflater.from(context), parent, false).apply {
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


        }


    }

    inner class ViewHolder(val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root)


}