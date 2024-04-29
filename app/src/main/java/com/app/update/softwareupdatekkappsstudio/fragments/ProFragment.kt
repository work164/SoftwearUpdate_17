package com.app.update.softwareupdatekkappsstudio.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.android.billingclient.api.BillingClient
import com.android.billingclient.api.BillingClientStateListener
import com.android.billingclient.api.BillingResult
import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase
import com.android.billingclient.api.QueryPurchasesParams
import com.app.update.softwareupdatekkappsstudio.R
import com.app.update.softwareupdatekkappsstudio.databinding.FragmentProBinding
import com.example.adssdk.advert.PurchasePrefs
import com.example.adssdk.billing.Billing6Listener
import com.example.adssdk.billing.Billing6Play
import com.google.firebase.ktx.BuildConfig


class ProFragment : Fragment(), Billing6Listener {
    private var binding: FragmentProBinding? = null


    private var productIds: ArrayList<String> = ArrayList()
    private var subscriptionIds: ArrayList<String> = ArrayList()
    private var billingClient: BillingClient? = null
    private var billing6Play: Billing6Play? = null
    private var logTag = "billingInAppClass"
    private var isSelected = "sub_weekly"
    private var isMonthly = false
    private var isYearly = false
    private var isWeekly = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProBinding.inflate(inflater, container, false)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()

                }
            })
        subscriptionIds.add("sub_weekly")//11.99
        subscriptionIds.add("sub_month")//5.99usd
        subscriptionIds.add("sub_year")//16.99

        productIds.add(if (BuildConfig.DEBUG) "android.test.purchased" else "ads_free")//
        billing6Play = Billing6Play(
            activity = requireActivity(),
            productIds = productIds,
            subscriptionIds = subscriptionIds,
            this
        )

        checkSubscription()
        agreementText()




        binding?.proButton?.setOnClickListener {
            Log.d(logTag, "trigger isSelected $isSelected")

            if (isSelected == "sub_year") {

                if (!isYearly) {
                    Log.d(logTag, "trigger sub_year")

                    billing6Play?.subscription(2)
                } else {

                    Toast.makeText(requireContext(), "Already Subscribed!", Toast.LENGTH_SHORT)
                        .show()
                }

            } else if (isSelected == "sub_month") {
                Log.d(logTag, "trigger sub_month")

                if (!isMonthly) {

                    billing6Play?.subscription(1)
                } else {
                    Toast.makeText(requireContext(), "Already Subscribed!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (isSelected == "sub_weekly") {
                Log.d(logTag, "trigger sub_weekly")

                if (!isWeekly) {

                    billing6Play?.subscription(0)
                } else {

                    Toast.makeText(requireContext(), "Already Subscribed!", Toast.LENGTH_SHORT)
                        .show()
                }
            } else if (isSelected == "sub_life") {
                Log.d(logTag, "trigger sub_life")

                if (!isWeekly) {

                    billing6Play?.oneTimeProduct()
                } else {

                    Toast.makeText(requireContext(), "Already Purchased!", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }

        binding?.cardMonthly?.setOnClickListener {
            Log.d("uiChange", "cardMonthly")

            isSelected = "sub_month"
            changeDesignNow(
                isSelected
            )
        }

        binding?.cardWeekly?.setOnClickListener {
            isSelected = "sub_weekly"
            changeDesignNow(
                isSelected
            )
        }

        binding?.cardYearly?.setOnClickListener {
            isSelected = "sub_year"
            changeDesignNow(
                isSelected
            )
        }

        binding?.cardLifetime?.setOnClickListener {
            isSelected = "sub_life"
            changeDesignNow(
                isSelected
            )
        }


        return binding?.root!!
    }


    override fun subList(subscriptionDetailsList: MutableList<ProductDetails>?) {
        Log.d(logTag, "$subscriptionDetailsList")

        try {
            if (!subscriptionDetailsList.isNullOrEmpty() && subscriptionDetailsList.size > 0) {

                Log.d(logTag, "subList: $subscriptionDetailsList")
                val product1 = subscriptionDetailsList[0]
                val product2 = subscriptionDetailsList[1]
                val product3 = subscriptionDetailsList[2]


                if (product1.subscriptionOfferDetails?.isNotEmpty() ?: return) {
                    product1.subscriptionOfferDetails?.forEach { details ->
                        details.pricingPhases.pricingPhaseList.forEach {
                            binding?.priceMonth?.text = it.formattedPrice
                        }
                    }
                }

                if (product2.subscriptionOfferDetails?.isNotEmpty() ?: return) {
                    product2.subscriptionOfferDetails?.forEach { details ->
                        details.pricingPhases.pricingPhaseList.forEach {
                            binding?.weekPrice?.text = it.formattedPrice

                        }
                    }
                }


                product3.subscriptionOfferDetails?.forEach { details ->
                    details.pricingPhases.pricingPhaseList.forEach {
                        binding?.priceYear?.text = it.formattedPrice
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } catch (e: ConcurrentModificationException) {
            e.printStackTrace()
        }
        super.subList(subscriptionDetailsList)
    }

    override fun purchasesList(productDetailsList: MutableList<ProductDetails>?) {
        Log.d(logTag, "purchasesList: $productDetailsList")
        productDetailsList?.forEach {
            val lifeTimePrice = it.oneTimePurchaseOfferDetails?.formattedPrice.toString()
            binding?.priceLifetime?.text = lifeTimePrice
        }
    }

    override fun purchaseOrSubDone() {
        Log.d(logTag, "trigger")
        super.purchaseOrSubDone()
        Log.e(logTag, "purchaseOrSubDone: down")
    }


    private fun agreementText() {
        val textViewAgreement = binding?.privacyLink
        val agreementText = getString(R.string.registration_agreement)

        val spannableStringBuilder = SpannableStringBuilder(agreementText)

        val termsClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                openUrl("https://unseenmessage.blogspot.com/2023/12/term-and-conditions.html")
            }
        }

        val privacyClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                openUrl("https://mobiledatatransferapp.blogspot.com/2023/12/privacy-policy.html")
            }
        }
        val manageClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                openUrl("https://play.google.com/store/account/subscriptions")
            }
        }

        spannableStringBuilder.setSpan(
            manageClickableSpan,
            agreementText.indexOf("Cancel or Restore"),
            agreementText.indexOf("Cancel or Restore") + "Cancel or Restore".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableStringBuilder.setSpan(
            termsClickableSpan,
            agreementText.indexOf("Privacy Policy"),
            agreementText.indexOf("Privacy Policy") + "Privacy Policy".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )


        spannableStringBuilder.setSpan(
            privacyClickableSpan,
            agreementText.indexOf("Terms of Services"),
            agreementText.indexOf("Terms of Services") + "Terms of Services".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        textViewAgreement?.text = spannableStringBuilder
        textViewAgreement?.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun changeDesignNow(type: String) {
        Log.d("uiChange", "$type")

        when (type) {
            "sub_year" -> {
                Log.d("uiChange", "sub_year")
                binding?.run {

                    weekPrice.setTextColor(resources.getColor(R.color.green_main))
                    priceYear.setTextColor(resources.getColor(R.color.white))
                    priceMonth.setTextColor(resources.getColor(R.color.green_main))
                    priceLifetime.setTextColor(resources.getColor(R.color.green_main))

                    tvWeek.setTextColor(resources.getColor(R.color.green_main))
                    tvYear.setTextColor(resources.getColor(R.color.white))
                    tvMonth.setTextColor(resources.getColor(R.color.green_main))
                    tvLifeTime.setTextColor(resources.getColor(R.color.green_main))

                    cardWeekly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardYearly.background =
                        resources.getDrawable(R.drawable.checking_update_background)
                    cardMonthly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardLifetime.background =
                        resources.getDrawable(R.drawable.white_rounded_background)


                }


            }

            "sub_month" -> {
                Log.d("uiChange", "sub_month")
                binding?.run {

                    weekPrice.setTextColor(resources.getColor(R.color.green_main))
                    priceYear.setTextColor(resources.getColor(R.color.green_main))
                    priceMonth.setTextColor(resources.getColor(R.color.white))
                    priceLifetime.setTextColor(resources.getColor(R.color.green_main))

                    tvWeek.setTextColor(resources.getColor(R.color.green_main))
                    tvYear.setTextColor(resources.getColor(R.color.green_main))
                    tvMonth.setTextColor(resources.getColor(R.color.white))
                    tvLifeTime.setTextColor(resources.getColor(R.color.green_main))

                    cardWeekly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardYearly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardMonthly.background =
                        resources.getDrawable(R.drawable.checking_update_background)
                    cardLifetime.background =
                        resources.getDrawable(R.drawable.white_rounded_background)


                }

            }

            "sub_weekly" -> {
                Log.d("uiChange", "sub_weekly")

                binding?.run {

                    weekPrice.setTextColor(resources.getColor(R.color.white))
                    priceYear.setTextColor(resources.getColor(R.color.green_main))
                    priceMonth.setTextColor(resources.getColor(R.color.green_main))
                    priceLifetime.setTextColor(resources.getColor(R.color.green_main))

                    tvWeek.setTextColor(resources.getColor(R.color.white))
                    tvYear.setTextColor(resources.getColor(R.color.green_main))
                    tvMonth.setTextColor(resources.getColor(R.color.green_main))
                    tvLifeTime.setTextColor(resources.getColor(R.color.green_main))

                    cardWeekly.background =
                        resources.getDrawable(R.drawable.checking_update_background)
                    cardYearly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardMonthly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardLifetime.background =
                        resources.getDrawable(R.drawable.white_rounded_background)


                }

            }


            else -> {
                Log.d("uiChange", "sub_life")

                binding?.run {

                    weekPrice.setTextColor(resources.getColor(R.color.green_main))
                    priceYear.setTextColor(resources.getColor(R.color.green_main))
                    priceMonth.setTextColor(resources.getColor(R.color.green_main))
                    priceLifetime.setTextColor(resources.getColor(R.color.white))

                    tvWeek.setTextColor(resources.getColor(R.color.green_main))
                    tvYear.setTextColor(resources.getColor(R.color.green_main))
                    tvMonth.setTextColor(resources.getColor(R.color.green_main))
                    tvLifeTime.setTextColor(resources.getColor(R.color.white))

                    cardWeekly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardYearly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardMonthly.background =
                        resources.getDrawable(R.drawable.white_rounded_background)
                    cardLifetime.background =
                        resources.getDrawable(R.drawable.checking_update_background)


                }

            }
        }

    }

    private fun checkSubscription() {
        billingClient = BillingClient.newBuilder(requireContext()).enablePendingPurchases()
            .setListener { billingResult: BillingResult?, list: List<Purchase?>? -> }.build()
        val finalBillingClient: BillingClient = billingClient ?: return
        billingClient?.startConnection(object : BillingClientStateListener {
            override fun onBillingServiceDisconnected() {}
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    finalBillingClient.queryPurchasesAsync(
                        QueryPurchasesParams.newBuilder()
                            .setProductType(BillingClient.ProductType.SUBS).build()
                    ) { billingResult1: BillingResult, list: List<Purchase> ->
                        if (billingResult1.responseCode == BillingClient.BillingResponseCode.OK) {
                            Log.d(logTag, list.size.toString() + " size")
                            if (list.isNotEmpty()) {
                                if (list.size == 3) {
                                    isMonthly = true
                                    isWeekly = true
                                    isMonthly = true
                                } else {
                                    for (purchase in list) {
                                        for (i in subscriptionIds.indices) {
                                            if (purchase.products[0] == "sub_month") {
                                                isWeekly = true
                                            } else if (purchase.products[0] == "sub_weekly") {
                                                isMonthly = true
                                            } else if (purchase.products[0] == "sub_year") {
                                                isYearly = true
                                            }
                                        }
                                    }
                                }
                                PurchasePrefs(requireContext())
                                    .putBoolean("inApp", true)
                            } else {
                                PurchasePrefs(requireContext())
                                    .putBoolean(
                                        "inApp", false
                                    ) // set 0 to de-activate premium feature
                            }
                        }
                    }
                }
            }
        })
    }


}