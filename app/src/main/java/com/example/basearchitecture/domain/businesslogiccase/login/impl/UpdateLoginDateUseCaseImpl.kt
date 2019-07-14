package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.domain.businesslogiccase.login.UpdateLoginDateUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener
import javax.inject.Inject

/**
 * UpdateLoginDateUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class UpdateLoginDateUseCaseImpl @Inject constructor(val dataManager: DataManager): UpdateLoginDateUseCase {

    /**
     * Success update login date method
     */
    private var mSuccessUpdateLoginDate: (() -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((ICommonError) -> Unit?)? = null

    /**
     * Success response for update login date service
     *
     * @param successLogin success update login date method
     *
     * @return this
     */
    override fun onSuccess(successUpdateLoginDate: () -> Unit): UpdateLoginDateUseCase {
        this.mSuccessUpdateLoginDate = successUpdateLoginDate
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): UpdateLoginDateUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param email email param
     */
    override fun execute(email: String) {
        dataManager.updateLoginDate(UserStatusRequest(email), object : UseCaseListener{
            /**
             * Success response
             *
             * @param response response object
             */
            override fun onSuccess(response: Any) {
                mSuccessUpdateLoginDate!!.invoke()
            }

            /**
             * Error response
             *
             * @param error error object
             */
            override fun onError(error: Any) {
                val errorResponse = error as ErrorResponse
                mErrorResponse!!.invoke(AppError(null, errorResponse.getErrorFormDto()!!.getFirstMessageOfList(),null))
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

}