package com.example.basearchitecture.ui.modules.splash.controller

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.Settings
import androidx.annotation.NonNull
import com.example.basearchitecture.R
import com.example.basearchitecture.ui.app.config.IPermissions
import com.example.basearchitecture.ui.app.config.PermissionsConfig
import com.example.basearchitecture.ui.modules.commons.BaseController
import com.example.basearchitecture.ui.modules.login.controller.LoginController
import com.example.basearchitecture.ui.utils.AlertDialogUtils
import com.example.basearchitecture.ui.utils.Constants
import com.example.basearchitecture.ui.utils.NavigationUtils

/**
 * SplashController
 */
class SplashController : BaseController() {

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
     * Permissions config
     */
    private var mIPermissions: IPermissions? = null

    /**
     * onCreate
     *
     * @param savedInstanceState bundle instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_controller)
        mIPermissions = PermissionsConfig(this, PermissionsConfig.Permissions.ALL_PERMISSIONS)
    }

    /**
     * onResume
     */
    override fun onResume() {
        super.onResume()
        if (mInitLaunched) {
            if (mIPermissions!!.hasPermissions()) {
                runHandler()
            } else {
                mIPermissions!!.askForPermits(Constants.REQUEST_CODE_PERMISSION)
            }
        }
    }

    /**
     * Method to init app
     */
    private fun runHandler() {
        mInitLaunched = false
        mRunnable = Runnable {
            NavigationUtils.Builder(this, LoginController::class.java)
                .setTransition(NavigationUtils.Transition.FADE)
                .start()
            finish()
        }
        mHandler.postDelayed(mRunnable, 1000)
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

    /**
     * Callback for the result from requesting permissions.
     * <p>
     * This method is invoked for every call on requestPermissions(java.lang.String[], int).
     * <p>
     * Note: It is possible that the permissions request interaction with the user is interrupted.
     * <p>
     * In this case you will receive empty permissions and results arrays which should be treated as a cancellation.
     *
     * @param requestCode  The request code passed in requestPermissions(java.lang.String[], int)
     * @param permissions  The requested permissions. Never null. This value must never be null.
     * @param grantResults The grant results for the corresponding permissions which is either
     *                     <p>
     *                     PackageManager.PERMISSION_GRANTED or PackageManager.PERMISSION_DENIED.
     *                     <p>
     *                     Never null. This value must never be null.
     */
    override fun onRequestPermissionsResult(requestCode: Int, @NonNull permissions: Array<String>, @NonNull grantResults: IntArray) {
        if (Constants.REQUEST_CODE_PERMISSION != requestCode) {
            return
        }

        if (mIPermissions!!.hasPermissions()) {
            runHandler()
        } else {
            mInitLaunched = false
            if (mIPermissions!!.shouldShowPermissionsAlert()) {
                mIPermissions!!.askForPermits(Constants.REQUEST_CODE_PERMISSION)
            } else {
                AlertDialogUtils.Builder(this@SplashController)
                    .setTitle(getString(R.string.text_alert))
                    .setMessage(getString(R.string.text_alert_message))
                    .setAccept(getString(R.string.text_alert_accept)) { launchSettingsIntent(requestCode) }
                    .show()
            }
        }
    }

    /**
     * Method to launch settings intent
     *
     * @param requestCode request code
     */
    private fun launchSettingsIntent(requestCode: Int) {
        val uri = Uri.fromParts("package", packageName, null)
        startActivityForResult(
            Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(uri),
            requestCode
        )
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode you started it with,
     * <p>
     * the resultCode it returned, and any additional data from it. The resultCode will be
     * <p>
     * RESULT_CANCELED if the activity explicitly returned that, didn't return any result, or
     * <p>
     * crashed during its operation.
     *
     * @param requestCode The integer request code originally supplied to startActivityForResult(),
     *                    <p>
     *                    allowing you to identify who this result came from.
     * @param resultCode  The integer result code returned by the child activity through its setResult().
     * @param data        An Intent, which can return result data to the caller (various data can
     *                    <p>
     *                    be attached to Intent "extras").
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mInitLaunched = true
    }

}
