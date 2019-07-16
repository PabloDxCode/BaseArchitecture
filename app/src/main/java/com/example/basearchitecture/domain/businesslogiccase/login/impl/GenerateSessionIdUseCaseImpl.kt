package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.data.models.response.GenerateIdSessionResponse
import com.example.basearchitecture.domain.businesslogiccase.login.GenerateSessionIdUseCase
import com.google.gson.Gson
import javax.inject.Inject

/**
 * GenerateSessionIdUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class GenerateSessionIdUseCaseImpl @Inject constructor(val dataManager: DataManager) : GenerateSessionIdUseCase {

    /**
     * Success session id method
     */
    private var mSuccessSessionId: ((String) -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((ICommonError) -> Unit?)? = null

    /**
     * Success response
     *
     * @param successSessionId session id response method with string param
     *
     * @return this
     */
    override fun onSuccess(successSessionId: (String) -> Unit): GenerateSessionIdUseCase {
        this.mSuccessSessionId = successSessionId
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): GenerateSessionIdUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param email email param
     */
    override fun execute(email: String) {
        val requestBody = Gson().toJson(UserStatusRequest(email))

        dataManager
            .onSuccess {
                val sessionId = (it as GenerateIdSessionResponse).getSessionId()
                mSuccessSessionId!!.invoke(sessionId!!)
            }
            .onError {
                val errorResponse = it as ErrorResponse
                mErrorResponse!!.invoke(AppError(null, errorResponse.getErrorFormDto()!!.getFirstMessageOfList(), null))
            }
            .onServerError { mErrorResponse!!.invoke(it) }
            .generateSessionId(requestBody)
    }

}