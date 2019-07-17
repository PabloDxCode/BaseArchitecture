package com.example.basearchitecture.ui.modules.splash.controller

import android.os.Bundle
import android.os.Handler
import com.example.basearchitecture.R
import com.example.basearchitecture.ui.app.config.PermissionsConfig
import com.example.basearchitecture.ui.modules.commons.BaseController
import com.example.basearchitecture.ui.modules.components.ModalRequestPermissions
import com.example.basearchitecture.ui.modules.login.controller.LoginController
import com.example.basearchitecture.ui.modules.splash.contracts.SplashContracts
import com.example.basearchitecture.ui.utils.NavigationUtils
import javax.inject.Inject

/**
 * SplashController
 *
 * extends splash view
 */
class SplashController : BaseController(), SplashContracts.SplashView {

    /**
     * Handler object
     */
    private val mHandler: Handler = Handler()
    /**
     * Runnable object
     */
    private var mRunnable: Runnable? = null
    /**
     * Boolean to know if init method was launched
     */
    private var mInitLaunched: Boolean = true
    /**
     * Splash presenter instance
     */
    @Inject
    lateinit var mSplashPresenter: SplashContracts.SplashPresenter

    /**
     * onCreate
     *
     * @param savedInstanceState bundle instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_controller)
        mSplashPresenter.init(this, this, PermissionsConfig.Permissions.ALL_PERMISSIONS)
    }

    /**
     * onResume
     */
    override fun onResume() {
        super.onResume()
        mSplashPresenter.hasPermissions()
    }

    /**
     * Method to go next class
     */
    override fun goToNextClass() {
        mRunnable = Runnable {
            NavigationUtils.Builder(this, LoginController::class.java)
                .setTransition(NavigationUtils.Transition.FADE)
                .start()
            finish()
        }
        mHandler.postDelayed(mRunnable, 1000)
    }

    /**
     * Method to request permissions
     */
    override fun requestPermissions() {
        NavigationUtils.Builder(this, ModalRequestPermissions::class.java).start()
    }

    /**
     * Method to verify if application can show permissions alert
     *
     * @param showAlertPermissions flag to validate if app can show permission alert
     */
    override fun canShowPermissionsAlert(showAlertPermissions: Boolean) {
        //Empty method
    }

    /**
     * onPause
     */
    override fun onPause() {
        super.onPause()
        if (mRunnable != null) {
            mHandler.removeCallbacks(mRunnable)
            mInitLaunched = true
        }
    }

    /**
     * onBackPressed
     */
    override fun onBackPressed() {
        //Empty method
    }

}
