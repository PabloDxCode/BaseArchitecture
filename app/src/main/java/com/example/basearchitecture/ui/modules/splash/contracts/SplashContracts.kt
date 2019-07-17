package com.example.basearchitecture.ui.modules.splash.contracts

import android.app.Activity

/**
 * SplashContracts
 *
 * Object with splash contracts
 */
object SplashContracts {

    /**
     * Interface contract for splash view
     */
    interface SplashView {

        /**
         * Method to go next class
         */
        fun goToNextClass()

        /**
         * Method to request permissions
         */
        fun requestPermissions()

        /**
         * Method to verify if application can show permissions alert
         *
         * @param showAlertPermissions flag to validate if app can show permission alert
         */
        fun canShowPermissionsAlert(showAlertPermissions: Boolean)

    }

    /**
     * Interface contract for splash presenter
     */
    interface SplashPresenter {

        /**
         * Method to init splash presenter
         *
         * @param view splash view
         * @param activity activity instance
         * @param permissions list of permissions
         */
        fun init(view: SplashView, activity: Activity, permissions: Array<String>)

        /**
         * Method to know if app has permissions
         *
         * @param requestCode request code to manage response
         */
        fun hasPermissions()

        /**
         * Method to launch request permissions
         *
         * @param requestCode request code to manage response
         */
        fun requestPermissions(requestCode: Int)

        /**
         * Method to show alert permissions
         */
        fun shouldShowAlertPermissions()

    }

}