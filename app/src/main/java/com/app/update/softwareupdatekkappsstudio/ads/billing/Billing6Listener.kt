package com.app.update.softwareupdatekkappsstudio.ads.billing

import com.android.billingclient.api.ProductDetails

interface Billing6Listener {

    fun purchasesList(productDetailsList: MutableList<ProductDetails>?){

    }
    fun subList(subscriptionDetailsList: MutableList<ProductDetails>?){

    }
    fun purchaseOrSubDone(){

    }
    fun isAnyOldDone(isFoundProd:Boolean,isFoundSub: Boolean){

    }

    fun isAnyOldDoneSub(isWeekly : Boolean, isMonthly : Boolean, isYearly : Boolean){}
}