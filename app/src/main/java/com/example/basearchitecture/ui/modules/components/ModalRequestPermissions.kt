package com.example.basearchitecture.ui.modules.components

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.annotation.NonNull
import com.example.basearchitecture.R
import com.example.basearchitecture.ui.app.config.PermissionsConfig
import com.example.basearchitecture.ui.modules.commons.BaseController
import com.example.basearchitecture.ui.modules.splash.contracts.SplashContracts
import com.example.basearchitecture.ui.utils.AlertDialogUtils
import com.example.basearchitecture.ui.utils.Constants
import javax.inject.Inject

/**
 * ModalRequestPermissions
 *
 * extends splash view
 */
class ModalRequestPermissions : BaseController(), SplashContracts.SplashView {

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
        setContentView(R.layout.activity_modal_request_permissions)
        mSplashPresenter.init(this, this, PermissionsConfig.Permissions.ALL_PERMISSIONS)
        mSplashPresenter.requestPermissions(Constants.REQUEST_CODE_PERMISSION)
    }

    /**
     * Method to go next class
     */
    override fun goToNextClass() {
        onBackPressed()
    }

    /**
     * Method to request permissions
     */
    override fun requestPermissions() {
        mSplashPresenter.requestPermissions(Constants.REQUEST_CODE_PERMISSION)
    }

    /**
     * Method to verify if application can show permissions alert
     *
     * @param showAlertPermissions flag to validate if app can show permission alert
     */
    override fun canShowPermissionsAlert(showAlertPermissions: Boolean) {
        if (showAlertPermissions) {
            mSplashPresenter.hasPermissions()
        } else {
            AlertDialogUtils.Builder(this@ModalRequestPermissions)
                .setTitle(getString(R.string.text_alert))
                .setMessage(getString(R.string.text_alert_message))
                .setAccept(getString(R.string.text_alert_accept)) { launchSettingsIntent(Constants.REQUEST_CODE_PERMISSION) }
                .show()
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
        mSplashPresenter.shouldShowAlertPermissions()
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
        mSplashPresenter.hasPermissions()
    }

}
