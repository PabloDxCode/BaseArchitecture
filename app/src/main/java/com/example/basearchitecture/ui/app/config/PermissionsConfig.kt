package com.example.basearchitecture.ui.app.config

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissionsConfig(val activity: Activity, val permissions: Array<String>) : IPermissions {

    object Permissions {

        const val INTERNET = Manifest.permission.INTERNET

        const val NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE

        const val WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE

        val ALL_PERMISSIONS = arrayOf(INTERNET, NETWORK_STATE, WIFI_STATE)
    }

    override fun hasPermissions(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    override fun askForPermits(requestCode: Int) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode)
    }

    override fun shouldShowPermissionsAlert(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                    return true
                }
            }
        }
        return false
    }

}