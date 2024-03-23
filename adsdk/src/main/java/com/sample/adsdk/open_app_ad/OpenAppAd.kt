package com.sample.adsdk.open_app_ad

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ProcessLifecycleOwner
import com.sample.adsdk.billing.PurchasePrefs
import com.sample.adsdk.constants.Constant
import com.sample.adsdk.constants.Constant.appOpenAd
import com.sample.adsdk.constants.Constant.isShowingOpenAppAd
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.material.snackbar.Snackbar
import com.sample.adsdk.R
import java.util.*

class OpenAppAd(
    private val application: Application,
    /* private val openAdIdH: String,
     private val openAdIdM: String,*/
    private val openAdIdL: String,
    buildType: Boolean
) :
    Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private var currentActivity: Activity? = null
    private var loadCallback: AppOpenAd.AppOpenAdLoadCallback? = null
    private var loadTime: Long = 0
    private val LOG_TAG = "OpenAppAd"
//    var dialog: Dialog? = null

    init {
        application.registerActivityLifecycleCallbacks(this)
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        val regex = Regex(Constant.regexPattern)
        // val isValid = regex.matches(openAdIdH)

        /*    if (!isValid) {
                throw IllegalArgumentException("Your Ad Id is not correct: Please add in valid pattern e.g: ca-app-pub-3940256099942544/6300978111")
            }
            when {
                Constant.isDebug() && buildType -> {
                    //TestIds
                    if (!Constant.getTestIds().contains(openAdIdH)) {
                        throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                    }
                }

                !Constant.isDebug() && buildType -> {
                    //TestIds
                    if (!Constant.getTestIds().contains(openAdIdH)) {
                        throw IllegalArgumentException("Please set test ids in app dev portion in gradle")
                    }
                }

                Constant.isDebug() && !buildType -> {
                    if (Constant.getTestIds().contains(openAdIdH)) {
                        throw IllegalArgumentException("Please add production Ad id in release version $openAdIdH")
                    }
                }

                !Constant.isDebug() && !buildType -> {
                    if (Constant.getTestIds().contains(openAdIdH)) {
                        throw IllegalArgumentException("Please add production Ad id in release version")
                    }
                }
            }*/

    }

    /*  fun loadAdHigh() {
          if (remoteValueH && Constant.isNetworkAvailable(application.applicationContext) &&
              !PurchasePrefs(application.applicationContext).getBoolean("inApp")
          ) {
              loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
                  override fun onAdLoaded(appOpenAd: AppOpenAd) {
                      Constant.appOpenAd = appOpenAd
                      loadTime = Date().time
                      super.onAdLoaded(appOpenAd)
                      Log.d(LOG_TAG, "onAdLoaded: open all screen")
                  }

                  override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                      super.onAdFailedToLoad(loadAdError)
                      Log.d(
                          LOG_TAG,
                          "onAdFailedToLoad: open all screen loadAdHigh ${loadAdError.code}"
                      )
                      loadAdMed()
                  }
              }
              val request = adRequest
              AppOpenAd.load(
                  application.applicationContext,
                  openAdIdH,
                  request,
                  loadCallback ?: return
              )
          } else {
              if (Constant.isDebug()) {
                  currentActivity?.window?.decorView?.rootView?.let {
                      try {
                          Snackbar.make(
                              it,
                              "There is no internet connection available or app open ads remote value is false",
                              Snackbar.LENGTH_LONG
                          ).show()
                      } catch (e: Exception) {
                          e.printStackTrace()
                      }
                  }
              }
              loadAdMed()
          }
      }

      fun loadAdMed() {
          Log.d(LOG_TAG, "onAdLoaded: open function call openAdIdM $remoteValueM")

          if (remoteValueM && Constant.isNetworkAvailable(application.applicationContext)
              && !PurchasePrefs(application.applicationContext).getBoolean("inApp")
          ) {
              Log.d(LOG_TAG, "onAdLoaded: open function callback openAdIdM")

              loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
                  override fun onAdLoaded(appOpenAd: AppOpenAd) {
                      Constant.appOpenAd = appOpenAd
                      loadTime = Date().time
                      super.onAdLoaded(appOpenAd)
                      Log.d(LOG_TAG, "onAdLoaded: open all screen")
                  }

                  override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                      super.onAdFailedToLoad(loadAdError)
                      loadAdLow()
                      Log.d(
                          LOG_TAG,
                          "onAdFailedToLoad: open all screen loadAdMed ${loadAdError.code}"
                      )
                  }
              }
              Log.d(LOG_TAG, "onAdLoaded: open recall openAdIdM")

              val request = adRequest
              AppOpenAd.load(
                  application.applicationContext,
                  openAdIdM,
                  request,
                  loadCallback ?: return
              )
              Log.d(LOG_TAG, "onAdLoaded: open all openAdIdM")

          } else {
              Log.d(LOG_TAG, "onAdLoaded: open all else  openAdIdM")
              loadAdLow()
              if (Constant.isDebug()) {
                  currentActivity?.window?.decorView?.rootView?.let {
                      try {
                          Snackbar.make(
                              it,
                              "There is no internet connection available or app open ads remote value is false",
                              Snackbar.LENGTH_LONG
                          ).show()
                      } catch (e: Exception) {
                          e.printStackTrace()
                      }
                  }
              }
          }
      }*/

    fun loadAdLow() {
        if (remoteValueL && Constant.isNetworkAvailable(application.applicationContext) &&
            !PurchasePrefs(application.applicationContext).getBoolean("inApp")
        ) {
            if (Constant.isOpenAppEnabled) {
                loadCallback = object : AppOpenAd.AppOpenAdLoadCallback() {
                    override fun onAdLoaded(appOpenAd: AppOpenAd) {
                        Constant.appOpenAd = appOpenAd
                        loadTime = Date().time
                        super.onAdLoaded(appOpenAd)
                        Log.d(LOG_TAG, "onAdLoaded: open all screen")
                    }

                    override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                        super.onAdFailedToLoad(loadAdError)
                        Log.d(
                            LOG_TAG,
                            "onAdFailedToLoad: open all screen loadAdLow ${loadAdError.code}"
                        )
                    }
                }
                val request = adRequest
                AppOpenAd.load(
                    application.applicationContext,
                    openAdIdL,
                    request,
                    loadCallback ?: return
                )
            }
        } else {
            if (Constant.isDebug()) {
                currentActivity?.window?.decorView?.rootView?.let {
                    try {
                        Snackbar.make(
                            it,
                            "There is no internet connection available or app open ads remote value is false",
                            Snackbar.LENGTH_LONG
                        ).show()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()

    private fun wasLoadTimeLessThanNHoursAgo(numHours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour: Long = 3600000
        return dateDifference < numMilliSecondsPerHour * numHours
    }

    private fun showAdIfAvailable() {
        // Only show ad if there is not already an app open ad currently showing
        // and an ad is available.
        Log.d(LOG_TAG, "Can $isShowingOpenAppAd.   $isAdAvailable   ${Constant.isShowingInterAd}")

        if (!isShowingOpenAppAd && isAdAvailable && !Constant.isShowingInterAd) {

            Log.d(LOG_TAG, "Will show ad.open all screen 11 $remoteValueH")

            onShowDialog(currentActivity ?: return)
            Log.d(LOG_TAG, "Will show ad.open all screen 444")

            if (remoteValueH || remoteValueM || remoteValueL) {
                Log.d(LOG_TAG, "Will show ad.open all screen gggg")

                Log.d(LOG_TAG, "Will show ad.open all screen")
                Log.d(LOG_TAG, "remoteValueH $remoteValueH")
                val fullScreenContentCallback: FullScreenContentCallback =
                    object : FullScreenContentCallback() {
                        override fun onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
//                            if (dialog?.isShowing == true) {
//                                dialog?.dismiss()
//                            }
                            Log.d(LOG_TAG, "onAdDismissedFullScreenContent: open all screen")
                            appOpenAd = null
                            isShowingOpenAppAd = false
                            // loadAdHigh()
                        }

                        override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                            Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: open all screen")
//                            if (dialog?.isShowing == true) {
//                                dialog?.dismiss()
//                            }
                        }

                        override fun onAdShowedFullScreenContent() {
                            Log.d(LOG_TAG, "onAdShowedFullScreenContent: open all screen")
                            isShowingOpenAppAd = true
                        }
                    }
                // Splash or other class where you not want to show this Ad
                val cr = currentActivity?.javaClass.toString()

                Log.d(LOG_TAG, " cr   Show  ${Constant.isOpenAppEnabled}")
                if (Constant.isOpenAppEnabled) {

//                    onShowDialog(currentActivity ?: return)
//                    dialog?.show()
                    appOpenAd?.show(currentActivity ?: return)
                } else {
                    Log.d(LOG_TAG, " cr   elseeee")

                }
                appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
                Log.d(LOG_TAG, " cr   $cr")
            }
        } else {
            Log.d(LOG_TAG, "Can not show ad.")
            loadAdLow()
        }

    }

    private val isAdAvailable: Boolean
        get() = appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        showAdIfAvailable()
    }

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
        currentActivity = p0

    }

    override fun onActivityStarted(p0: Activity) {
        currentActivity = p0
    }

    override fun onActivityResumed(p0: Activity) {
        currentActivity = p0
    }

    override fun onActivityPaused(p0: Activity) {

    }

    override fun onActivityStopped(p0: Activity) {

    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {

    }

    override fun onActivityDestroyed(p0: Activity) {
        currentActivity = null
    }

    private fun onShowDialog(context: Context) {
//        dialog = Dialog(context, R.style.full_screen_dialog)
//        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        dialog?.setCancelable(true)
//        dialog?.setContentView(R.layout.blank_dialog)
//        dialog?.window?.setLayout(
//            WindowManager.LayoutParams.MATCH_PARENT,
//            WindowManager.LayoutParams.MATCH_PARENT
//        )
    }

    companion object {
        var remoteValueH = false
        var remoteValueM = false
        var remoteValueL = true
    }
}