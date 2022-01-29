package com.ugurbuga.followtvmovie.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity

class NetworkUtil :
    ConnectivityManager.NetworkCallback() {

    private lateinit var connectivityManager: ConnectivityManager
    var onChange: ((isConnect: Boolean) -> Unit)? = null

    fun checkNetworkConnectivity(context: Context) {
        connectivityManager =
            context.applicationContext.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkRequest = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(
            networkRequest, this
        )
    }

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        onChange?.invoke(true)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
        onChange?.invoke(false)
    }

    fun removeRegister() {
        connectivityManager.unregisterNetworkCallback(this)
    }
}
