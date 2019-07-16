package com.example.basearchitecture.ui.app.config

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.basearchitecture.data.config.ICheckConnection

/**
 * WifiConfig
 *
 * @param context context instance
 */
class WifiConfig(val context: Context) : ICheckConnection {

    /**
     * network info
     */
    private var mConnectivityManager: ConnectivityManager? = null

    /**
     * Init method
     */
    init {
        mConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    /**
     * Method to know if wifi is connected
     *
     * @return boolean
     */
    override fun isConnected(): Boolean {
        if (mConnectivityManager == null) {
            return false
        } else {
            if (Build.VERSION.SDK_INT < 23) {
                return validateNetworkInfo()
            } else {
                return validateNetwork()
            }
        }
    }

    /**
     * Method to validate network info
     *
     * @return boolean
     */
    private fun validateNetworkInfo(): Boolean {
        val networkInfo = mConnectivityManager!!.activeNetworkInfo
        if (networkInfo == null) {
            return false
        } else {
            return networkInfo.type == ConnectivityManager.TYPE_WIFI || networkInfo.type == ConnectivityManager.TYPE_MOBILE
        }
    }

    /**
     * Method to validate network
     *
     * @return boolean
     */
    @RequiresApi(Build.VERSION_CODES.M)
    private fun validateNetwork(): Boolean {
        val network = mConnectivityManager!!.activeNetwork

        if (network == null) {
            return false
        } else {
            val networkCapabilities = mConnectivityManager!!.getNetworkCapabilities(network)

            return (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
        }
    }

}