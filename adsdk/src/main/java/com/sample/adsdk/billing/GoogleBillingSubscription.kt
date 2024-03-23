package com.sample.adsdk.billing

import android.app.Activity
import android.content.Context
import android.util.Log
import com.android.billingclient.api.AcknowledgePurchaseParams
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingFlowParams
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.PurchasesUpdatedListener
import com.android.billingclient.api.QueryProductDetailsParams
import com.android.billingclient.api.QueryPurchasesParams


class GoogleBillingSubscription(
    val activity: Context
) :
    PurchasesUpdatedListener {
    var onPurchasePassOrFailHistory: ((Purchase?) -> Unit)? = null
    private var oldPurchases: List<Purchase>? = null
    var onPurchase: ((String) -> Unit)? = null
    var currentOffer: String? = null
    var billingReady = false


    fun nextBillingDate(): String {

        return ""
    // return oldPurchases?.first()?.purchaseTime?.plusCurrentDate()
    }


    private val billingClient: BillingClient = BillingClient.newBuilder(activity)
        .enablePendingPurchases()
        .setListener(this)
        .build()


    fun startConnection(
        onBuildConnection: () -> Unit,
        onPurchaseFound: (List<Purchase>) -> Unit,
        onPurchaseNotFound: () -> Unit
    ) {
        billingClient.startConnection(object : BillingClientStateListener {
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                billingReady = billingResult.responseCode == BillingClient.BillingResponseCode.OK
                //getOldPurchases(onPurchaseFound, onPurchaseNotFound)
                onBuildConnection()
                Log.d("OnPurhase", "billingReady:$billingReady  ${billingResult.responseCode}")
            }
            override fun onBillingServiceDisconnected() {
                startConnection(onBuildConnection,onPurchaseFound, onPurchaseNotFound)
            }
        })
    }

    fun getOldPurchases(
        onPurchaseFound: (List<Purchase>) -> Unit,
        onPurchaseNotFound: () -> Unit
    ) {
        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder()
                .setProductType(BillingClient.ProductType.SUBS)//or SUBS
                .build()
        ) { billingResult1, list ->
            if (billingResult1.responseCode == BillingClient.BillingResponseCode.OK && list.isNotEmpty()
            ) {
                oldPurchases = list
                onPurchaseFound.invoke(list)

                //here you can pass the user to use the app because he has an active subscription

            } else if (billingResult1.responseCode == BillingClient.BillingResponseCode.OK && list.isEmpty()
            ) {
                Log.i("OnPurhase", "onPurchaseCallback: need to load purchases")

                onPurchaseNotFound()
            }

        }
    }


    fun querySkuDetails(
        skuList: List<String>,
        type: String = BillingClient.ProductType.SUBS,
        onSuccess: (List<ProductDetails>) -> Unit,
        onError: () -> Unit
    ) {


        val productList = ArrayList<QueryProductDetailsParams.Product>()
        skuList.forEach { sku ->
            productList.add(
                QueryProductDetailsParams.Product.newBuilder()
                    .setProductId(sku)
                    .setProductType(type)
                    .build()
            )
        }

        val params = QueryProductDetailsParams.newBuilder().setProductList(productList).build()

        billingClient.queryProductDetailsAsync(params) { billingResult, productDetailsList ->
            Log.i(
                "onpurhase",
                "querySkuDetails: ${billingResult.responseCode} ${productDetailsList.size} "
            )

            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                onSuccess(productDetailsList)
                Log.i(
                    "onpurhase",
                    "querySkuDetails on success: ${billingResult.responseCode} ${productDetailsList.size} "
                )
            } else {
                onError()
            }
        }
    }


    fun launchBillingFlow(activity: Activity, productDetails: ProductDetails, offer: String?) {
        // An activity reference from which the billing flow will be launched.
        // Log.i("onpurhase", "getOldPurchases:  $nextBillingDate")


        if (oldPurchases.isNullOrEmpty())
            launchSubscribeFlow(activity, productDetails, offer)
        else upgradeBilling(activity, oldPurchases, productDetails, offer)


    }


    fun launchSubscribeFlow(activity: Activity, productDetails: ProductDetails, offer: String?) {
        // An activity reference from which the billing flow will be launched.

        val offerToken = productDetails.subscriptionOfferDetails?.priceToken(offer)


        val productDetailsParamsList =
            listOf(
                offerToken?.let {
                    BillingFlowParams.ProductDetailsParams.newBuilder()
                        .setProductDetails(productDetails)
                        .setOfferToken(it)
                        .build()
                }
            )


        val billingFlowParams =
            BillingFlowParams.newBuilder()
                .setProductDetailsParamsList(productDetailsParamsList)
                .build()

// Launch the billing flow
        billingClient.launchBillingFlow(activity, billingFlowParams)
    }


    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        Log.i("OnPurhase", "onPurchasesUpdated: ${purchases?.size}")

        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            oldPurchases = purchases

            for (purchase in purchases) {

                onPurchasePassOrFailHistory?.invoke(purchase)

                if (purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    Log.i(
                        "OnPurhase",
                        "onPurchasesUpdated: ${purchase.isAutoRenewing} ${purchase.products.size}  ${purchase.products[0]}"
                    )

                    if (!purchase.isAcknowledged && purchase.isAutoRenewing) {
                        val acknowledgePurchaseParams = AcknowledgePurchaseParams
                            .newBuilder()
                            .setPurchaseToken(purchase.purchaseToken)
                            .build()


                        billingClient.acknowledgePurchase(acknowledgePurchaseParams) {
                            // result here

                            onPurchase?.invoke(purchase.products[0])

                            Log.i(
                                "OnPurhase",
                                "aknowlegde: ${it.responseCode == BillingClient.BillingResponseCode.OK}"
                            )

                        }
                    }


                } else if (purchase.purchaseState == Purchase.PurchaseState.UNSPECIFIED_STATE) {
                    // The user has cancelled their subscription
                    // Update their subscription status in your app
                    Log.i("OnPurhase", "onPurchasesUpdated error: ${billingResult.debugMessage}")
                }
            }
        } else onPurchasePassOrFailHistory?.invoke(null)

        Log.i("OnPurhase", "onPurchasesUpdated error: ${billingResult.debugMessage}")

    }


    fun List<ProductDetails.SubscriptionOfferDetails>.priceToken(offer1: String?): String {
        var offerToken: String? = null

        if (offer1 != null)
            offerToken = this.firstOrNull { it.offerId == offer1 }?.offerToken

        Log.i("Offers", "user Token: $offer1  $offerToken")

        currentOffer = offer1

        if (this.isNotEmpty() && offerToken == null)
            offerToken = this.first { it.offerId == null }.offerToken


        return offerToken ?: ""
    }

    fun upgradeBilling(
        activity: Activity,
        currentPurchases: List<Purchase>?,
        productDetails: ProductDetails,
        offer: String?
    ) {
        val offerToken =
            productDetails.subscriptionOfferDetails?.priceToken(offer)
        Log.i("onpurhase", "upgradeBilling: coming to upgrade")
        val oldPurchaseToken: String

        // Get current purchase. In this app, a user can only have one current purchase at
        // any given time.
        if (!currentPurchases.isNullOrEmpty() && currentPurchases.isNotEmpty()) {
            // This either an upgrade, downgrade, or conversion purchase.
            val currentPurchase = currentPurchases.first()
            // Get the token from current purchase.
            oldPurchaseToken = currentPurchase.purchaseToken

            val billingParams = offerToken?.let {
                upDowngradeBillingFlowParamsBuilder(
                    productDetails = productDetails,
                    offerToken = it,
                    oldToken = oldPurchaseToken
                )
            }

            if (billingParams != null) {
                billingClient.launchBillingFlow(
                    activity,
                    billingParams
                )
            }
        }
    }


    private fun upDowngradeBillingFlowParamsBuilder(
        productDetails: ProductDetails,
        offerToken: String,
        oldToken: String
    ): BillingFlowParams {
        return BillingFlowParams.newBuilder().setProductDetailsParamsList(
            listOf(
                BillingFlowParams.ProductDetailsParams.newBuilder()
                    .setProductDetails(productDetails)
                    .setOfferToken(offerToken)
                    .build()
            )
        ).setSubscriptionUpdateParams(
            BillingFlowParams.SubscriptionUpdateParams.newBuilder()
                .setOldPurchaseToken(oldToken)
                .setReplaceProrationMode(
                    BillingFlowParams.ProrationMode.IMMEDIATE_AND_CHARGE_FULL_PRICE
                )
                .build()
        ).build()
    }

}