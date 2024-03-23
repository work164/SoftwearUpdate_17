package com.sample.adsdk.callbacks

import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

interface OnNativeView {
        fun nativeViewFind(
            headlineView: TextView,
            bodyView: TextView,
            ad: TextView
        )
    }