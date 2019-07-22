package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.domain.businesslogiccase.login.UpdateLoginDateUseCase
import com.google.gson.Gson
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
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

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
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): UpdateLoginDateUseCase {
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

        dataManager.getWibeRepository()
            .setRequestBody(requestBody)
            .setZoneType(ZoneTypeEnum.PRIVATE)
            .setSuccessResponse(ErrorResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .onSuccess { _, _ -> mSuccessUpdateLoginDate!!.invoke() }
            .onError { iAppError, _ ->
                val errorResponse = iAppError as ErrorResponse
                mErrorResponse!!.invoke(AppError(null, errorResponse.getErrorFormDto()!!.getFirstMessageOfList(),null))
            }
            .onServerError { iAppError, _ ->
                mErrorResponse!!.invoke(iAppError)
            }
            .invokeService(ConstantsService.UPDATE_LOGIN_DATE_SERVICE_CODE)
    }

}