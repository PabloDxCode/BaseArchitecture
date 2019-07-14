package com.example.basearchitecture.presentation.modules.login.controllers

import android.os.Bundle
import android.widget.Toast
import com.example.basearchitecture.R
import com.example.basearchitecture.presentation.modules.commons.BaseController
import com.example.basearchitecture.presentation.modules.login.contracts.LoginContracts
import com.example.basearchitecture.presentation.modules.main.controllers.MainController
import com.example.basearchitecture.presentation.utils.NavigationUtils
import com.example.basearchitecture.presentation.utils.Utils
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

    /**
     * Method for error response
     *
     * @param response response error
     */
    override fun onError(response: Any) {
        runOnUiThread {
            Toast.makeText(this, response.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}
