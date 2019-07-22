package com.example.basearchitecture.ui.modules.login.presenters

import android.content.Context
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.ui.modules.login.contracts.LoginContracts
import com.example.basearchitecture.ui.modules.login.presenters.listeners.LoginPresenterListener
import com.example.basearchitecture.ui.utils.Utils
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
            loginInteractor.init(this)
            loginInteractor.doLogin(email, password)
        }
    }

    /**
     * Method to get email saved
     */
    override fun getEmail() {
        if (mView != null) {
            loginInteractor.init(this)
            loginInteractor.getEmailFromPreferences()
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
     * Success method to get email
     *
     * @param email email saved
     */
    override fun onSuccessGettingEmail(email: String) {
        if (mView != null) {
            mView!!.onSuccessGettingEmail(email)
        }
    }

    /**
     * Method to return view error
     *
     * @param responseErrorType response error type list
     */
    override fun onViewError(responseErrorType: ArrayList<ResponseErrorType>) {
        if (mView != null) {
            mView!!.hideProgress()
            for (item in responseErrorType) {
                val message = Utils.getErrorFromStrings(item.toString(), context)
                manageFieldResponse(message, item)
            }
        }
    }

    private fun manageFieldResponse(message: String, responseErrorType: ResponseErrorType){
        when(responseErrorType){
            ResponseErrorType.EMAIL->{ mView!!.onEmailError(message) }
            ResponseErrorType.PASSWORD->{ mView!!.onPasswordError(message) }
        }
    }

    /**
     * Error for pending user
     */
    override fun onErrorPendingUser() {
        if (mView != null) {
            mView!!.hideProgress()
        }
    }

    /**
     * Error for inactive user
     */
    override fun onErrorInactiveUser() {
        if (mView != null) {
            mView!!.hideProgress()
        }
    }

    /**
     * Error for non-exist user
     *
     * @param response common response
     */
    override fun onErrorNonExistUser(response: IAppError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

    /**
     * Error for locked user
     *
     * @param response common response
     */
    override fun onErrorLockedUser(response: IAppError) {
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
    override fun onRequestError(response: IAppError) {
        if (mView != null) {
            mView!!.hideProgress()
            val error = response as AppError
            mView!!.onError(Utils.getErrorFromStrings(error.message!!, context))
        }
    }

}