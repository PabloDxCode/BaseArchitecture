package com.example.basearchitecture.ui.app.config

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

/**
 * Permissions implementation
 *
 * extends to IPermissions
 */
class PermissionsConfig : IPermissions {

    /**
     * Object permissions
     */
    object Permissions {

        /**
         * Constant for internet permission
         */
        private const val INTERNET = Manifest.permission.INTERNET
        /**
         * Constant for network state permission
         */
        private const val NETWORK_STATE = Manifest.permission.ACCESS_NETWORK_STATE
        /**
         * Constant for wifi state permission
         */
        private const val WIFI_STATE = Manifest.permission.ACCESS_WIFI_STATE
        /**
         * Constant for read external storage permission
         */
        private const val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
        /**
         * Constant for all permission
         */
        val ALL_PERMISSIONS = arrayOf(INTERNET, NETWORK_STATE, WIFI_STATE, READ_EXTERNAL_STORAGE)
    }

    /**
     * List of permissions
     */
    private var mPermissions: Array<String>? = null
    /**
     * Activity object
     */
    private var mActivity: Activity? = null

    /**
     * Method to init permissions class
     *
     * @param activity activity instance
     */
    override fun init(activity: Activity) {
        this.mActivity = activity
    }

    /**
     * Method to set permissions
     *
     * @param permissions list of permissions
     */
    override fun setPermissions(permissions: Array<String>) {
        this.mPermissions = permissions
    }

    /**
     * Method to validate if app has permissions
     *
     * @return boolean
     */
    override fun hasPermissions(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in mPermissions!!) {
                if (ActivityCompat.checkSelfPermission(mActivity!!, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false
                }
            }
        }
        return true
    }

    /**
     * Method to launch request permissions
     *
     * @param requestCode request code to manage response
     */
    override fun requestPermissions(requestCode: Int) {
        ActivityCompat.requestPermissions(mActivity!!, mPermissions!!, requestCode)
    }

    /**
     * Method to show permissions alert
     *
     * @return boolean
     */
    override fun shouldShowPermissionsAlert(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in mPermissions!!) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity!!, permission)) {
                    return true
                }
            }
        }
        return false
    }

}