package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.models.response.GetUserInformationResponse

/**
 * GetUserInfoUseCase
 *
 * Implements CommonErrorResponseUseCase
 */
interface GetUserInfoUseCase {

    /**
     * Success response
     *
     * @param successGetUserInfo get user info response method with object param
     *
     * @return this
     */
    fun onSuccess(successGetUserInfo: (GetUserInformationResponse) -> Unit): GetUserInfoUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): GetUserInfoUseCase

    /**
     * Execute method
     *
     * @param sessionId session id param
     */
    fun execute(sessionId: String)

}