package com.example.basearchitecture.ui.modules.login.contracts

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
         * Success method to get email
         */
        fun onSuccessGettingEmail(email: String)

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

        /**
         * Method to get email saved
         */
        fun getEmail()

    }

    /**
     * LoginInteractor
     */
    interface LoginInteractor {

        /**
         * Method to init interactor
         *
         * @param loginPresenterListener login presenter listener
         */
        fun init(loginPresenterListener: LoginPresenterListener)

        /**
         * Method to do login
         *
         * @param email email param
         * @param password password param
         */
        fun doLogin(email: String, password: String)

        /**
         * Method to get email from preferences
         */
        fun getEmailFromPreferences()

    }

}