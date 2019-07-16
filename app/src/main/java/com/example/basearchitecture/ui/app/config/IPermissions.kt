package com.example.basearchitecture.ui.app.config

interface IPermissions {

    fun hasPermissions(): Boolean

    fun askForPermits(requestCode: Int)

    fun shouldShowPermissionsAlert(): Boolean

}