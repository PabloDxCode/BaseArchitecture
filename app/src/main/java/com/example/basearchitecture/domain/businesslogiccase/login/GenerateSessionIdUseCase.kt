package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError

/**
 * GenerateSessionIdUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface GenerateSessionIdUseCase {

    /**
     * Success response
     *
     * @param successSessionId session id response method with string param
     *
     * @return this
     */
    fun onSuccess(successSessionId: (String) -> Unit): GenerateSessionIdUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): GenerateSessionIdUseCase

    /**
     * Execute method
     *
     * @param email email param
     */
    fun execute(email: String)

}