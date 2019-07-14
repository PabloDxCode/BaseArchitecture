package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.ICommonError

/**
 * LoginUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface LoginUseCase {

    /**
     * Success response for login service
     *
     * @param successLogin success login method
     *
     * @return this
     */
    fun onSuccess(successLogin: () -> Unit): LoginUseCase

    /**
     * Error response for inactive user
     *
     * @param errorInactiveUser inactive user with common error param
     *
     * @return this
     */
    fun onErrorInactiveUser(errorInactiveUser: (ICommonError) -> Unit): LoginUseCase

    /**
     * Error response for non exist user
     *
     * @param errorNonExistUser non exist user with common error param
     *
     * @return this
     */
    fun onErrorNonExistUser(errorNonExistUser: (ICommonError) -> Unit): LoginUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (ICommonError) -> Unit): LoginUseCase

    /**
     * Execute method
     *
     * @param email email param
     * @param password password param
     */
    fun execute(email: String, password: String)

}