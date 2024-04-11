package com.app.update.softwareupdatekkappsstudio.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.update.softwareupdatekkappsstudio.databinding.ItemLanguageBinding
import com.app.update.softwareupdatekkappsstudio.utils.arrayOfLanguages
import com.bumptech.glide.Glide
import com.google.errorprone.annotations.Keep

class LanguageAdaptor(
    val context: Context,
    private var languageCode: String,
    val onItemClick: (languageModel: LanguageModel) -> Unit
) : RecyclerView.Adapter<LanguageAdaptor.ViewHolder>() {
    private val languageArray: ArrayList<LanguageModel> = arrayOfLanguages

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        ItemLanguageBinding.inflate(LayoutInflater.from(context), parent, false).apply {
            return ViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = languageArray[position]
        with(holder.binding) {
            if (languageCode == item.languageCode) {
                vSelectedItem.visibility = View.VISIBLE
            } else {
                vSelectedItem.visibility = View.GONE
            }
           // tvLanguageName.text = item.name
            tvCountryName.text = item.countryName
            Glide.with(context).load(languageArray[position].icon).into(ifvLanguageIcon)
            clLanguageItem.setOnClickListener {
                languageCode = item.languageCode
                onItemClick(item)
                notifyDataSetChanged()
            }


        }

    }

    override fun getItemCount(): Int {
        return languageArray.size
    }

    inner class ViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root)
}

@Keep
data class LanguageModel(
    val icon: Int,
    val name: String,
    val languageCode: String,
    val countryCode: String,
    val countryName: String
)
