package com.example.basearchitecture.ui.modules.login.presenters.listeners

import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType

/**
 * LoginPresenterListener
 */
interface LoginPresenterListener {

    /**
     * Success method
     */
    fun onSuccess()

    /**
     * Success method to get email
     *
     * @param email email saved
     */
    fun onSuccessGettingEmail(email: String)

    /**
     * Method to return view error
     *
     * @param responseErrorType response error type list
     */
    fun onViewError(responseErrorType: ArrayList<ResponseErrorType>)

    /**
     * Error for pending user
     */
    fun onErrorPendingUser()

    /**
     * Error for inactive user
     */
    fun onErrorInactiveUser()

    /**
     * Error for non-exist user
     *
     * @param response common response
     */
    fun onErrorNonExistUser(response: IAppError)

    /**
     * Error for locked user
     *
     * @param response common response
     */
    fun onErrorLockedUser(response: IAppError)

    /**
     * Error response
     *
     * @param response common response
     */
    fun onRequestError(response: IAppError)

}