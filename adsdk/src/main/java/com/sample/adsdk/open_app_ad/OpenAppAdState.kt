package com.sample.adsdk.open_app_ad

import com.sample.adsdk.constants.Constant.isOpenAppEnabled

class OpenAppAdState {
    fun enabled(){
        isOpenAppEnabled = true
    }

    fun disabled(){
        isOpenAppEnabled = false
    }
}