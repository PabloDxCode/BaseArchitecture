package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.domain.businesslogiccase.login.LoginSlodUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.helper.SessionErrorHelper
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener
import javax.inject.Inject

/**
 * LoginSlodUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class LoginSlodUseCaseImpl @Inject constructor(val dataManager: DataManager) : LoginSlodUseCase {

    /**
     * Success session started method
     */
    private var mIsSessionStarted: (() -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((ICommonError) -> Unit?)? = null

    /**
     * Success response
     *
     * @param isSessionStarted is session started response method
     *
     * @return this
     */
    override fun onSuccess(isSessionStarted: () -> Unit): LoginSlodUseCase {
        this.mIsSessionStarted = isSessionStarted
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): LoginSlodUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param email email param
     * @param password password param
     */
    override fun execute(email: String, password: String) {
        dataManager.loginSlod(email, password, object : UseCaseListener{
            /**
             * Success response
             *
             * @param response response object
             */
            override fun onSuccess(response: Any) {
                manageResponse(response.toString())
            }

            /**
             * Error response
             *
             * @param error error object
             */
            override fun onError(error: Any) {
                mErrorResponse!!.invoke(AppError(null, "session"))
            }

            /**
             * Error response
             *
             * @param error generic error
             */
            override fun onErrorServer(error: ICommonError) {
                mErrorResponse!!.invoke(error)
            }
        })
    }

    /**
     * Method to manage success response
     *
     * @return response service
     */
    private fun manageResponse(response: String) {
        val sessionResponse = SessionErrorHelper.getSessionResponse(response)
        if (sessionResponse.isSessionActive) {
            mIsSessionStarted!!.invoke()
        } else {
            mErrorResponse!!.invoke(AppError(null, sessionResponse.errorCode))
        }
    }

}