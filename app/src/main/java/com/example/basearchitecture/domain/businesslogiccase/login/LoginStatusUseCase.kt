package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError

/**
 * LoginStatusUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface LoginStatusUseCase {

    /**
     * Success response
     *
     * @param isSessionStarted is session started response method
     *
     * @return this
     */
    fun onSuccess(isSessionStarted: () -> Unit): LoginStatusUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): LoginStatusUseCase

    /**
     * Execute method
     */
    fun execute()

}