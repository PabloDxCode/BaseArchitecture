package com.example.basearchitecture.presentation.modules.login.presenters.listeners

import com.example.basearchitecture.data.models.error.ICommonError

/**
 * LoginPresenterListener
 */
interface LoginPresenterListener {

    /**
     * Success method
     */
    fun onSuccess()

    /**
     * Error for inactive user
     *
     * @param response common response
     */
    fun onErrorInactiveUser(response: ICommonError)

    /**
     * Error for non-exist user
     *
     * @param response common response
     */
    fun onErrorNonExistUser(response: ICommonError)

    /**
     * Error response
     *
     * @param response common response
     */
    fun onRequestError(response: ICommonError)

    /**
     * Error for inactive session
     *
     * @param response common response
     */
    fun onErrorInactiveSession(response: ICommonError)

}