package com.app.update.softwareupdatekkappsstudio.ads.billing

import android.app.Activity
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams

import com.facebook.common.internal.ImmutableList
import com.app.update.softwareupdatekkappsstudio.ads.ConnectivityStatus
import com.sample.adsdk.billing.PurchasePrefs
import com.sample.adsdk.constants.Constant.isNetworkAvailable
import com.sample.adsdk.constants.Constant.mInterstitialAd

class Billing6Play(
    val activity: Activity,
    private val productIds: ArrayList<String>,
    private val subscriptionIds: ArrayList<String>,
    private val billing6Listener: Billing6Listener
) {


    private var billingClient: BillingClient? = null
    private var productDetailsList: MutableList<ProductDetails>? = ArrayList()
    private var subscriptionDetailsList: MutableList<ProductDetails>? = ArrayList()
    private var connectivityLiveData: ConnectivityStatus? = null

    private var handler: Handler? = Handler(Looper.getMainLooper())
    private var logTag = "billingInApp"


    companion object {
        var isAtLeastOneSub = false
        var isAtLeastOneProd = false
    }

    private var isWeekly = false
    private var isMonthly = false
    private var isYearly = false


    init {
        try {
            connectivityLiveData = ConnectivityStatus(activity.application)
            billingSetup()
            establishConnection()
        } catch (e: java.lang.SecurityException) {
           e.printStackTrace()
        }
    }


    fun oneTimeProduct() {
        if (isNetworkAvailable(activity)) {
            if (!productDetailsList.isNullOrEmpty()) {
                launchProdFlow(productDetailsList?.get(0) ?: return)
            } else {
                Toast.makeText(activity, "No Product is available.", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }


    private fun establishConnection() {
        if (isNetworkAvailable(activity)) {
            try {
                billingClient?.startConnection(object : BillingClientStateListener {
                    override fun onBillingSetupFinished(billingResult: BillingResult) {
                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                            Log.d(logTag, "onBillingSetupFinished with OK")
                            showProducts()
                        } else {
                            Log.d(logTag, "onBillingSetupFinished with Not OK")
                        }
                    }

                    override fun onBillingServiceDisconnected() {
                        Log.d(logTag, "onBillingServiceDisconnected")
                       // establishConnection()
                    }
                })
            } catch (e: Exception) {
               e.printStackTrace()
            }
        }

    }


    private fun showProducts() {
        try {
            val subList = ArrayList<QueryProductDetailsParams.Product>()
            try {
                if (subscriptionIds.size > 0) {
                    subscriptionIds.forEach { ids ->
                        subList.add(
                            QueryProductDetailsParams.Product.newBuilder()
                                .setProductId(ids)
                                .setProductType(BillingClient.ProductType.SUBS)
                                .build()
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            val prodList = ArrayList<QueryProductDetailsParams.Product>()
            try {
                if (productIds.size > 0) {
                    productIds.forEach { ids ->
                        prodList.add(
                            QueryProductDetailsParams.Product.newBuilder()
                                .setProductId(ids)
                                .setProductType(BillingClient.ProductType.INAPP)
                                .build()
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }


            if (subList.size > 0) {
                val paramsSubscriptions = QueryProductDetailsParams.newBuilder()
                    .setProductList(subList ?: return)
                    .build()


                //
                billingClient?.queryProductDetailsAsync(
                    paramsSubscriptions ?: return
                ) { billingResult: BillingResult?, prodDetailsList: List<ProductDetails> ->
                    try {
                        if (billingResult?.responseCode == BillingClient.BillingResponseCode.OK) {
                            if (prodDetailsList.isNotEmpty()) {
                                Log.d(
                                    logTag,
                                    "onBillingService Sub Size -> " + prodDetailsList.size
                                )
                                subscriptionDetailsList?.clear()
                                handler?.postDelayed({
                                    Log.d(logTag, "DetailsList Sub List-> $prodDetailsList")
                                    subscriptionDetailsList?.addAll(prodDetailsList)
                                    billing6Listener.subList(subscriptionDetailsList)
                                }, 10)
                            } else {
                                Toast.makeText(
                                    activity,
                                    "Not Subscriptions is available at Store.",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }


            }
            if (prodList.size > 0) {
                val paramsProducts =
                    QueryProductDetailsParams.newBuilder()
                        .setProductList(prodList)
                        .build()


                billingClient?.queryProductDetailsAsync(
                    paramsProducts
                ) { _: BillingResult?, prodDetailsList: List<ProductDetails> ->
                    try {
                        if (prodDetailsList.isNotEmpty()) { // checking if there's a product returned then set the product(s)
                            Log.d(
                                logTag,
                                "onBillingService Product Size -> " + prodDetailsList.size
                            )
                            productDetailsList?.clear()
                            handler?.postDelayed({
                                Log.d(logTag, "DetailsList Prod List->  $prodDetailsList")
                                productDetailsList?.addAll(prodDetailsList)
                                billing6Listener.purchasesList(productDetailsList)

                            }, 10)
                        } else {
                            Toast.makeText(
                                activity,
                                "Not Products is available at Store.",
                                Toast.LENGTH_SHORT
                            ).show()

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    private fun billingSetup() {
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases()
            .setListener { billingResult: BillingResult, list: List<Purchase?>? ->
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && list != null) {
                    for (purchase in list) {
                        verifySubPurchase(purchase ?: return@setListener)
                    }
                } else {
                    Log.d(logTag, "Not old purchase found")
                }
            }.build()
    }


    private fun verifySubPurchase(purchases: Purchase) {
        val acknowledgePurchaseParams = AcknowledgePurchaseParams
            .newBuilder()
            .setPurchaseToken(purchases.purchaseToken)
            .build()
        billingClient?.acknowledgePurchase(
            acknowledgePurchaseParams
        ) { billingResult: BillingResult ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {

                mInterstitialAd = null
                PurchasePrefs(activity).putBoolean("inApp", true)

            }
        }
    }


    private fun launchSubFlow(productDetails: ProductDetails) {
        if (productDetails.subscriptionOfferDetails != null) {
            val productDetailsParamsList = ImmutableList.of(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .setOfferToken(
                        productDetails.subscriptionOfferDetails?.get(0)?.offerToken ?: return
                    )
                    .build()
            )
            if (productDetailsParamsList.size > 0) {
                val billingFlowParams = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList ?: return)
                    .build()
                Log.d(logTag, "launchBillingFlow  ")
                billingClient?.launchBillingFlow(activity, billingFlowParams ?: return)
            }
        }
    }


    private fun launchProdFlow(productDetails: ProductDetails) {
        if (productDetails.oneTimePurchaseOfferDetails != null) {
            val productDetailsParamsList = ImmutableList.of(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails ?: return)
                    .build()
            )
            if (productDetailsParamsList.size > 0) {
                val billingFlowProduct = BillingFlowParams.newBuilder()
                    .setProductDetailsParamsList(productDetailsParamsList)
                    .build()
                Log.d(logTag, "launchBillingFlow  ")
                billingClient?.launchBillingFlow(activity, billingFlowProduct ?: return)
            }
        }
    }


    fun checkOldProductSubscription() {
        billingClient = BillingClient.newBuilder(activity).enablePendingPurchases()
            .setListener { _: BillingResult?, list: List<Purchase?>? -> }.build()
        val finalBillingClient: BillingClient = billingClient ?: return
        billingClient!!.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {}
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {

                    finalBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                            .setProductType(BillingClient.ProductType.INAPP).build()
                    ) { billingResult1: BillingResult, list: List<Purchase> ->
                        if (billingResult1.responseCode == BillingClient.BillingResponseCode.OK) {
                            Log.d(logTag, list.size.toString() + "old Products size")
                            Log.d(logTag, "old Products $list")

                            if (list.isNotEmpty()) {
                                isAtLeastOneProd = true
                                billing6Listener.isAnyOldDone(
                                    isFoundProd = isAtLeastOneProd,
                                    isFoundSub = isAtLeastOneSub
                                )
                                PurchasePrefs(activity).putBoolean("product", true)
                                PurchasePrefs(activity).putBoolean("inApp", true)

                            } else {
                                PurchasePrefs(activity).putBoolean("product", false)
                                PurchasePrefs(activity).putBoolean("inApp", false)
                            }
                        }
                    }
                    finalBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                            .setProductType(BillingClient.ProductType.SUBS).build()
                    ) { billingResult1: BillingResult, list: List<Purchase> ->
                        if (billingResult1.responseCode == BillingClient.BillingResponseCode.OK) {
                            Log.d(logTag, list.size.toString() + "old Subscriptions size")
                            Log.d(logTag, "old Subscriptions $list")
                            if (list.isNotEmpty()) {
                                isAtLeastOneSub = true

                                billing6Listener.isAnyOldDone(
                                    isFoundProd = isAtLeastOneProd,
                                    isFoundSub = isAtLeastOneSub
                                )

                            } else {
                                PurchasePrefs(activity).putBoolean(
                                    "inApp",
                                    false
                                ) // set 0 to de-activate premium feature
                            }
                            if (list.isNotEmpty()) {
                                if (list.size == 3) {
                                    isMonthly = true
                                    isWeekly = true
                                    isYearly = true
                                    billing6Listener.isAnyOldDoneSub(
                                        isWeekly = isWeekly,
                                        isMonthly = isMonthly,
                                        isYearly = isYearly
                                    )
                                } else {
                                    for (purchase in list) {
                                        for (i in subscriptionIds.indices) {
                                            if (purchase.products[0] == "sub_week") {
                                                isWeekly = true
                                                billing6Listener.isAnyOldDoneSub(
                                                    isWeekly = isWeekly,
                                                    isMonthly = isMonthly,
                                                    isYearly = isYearly
                                                )
                                            } else if (purchase.products[0] == "sub_month") {
                                                isMonthly = true
                                                billing6Listener.isAnyOldDoneSub(
                                                    isWeekly = isWeekly,
                                                    isMonthly = isMonthly,
                                                    isYearly = isYearly
                                                )
                                            } else if (purchase.products[0] == "sub_year") {
                                                isYearly = true
                                                billing6Listener.isAnyOldDoneSub(
                                                    isWeekly = isWeekly,
                                                    isMonthly = isMonthly,
                                                    isYearly = isYearly
                                                )
                                            }
                                        }
                                    }
                                }
                            } else {
                                PurchasePrefs(activity).putBoolean(
                                    "inApp",
                                    false
                                )
                            }
                        }
                    }
                }
            }
        })
    }


    fun checkPurchased(context: Context): Boolean {
        return PurchasePrefs(context).getBoolean("inApp")
    }


}