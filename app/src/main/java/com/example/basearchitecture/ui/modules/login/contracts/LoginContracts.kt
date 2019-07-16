package com.example.basearchitecture.ui.modules.login.contracts

import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.ui.modules.login.presenters.listeners.LoginPresenterListener

/**
 * LoginContracts
 */
object LoginContracts {

    /**
     * LoginView
     */
    interface LoginView {

        /**
         * Method to show progress
         */
        fun showProgress()

        /**
         * Method to hide progress
         */
        fun hideProgress()

        /**
         * Method for success response
         */
        fun onSuccess()

        /**
         * Method to show email error
         *
         * @param error error message
         */
        fun onEmailError(error:String)

        /**
         * Method to show password error
         *
         * @param error error message
         */
        fun onPasswordError(error:String)

        /**
         * Method for error response
         *
         * @param response response error
         */
        fun onError(response: Any)

    }

    /**
     * LoginPresenter
     */
    interface LoginPresenter {

        /**
         * Method to init presenter
         *
         * @param view login view interface
         */
        fun init(view: LoginView)

        /**
         * Method to do login
         *
         * @param email email param
         * @param password password param
         */
        fun doLogin(email: String, password: String)

    }

    /**
     * LoginInteractor
     */
    interface LoginInteractor {

        /**
         * Method to init interactor
         *
         * @param email email param
         * @param password password param
         * @param loginPresenterListener login presenter listener
         */
        fun init(email: String, password: String, loginPresenterListener: LoginPresenterListener)

        /**
         * Method to do login
         */
        fun doLogin()

        /**
         * Method to generate session id
         */
        fun generateSessionId()

        /**
         * Method to login slod
         */
        fun loginSlod()

        /**
         * Method to login status
         */
        fun loginStatus()

        /**
         * Method to get user info
         *
         * @param sessionId session id param
         */
        fun getUserInfo(sessionId: String)

        /**
         * Method to save user info
         *
         * @param userInfo user info
         */
        fun saveUserInfo(userInfo: User)

        /**
         * Method to update login date
         */
        fun updateLoginDate()

    }

}