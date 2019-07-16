package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.response.GetUserInformationResponse
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
    private var mErrorResponse: ((ICommonError) -> Unit?)? = null

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
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): GetUserInfoUseCase {
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

        dataManager
            .onSuccess { mSuccessGetUserInfo!!.invoke(it as GetUserInformationResponse) }
            .onError { mErrorResponse!!.invoke(AppError(null, "generic_response")) }
            .onServerError { mErrorResponse!!.invoke(it) }
            .getUserInfo(params)
    }

}