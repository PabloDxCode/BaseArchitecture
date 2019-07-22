package com.example.basearchitecture.domain.businesslogiccase.login

import com.example.basearchitecture.data.models.error.IAppError

/**
 * GetEmailUseCase
 */
interface GetEmailUseCase {

    /**
     * Success response
     *
     * @param emailSaved param method to get email saved
     *
     * @return this
     */
    fun onSuccess(emailSaved: (String) -> Unit): GetEmailUseCase

    /**
     * Execute method
     */
    fun execute()

}