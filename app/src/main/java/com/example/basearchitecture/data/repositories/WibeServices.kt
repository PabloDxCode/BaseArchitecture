package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener

/**
 * WibeServices
 */
interface WibeServices {

    /**
     * Method to do login
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    fun login(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener)

    /**
     * Method to generate session id
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    fun generateSessionId(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener)

    /**
     * Method to do login slod
     *
     * @param email email param
     * @param password password param
     * @param useCaseListener generic use case listener
     */
    fun loginSlod(email: String, password: String, useCaseListener: UseCaseListener)

    /**
     * Method to do login status
     *
     * @param useCaseListener generic use case listener
     */
    fun loginStatus(useCaseListener: UseCaseListener)

    /**
     * Method to get user information
     *
     * @param idSession wibe id session
     * @param useCaseListener generic use case listener
     */
    fun getUserInfo(idSession: String, useCaseListener: UseCaseListener)

    /**
     * Method to update login date
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    fun updateLoginDate(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener)

}