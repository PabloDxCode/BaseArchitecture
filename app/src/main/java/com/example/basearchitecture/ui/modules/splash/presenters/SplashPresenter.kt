package com.example.basearchitecture.ui.modules.splash.presenters

import android.app.Activity
import com.example.basearchitecture.ui.app.config.IPermissions
import com.example.basearchitecture.ui.modules.splash.contracts.SplashContracts
import javax.inject.Inject

/**
 * SplashPresenter
 *
 * @param iPermissions interface to request permissions
 */
class SplashPresenter @Inject constructor(val iPermissions: IPermissions): SplashContracts.SplashPresenter {

    /**
     * Interface contract view for splash controller
     */
    private var mView: SplashContracts.SplashView?= null

    /**
     * Method to init splash presenter
     *
     * @param view splash view interface instance
     * @param activity activity instance
     * @param permissions list of permissions
     */
    override fun init(view: SplashContracts.SplashView, activity: Activity, permissions: Array<String>) {
        this.mView = view
        iPermissions.init(activity)
        iPermissions.setPermissions(permissions)
    }

    /**
     * Method to know if app has permissions
     */
    override fun hasPermissions() {
        if(this.mView != null) {
            if (iPermissions.hasPermissions()) {
                mView!!.goToNextClass()
            } else {
                mView!!.requestPermissions()
            }
        }
    }

    /**
     * Method to launch request permissions
     *
     * @param requestCode request code to manage response
     */
    override fun requestPermissions(requestCode: Int) {
        iPermissions.requestPermissions(requestCode)
    }

    /**
     * Method to show alert permissions
     */
    override fun shouldShowAlertPermissions() {
        if(this.mView != null) {
            if (iPermissions.hasPermissions()) {
                mView!!.goToNextClass()
            } else {
                mView!!.canShowPermissionsAlert(iPermissions.shouldShowPermissionsAlert())
            }
        }
    }

}