package com.example.basearchitecture.ui.modules.login.interactors

import com.example.basearchitecture.domain.businesslogiccase.login.factory.ILoginFactory
import com.example.basearchitecture.ui.modules.login.contracts.LoginContracts
import com.example.basearchitecture.ui.modules.login.presenters.listeners.LoginPresenterListener
import javax.inject.Inject

/**
 * LoginInteractor
 *
 * @param loginFactory login factory instance
 */
class LoginInteractor @Inject constructor(val loginFactory: ILoginFactory) : LoginContracts.LoginInteractor {

    /**
     * Login presenter listener
     */
    private var mLoginPresenterListener: LoginPresenterListener? = null

    /**
     * Method to init interactor
     *
     * @param loginPresenterListener login presenter listener
     */
    override fun init(loginPresenterListener: LoginPresenterListener) {
        this.mLoginPresenterListener = loginPresenterListener
    }

    /**
     * Method to do login
     *
     * @param email email param
     * @param password password param
     */
    override fun doLogin(email: String, password: String) {
        loginFactory.init(email, password)
            .onSuccess { mLoginPresenterListener!!.onSuccess() }
            .onErrorPendingStatusUser { mLoginPresenterListener!!.onErrorPendingUser() }
            .onErrorInactiveUser { mLoginPresenterListener!!.onErrorInactiveUser() }
            .onErrorNonExistUser { mLoginPresenterListener!!.onErrorNonExistUser(it) }
            .onErrorLockedUser { mLoginPresenterListener!!.onErrorLockedUser(it) }
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(it) }
            .doLogin()
    }

}