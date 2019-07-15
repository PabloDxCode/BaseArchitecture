package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.data.models.error.ICommonError

/**
 * SaveUserInfoUseCase
 */
interface SaveUserInfoUseCase {

    /**
     * Success response
     *
     * @param successInfoSaved success method for save info
     *
     * @return this
     */
    fun onSuccess(successInfoSaved: () -> Unit): SaveUserInfoUseCase

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (ICommonError) -> Unit): SaveUserInfoUseCase

    /**
     * Execute method
     *
     * @param userInfo user information
     */
    fun execute(userInfo: User)

}