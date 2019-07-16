package com.example.basearchitecture.ui.modules.login.presenters.listeners

import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.domain.businesslogiccase.enums.ViewTypeErrorEnum

/**
 * LoginPresenterListener
 */
interface LoginPresenterListener {

    /**
     * Success method
     */
    fun onSuccess()

    /**
     * Method to return view error
     *
     * @param viewTypeError view error
     */
    fun onViewError(viewTypeError: ViewTypeErrorEnum)

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