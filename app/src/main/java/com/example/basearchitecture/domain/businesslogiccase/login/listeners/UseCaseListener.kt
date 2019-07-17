package com.example.basearchitecture.domain.businesslogiccase.login.listeners

import com.example.basearchitecture.data.models.error.IAppError

/**
 * UseCaseListener
 */
interface UseCaseListener {

    /**
     * Success response
     *
     * @param response response object
     */
    fun onSuccess(response: Any)

    /**
     * Error response
     *
     * @param error error object
     */
    fun onError(error: Any)

    /**
     * Error response
     *
     * @param error generic error
     */
    fun onErrorServer(error: IAppError)

}