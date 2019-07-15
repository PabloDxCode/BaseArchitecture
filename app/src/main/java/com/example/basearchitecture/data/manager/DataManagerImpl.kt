package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.data.models.response.*
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.data.repositories.DataBaseRepository
import com.example.basearchitecture.data.repositories.WibeRepository
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener
import com.google.gson.Gson
import javax.inject.Inject

/**
 * DataManagerImpl
 */
class DataManagerImpl @Inject constructor(val wibeRepository: WibeRepository, val dataBaseRepository: DataBaseRepository) :
    DataManager {

    /**
     * Method to do login
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    override fun login(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener) {
        wibeRepository
            .setRequestBody(Gson().toJson(userStatusRequest))
            .setSuccessResponse(UserStatusResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .invokeWibeService(ConstantsService.LOGIN_SERVICE_CODE, useCaseListener)
    }

    /**
     * Method to generate session id
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    override fun generateSessionId(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener) {
        wibeRepository
            .setRequestBody(Gson().toJson(userStatusRequest))
            .setSuccessResponse(GenerateIdSessionResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .invokeWibeService(ConstantsService.GENERATE_SESSION_ID_SERVICE_CODE, useCaseListener)
    }

    /**
     * Method to do login slod
     *
     * @param email email param
     * @param password password param
     * @param useCaseListener generic use case listener
     */
    override fun loginSlod(email: String, password: String, useCaseListener: UseCaseListener) {
        val params = HashMap<String, String>()
        params["eai_user"] = email + "-w"
        params["idWibe"] = ""
        params["eai_password"] = password
        params["origen"] = "wibe"
        params["eai_URLDestino"] =
                "https://qa.wibe.com.mx/mwib_mult_web_mwibprivatewebapp_01/api/loginStatus&idioma=CAS"

        wibeRepository
            .setParams(params)
            .invokeWibeService(ConstantsService.LOGIN_SLOD_SERVICE_CODE, useCaseListener)
    }

    /**
     * Method to do login status
     *
     * @param useCaseListener generic use case listener
     */
    override fun loginStatus(useCaseListener: UseCaseListener) {
        wibeRepository
            .setSuccessResponse(LoginStatusResponse::class.java)
            .invokeWibeService(ConstantsService.LOGIN_STATUS_SERVICE_CODE, useCaseListener)
    }

    /**
     * Method to get user information
     *
     * @param idSession wibe id session
     * @param useCaseListener generic use case listener
     */
    override fun getUserInfo(idSession: String, useCaseListener: UseCaseListener) {
        val params = HashMap<String, String>()
        params["idWibe"] = idSession

        wibeRepository
            .setParams(params)
            .setSuccessResponse(GetUserInformationResponse::class.java)
            .invokeWibeService(ConstantsService.GET_USER_INFO_SERVICE_CODE, useCaseListener, ZoneTypeEnum.PRIVATE)
    }

    /**
     * Method to update login date
     *
     * @param userStatusRequest user status object
     * @param useCaseListener generic use case listener
     */
    override fun updateLoginDate(userStatusRequest: UserStatusRequest, useCaseListener: UseCaseListener) {
        wibeRepository
            .setRequestBody(Gson().toJson(userStatusRequest))
            .setSuccessResponse(ErrorResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .invokeWibeService(ConstantsService.UPDATE_LOGIN_DATE_SERVICE_CODE, useCaseListener, ZoneTypeEnum.PRIVATE)
    }

}