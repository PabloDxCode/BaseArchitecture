package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError

/**
 * LoginSlodUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface LoginSlodUseCase {

    /**
     * Success response
     *
     * @param isSessionStarted is session started response method
     *
     * @return this
     */
    fun onSuccess(isSessionStarted: () -> Unit): LoginSlodUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): LoginSlodUseCase

    /**
     * Execute method
     *
     * @param email email param
     * @param password password param
     */
    fun execute(email: String, password: String)

}