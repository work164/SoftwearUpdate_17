package com.app.update.softwareupdatekkappsstudio.utils

import android.content.Context
import android.content.res.Configuration
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.adapter.LanguageModel
import java.util.Locale

fun setLocale(context: Context, languageCode: String, countryCode: String? = null) {
    val locale = if (countryCode != null) {
        Locale(languageCode, countryCode)
    } else {
        Locale(languageCode)
    }

    Locale.setDefault(locale)

    val config = Configuration(context.resources.configuration)
    config.setLocale(locale)

    context.createConfigurationContext(config)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}


val arrayOfLanguages = arrayListOf(
    LanguageModel(R.drawable.flage_english, "English", "en", "us", "United States"),
    LanguageModel(R.drawable.flage_china, "中文", "zh", "cn", "China"),
    LanguageModel(R.drawable.flage_purtagal, "Purtagal", "pt", "ph", "Purtagal"),
    LanguageModel(R.drawable.flage_german, "German", "hi", "ge", "German"),
    LanguageModel(R.drawable.flage_korian, "Korian", "it", "ko", "Korian"),
    LanguageModel(R.drawable.flage_turk, "Turk", "pt", "tu", "Turk"),
    LanguageModel(R.drawable.flage_franch, "Franch", "fr", "fr", "France"),
    LanguageModel(R.drawable.flage_spanish, "Spanish", "sp", "br", "Spanish"),
)