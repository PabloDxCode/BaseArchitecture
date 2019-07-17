package com.example.basearchitecture.domain.businesslogiccase.login.factory

import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.data.models.error.IAppError

/**
 * ILoginFactory
 */
interface ILoginFactory {

    /**
     * Method to init interactor
     *
     * @param email email param
     * @param password password param
     *
     * @return this
     */
    fun init(email: String, password: String): ILoginFactory

    /**
     * Success response for login service
     *
     * @param successLogin success login method
     *
     * @return this
     */
    fun onSuccess(successLogin: () -> Unit): ILoginFactory

    /**
     * Error response for pending status user
     *
     * @param errorPendingStatusUser pending status user with common error param
     *
     * @return this
     */
    fun onErrorPendingStatusUser(errorPendingStatusUser: () -> Unit): ILoginFactory

    /**
     * Error response for inactive user
     *
     * @param errorInactiveUser unit method for inactive user error
     *
     * @return this
     */
    fun onErrorInactiveUser(errorInactiveUser: () -> Unit): ILoginFactory

    /**
     * Error response for non exist user
     *
     * @param errorNonExistUser non exist user with common error param
     *
     * @return this
     */
    fun onErrorNonExistUser(errorNonExistUser: (IAppError) -> Unit): ILoginFactory

    /**
     * Error response for locked user
     *
     * @param errorLockedUser common error response
     *
     * @return this
     */
    fun onErrorLockedUser(errorLockedUser: (IAppError) -> Unit): ILoginFactory

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    fun onErrorResponse(errorResponse: (IAppError) -> Unit): ILoginFactory

    /**
     * Method to do login
     */
    fun doLogin()

    /**
     * Method to generate session id
     */
    fun generateSessionId()

    /**
     * Method to login slod
     */
    fun loginSlod()

    /**
     * Method to login status
     */
    fun loginStatus()

    /**
     * Method to get user info
     *
     * @param sessionId session id param
     */
    fun getUserInfo(sessionId: String)

    /**
     * Method to save user info
     *
     * @param userInfo user info
     */
    fun saveUserInfo(userInfo: User)

    /**
     * Method to update login date
     */
    fun updateLoginDate()

}