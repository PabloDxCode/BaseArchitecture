package com.example.basearchitecture.presentation.modules.login.presenters

import android.content.Context
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.presentation.modules.login.contracts.LoginContracts
import com.example.basearchitecture.presentation.modules.login.presenters.listeners.LoginPresenterListener
import com.example.basearchitecture.presentation.utils.Utils
import javax.inject.Inject

/**
 * LoginPresenter
 *
 * @param loginInteractor login interactor
 * @param context context instance
 */
class LoginPresenter @Inject constructor(val loginInteractor: LoginContracts.LoginInteractor, val context: Context) :
    LoginContracts.LoginPresenter, LoginPresenterListener {

    /**
     * Login view
     */
    private var mView: LoginContracts.LoginView? = null

    /**
     * Method to init presenter
     *
     * @param view login view interface
     */
    override fun init(view: LoginContracts.LoginView) {
        this.mView = view
    }

    /**
     * Method to do login
     *
     * @param email email param
     * @param password password param
     */
    override fun doLogin(email: String, password: String) {
        if (mView != null) {
            mView!!.showProgress()
            loginInteractor.init(email, password, this)
            loginInteractor.doLogin()
        }
    }

    /**
     * Success method
     */
    override fun onSuccess() {
        if (mView != null) {
            mView!!.hideProgress()
            mView!!.onSuccess()
        }
    }

    /**
     * Error for inactive user
     *
     * @param response common response
     */
    override fun onErrorInactiveUser(response: ICommonError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

    /**
     * Error for non-exist user
     *
     * @param response common response
     */
    override fun onErrorNonExistUser(response: ICommonError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

    /**
     * Error response
     *
     * @param response common response
     */
    override fun onRequestError(response: ICommonError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

    /**
     * Error for inactive session
     *
     * @param response common response
     */
    override fun onErrorInactiveSession(response: ICommonError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

}