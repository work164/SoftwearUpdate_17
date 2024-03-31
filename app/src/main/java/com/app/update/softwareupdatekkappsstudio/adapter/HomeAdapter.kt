package com.app.update.softwareupdatekkappsstudio.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.model.HomeViewModel

class HomeAdapter(private var mList:ArrayList<HomeViewModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeModel = mList[position]
    }

}