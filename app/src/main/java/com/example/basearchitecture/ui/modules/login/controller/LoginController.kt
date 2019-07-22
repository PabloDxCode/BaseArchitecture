package com.example.basearchitecture.ui.modules.login.controller

import android.os.Bundle
import android.widget.Toast
import com.example.basearchitecture.R
import com.example.basearchitecture.ui.modules.commons.BaseController
import com.example.basearchitecture.ui.modules.login.contracts.LoginContracts
import com.example.basearchitecture.ui.modules.main.controllers.MainController
import com.example.basearchitecture.ui.utils.NavigationUtils
import com.example.basearchitecture.ui.utils.Utils
import kotlinx.android.synthetic.main.activity_login_controller.*
import javax.inject.Inject

/**
 * LoginController
 */
class LoginController : BaseController(), LoginContracts.LoginView {

    /**
     * Login presenter instance
     */
    @Inject
    lateinit var mPresenter: LoginContracts.LoginPresenter

    /**
     * onCreate
     *
     * @param savedInstanceState bundle instance
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_controller)
        btLogin.setOnClickListener {
            Utils.closeKeyboard(this)
            mPresenter.doLogin(etEmail.text.toString(), etPassword.text.toString())
        }
        mPresenter.init(this)
        mPresenter.getEmail()
    }

    /**
     * Method to show progress
     */
    override fun showProgress() {
        showHideProgress(true)
    }

    /**
     * Method to hide progress
     */
    override fun hideProgress() {
        showHideProgress(false)
    }

    /**
     * Method for success response
     */
    override fun onSuccess() {
        NavigationUtils.Builder(this, MainController::class.java).start()
        finish()
    }

    override fun onSuccessGettingEmail(email: String) {
        etEmail.setText(email)
    }

    /**
     * Method to show email error
     *
     * @param error error message
     */
    override fun onEmailError(error: String) {
        etEmail.error = error
    }

    /**
     * Method to show password error
     *
     * @param error error message
     */
    override fun onPasswordError(error: String) {
        etPassword.error = error
    }

    /**
     * Method for error response
     *
     * @param response response error
     */
    override fun onError(response: Any) {
        Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
    }

}
