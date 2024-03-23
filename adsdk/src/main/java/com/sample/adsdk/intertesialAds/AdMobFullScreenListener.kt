package com.sample.adsdk.intertesialAds

/**
 * Created by
 *
 * @Author: Mahboob Khan ,
 * @Company: Integer Technologies ,
 * @Email: mahboobk522@gmail.com ,
 * on 11/5/2021 , Fri .
 */
interface AdMobFullScreenListener {
    fun fullScreenAdShow()
    fun fullScreenAdDismissed()
    fun fullScreenAdFailedToShow()
    fun fullScreenAdNotAvailable()
}