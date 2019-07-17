package com.example.basearchitecture.ui.app.config

import android.app.Activity

/**
 * IPermissions
 *
 * Interface to validate permissions
 */
interface IPermissions {

    /**
     * Method to init permissions class
     *
     * @param activity activity instance
     */
    fun init(activity: Activity)

    /**
     * Method to set permissions
     *
     * @param permissions list of permissions
     */
    fun setPermissions(permissions: Array<String>)

    /**
     * Method to validate if app has permissions
     *
     * @return boolean
     */
    fun hasPermissions(): Boolean

    /**
     * Method to launch request permissions
     *
     * @param requestCode request code to manage response
     */
    fun requestPermissions(requestCode: Int)

    /**
     * Method to show permissions alert
     *
     * @return boolean
     */
    fun shouldShowPermissionsAlert(): Boolean

}