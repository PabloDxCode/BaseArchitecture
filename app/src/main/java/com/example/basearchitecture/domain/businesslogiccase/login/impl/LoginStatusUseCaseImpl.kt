package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.response.LoginStatusResponse
import com.example.basearchitecture.domain.businesslogiccase.login.LoginStatusUseCase
import javax.inject.Inject

/**
 * LoginStatusUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class LoginStatusUseCaseImpl @Inject constructor(val dataManager: DataManager): LoginStatusUseCase {

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
    override fun onSuccess(isSessionStarted: () -> Unit): LoginStatusUseCase {
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
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): LoginStatusUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     */
    override fun execute() {
        dataManager
            .onSuccess { manageResponse(it as LoginStatusResponse) }
            .onError { mErrorResponse!!.invoke(AppError(null, "session")) }
            .onServerError { mErrorResponse!!.invoke(it) }
            .loginStatus()
    }

    /**
     * Method to manage success response
     *
     * @return response service
     */
    private fun manageResponse(loginStatusResponse: LoginStatusResponse) {
        if(loginStatusResponse.isLogged()){
            mIsSessionStarted!!.invoke()
        } else {
            mErrorResponse!!.invoke(AppError(null, "session"))
        }
    }

}