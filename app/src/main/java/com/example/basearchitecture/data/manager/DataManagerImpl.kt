package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.response.*
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.data.repositories.BaseRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
import javax.inject.Inject

/**
 * DataManagerImpl
 */
class DataManagerImpl @Inject constructor(
    val wibeRepository: BaseRepository,
    val dataBaseRepository: DataBaseRepository
) : DataManager {

    /**
     * Success method response
     */
    private var mResponse: ((Any) -> Unit?)? = null
    /**
     * Error method response
     */
    private var mError: ((Any) -> Unit?)? = null
    /**
     * Server error method response
     */
    private var mServerError: ((ICommonError) -> Unit?)? = null

    /**
     * On success response
     *
     * @param response success method response
     *
     * @return this
     */
    override fun onSuccess(response: (Any) -> Unit): DataManager {
        this.mResponse = response
        return this
    }

    /**
     * On error response
     *
     * @param error error method response
     *
     * @return this
     */
    override fun onError(error: (Any) -> Unit): DataManager {
        this.mError = error
        return this
    }

    /**
     * On server error response
     *
     * @param serverError server error method response
     *
     * @return this
     */
    override fun onServerError(serverError: (ICommonError) -> Unit): DataManager {
        this.mServerError = serverError
        return this
    }

    /**
     * Method to do login
     *
     * @param requestBody user status object
     */
    override fun login(requestBody: String) {
        wibeRepository
            .setRequestBody(requestBody)
            .setSuccessResponse(UserStatusResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.LOGIN_SERVICE_CODE)
    }

    /**
     * Method to generate session id
     *
     * @param requestBody user status object
     */
    override fun generateSessionId(requestBody: String) {
        wibeRepository
            .setRequestBody(requestBody)
            .setSuccessResponse(GenerateIdSessionResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.GENERATE_SESSION_ID_SERVICE_CODE)
    }

    /**
     * Method to do login slod
     *
     * @param params map params
     */
    override fun loginSlod(params: HashMap<String, String>) {
        wibeRepository
            .setParams(params)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.LOGIN_SLOD_SERVICE_CODE)
    }

    /**
     * Method to do login status
     */
    override fun loginStatus() {
        wibeRepository
            .setSuccessResponse(LoginStatusResponse::class.java)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.LOGIN_STATUS_SERVICE_CODE)
    }

    /**
     * Method to get user information
     *
     * @param params map params
     */
    override fun getUserInfo(params: HashMap<String, String>) {
        wibeRepository
            .setParams(params)
            .setSuccessResponse(GetUserInformationResponse::class.java)
            .setZoneType(ZoneTypeEnum.PRIVATE)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.GET_USER_INFO_SERVICE_CODE)
    }

    /**
     * Method to update login date
     *
     * @param requestBody user status object
     */
    override fun updateLoginDate(requestBody: String) {
        wibeRepository
            .setRequestBody(requestBody)
            .setZoneType(ZoneTypeEnum.PRIVATE)
            .setSuccessResponse(ErrorResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .setResponseListeners(mResponse, mError, mServerError)
            .invokeWibeService(ConstantsService.UPDATE_LOGIN_DATE_SERVICE_CODE)
    }

}