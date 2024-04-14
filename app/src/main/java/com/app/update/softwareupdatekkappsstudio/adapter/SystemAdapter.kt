package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.model.SystemModel

class SystemAdapter(private var mList: ArrayList<SystemModel>,val context: Context,private val onItemClik :(packageName:String)->Unit) : RecyclerView.Adapter<SystemAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_system_app,parent,false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return mList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val systemModel = mList[position]
        val image= holder.itemView.findViewById<ImageView>(R.id.systemIcon)
        val allVideo = holder.itemView.findViewById<TextView>(R.id.mainText1)
        val sizeValue= holder.itemView.findViewById<TextView>(R.id.sizeMb)
        val dateCheck = holder.itemView.findViewById<TextView>(R.id.date)
        val checkButton = holder.itemView.findViewById<Button>(R.id.checkMove)
//        image.setImageResource(systemModel.image)
        allVideo.text = systemModel.appName
        sizeValue.text = systemModel.appSize
        dateCheck.text = systemModel.appDate
        checkButton.setOnClickListener {
            onItemClik(systemModel.packageName)
        }
    }
}