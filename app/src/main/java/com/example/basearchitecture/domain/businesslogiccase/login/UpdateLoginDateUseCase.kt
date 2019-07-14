package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.ICommonError

/**
 * UpdateLoginDateUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface UpdateLoginDateUseCase {

    /**
     * Success response for update login date service
     *
     * @param successLogin success update login date method
     *
     * @return this
     */
    fun onSuccess(successUpdateLoginDate: () -> Unit): UpdateLoginDateUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (ICommonError) -> Unit): UpdateLoginDateUseCase

    /**
     * Execute method
     *
     * @param email email param
     */
    fun execute(email: String)

}