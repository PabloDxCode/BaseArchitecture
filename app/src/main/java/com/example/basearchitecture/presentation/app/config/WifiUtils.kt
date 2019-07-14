package com.example.basearchitecture.presentation.app.config

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.widget.Toast
import com.example.basearchitecture.data.config.ICheckConnection
import com.example.basearchitecture.presentation.app.BaseArchitectureApp
import java.util.concurrent.atomic.AtomicBoolean

/**
 * WifiUtils
 *
 * @param context context instance
 */
class WifiUtils(val context: Context) : ICheckConnection {

    /**
     * network info
     */
    private var networkInfo: NetworkInfo? = null

    /**
     * Init method
     */
    init {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        this.networkInfo = connectivityManager.activeNetworkInfo
    }

    /**
     * Method to save String preference
     *
     * @return boolean
     */
    override fun isWifiActive(): Boolean {
        val mng = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return mng.isWifiEnabled
    }

    /**
     * Method to know if wifi is connected
     *
     * @return boolean
     */
    override fun isConnected(): Boolean {
        val isConected = AtomicBoolean(isWifiActive())

        if (networkInfo != null && networkInfo!!.isConnected) {
            if (networkInfo!!.getType() == ConnectivityManager.TYPE_MOBILE) {
                isConected.set(true)
            }
            // Si hay conexión a Internet en este momento
        } else {
            return false
        }
        return isConected.get()
    }

}