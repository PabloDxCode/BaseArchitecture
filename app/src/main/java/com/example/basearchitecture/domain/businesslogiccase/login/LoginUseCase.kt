package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError

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
     * Error response for pending status user
     *
     * @param errorPendingStatusUser pending status user with common error param
     *
     * @return this
     */
    fun onErrorPendingStatusUser(errorPendingStatusUser: () -> Unit): LoginUseCase

    /**
     * Error response for inactive user
     *
     * @param errorInactiveUser unit method for inactive user error
     *
     * @return this
     */
    fun onErrorInactiveUser(errorInactiveUser: () -> Unit): LoginUseCase

    /**
     * Error response for non exist user
     *
     * @param errorNonExistUser non exist user with common error param
     *
     * @return this
     */
    fun onErrorNonExistUser(errorNonExistUser: (IAppError) -> Unit): LoginUseCase

    /**
     * Error response for locked user
     *
     * @param errorLockedUser common error response
     *
     * @return this
     */
    fun onErrorLockedUser(errorLockedUser: (IAppError) -> Unit): LoginUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): LoginUseCase

    /**
     * Execute method
     *
     * @param email email param
     * @param password password param
     */
    fun execute(email: String, password: String)

}