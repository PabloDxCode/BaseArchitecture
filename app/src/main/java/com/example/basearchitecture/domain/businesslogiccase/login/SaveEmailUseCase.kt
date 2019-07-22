package com.example.basearchitecture.domain.businesslogiccase.login

/**
 * SaveEmailUseCase
 */
interface SaveEmailUseCase {

    /**
     * Success response
     *
     * @param isEmailSaved is email saved response method
     *
     * @return this
     */
    fun onSuccess(isEmailSaved: () -> Unit): SaveEmailUseCase

    /**
     * Execute method
     *
     * @param email email param
     */
    fun execute(email: String)

}