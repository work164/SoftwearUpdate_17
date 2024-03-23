package com.app.update.softwareupdatekkappsstudio.ads

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import com.app.update.softwareupdatekkappsstudio.ads.Event

class ConnectivityStatus(
    private val connectivityManager: ConnectivityManager
) : LiveData<Event<Boolean>>() {

    constructor(application: Application) : this(application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(Event(true))
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(Event(false))
        }
    }

    override fun onActive() {
        super.onActive()
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}