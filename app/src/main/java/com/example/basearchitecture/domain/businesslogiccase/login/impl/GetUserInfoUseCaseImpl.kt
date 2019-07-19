package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.models.response.GetUserInformationResponse
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.domain.businesslogiccase.login.GetUserInfoUseCase
import javax.inject.Inject

/**
 * GetUserInfoUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class GetUserInfoUseCaseImpl @Inject constructor(val dataManager: DataManager) : GetUserInfoUseCase {

    /**
     * Success get user info method
     */
    private var mSuccessGetUserInfo: ((GetUserInformationResponse) -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

    /**
     * Success response
     *
     * @param successGetUserInfo get user info response method with object param
     *
     * @return this
     */
    override fun onSuccess(successGetUserInfo: (GetUserInformationResponse) -> Unit): GetUserInfoUseCase {
        this.mSuccessGetUserInfo = successGetUserInfo
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): GetUserInfoUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param sessionId session id param
     */
    override fun execute(sessionId: String) {
        val params = HashMap<String, String>()
        params["idWibe"] = sessionId

        dataManager.getWibeRepository()
            .setParams(params)
            .setSuccessResponse(GetUserInformationResponse::class.java)
            .setZoneType(ZoneTypeEnum.PRIVATE)
            .onSuccess { mSuccessGetUserInfo!!.invoke(it as GetUserInformationResponse) }
            .onError { _, _ ->
                mErrorResponse!!.invoke(AppError(null, ResponseErrorType.GENERIC_RESPONSE.toString()))
            }
            .onServerError { iAppError, _ ->
                mErrorResponse!!.invoke(iAppError)
            }
            .invokeService(ConstantsService.GET_USER_INFO_SERVICE_CODE)
    }

}