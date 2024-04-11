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
    LanguageModel(R.drawable.flage_arabic, "中文", "zh", "cn", "China"),
    LanguageModel(R.drawable.flage_china, "pilipino", "fil", "ph", "Philippines"),
    LanguageModel(R.drawable.flage_franch, "हिंदी", "hi", "in", "India"),
    LanguageModel(R.drawable.flage_german, "বাংলা", "bn", "bd", "Bangladesh"),
    LanguageModel(R.drawable.flage_korian, "Italiana", "it", "it", "Italy"),
    LanguageModel(R.drawable.flage_purtagal, "русский", "ru", "ru", "Russia"),
    LanguageModel(R.drawable.flage_turk, "Português", "pt", "br", "Brazil"),
    LanguageModel(R.drawable.flage_spanish, "Português", "pt", "br", "Brazil"),
//    LanguageModel(R.drawable.ic_language_arabic, "العربية", "ar", "sa")
)