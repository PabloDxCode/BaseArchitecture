package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener

interface AUCHRepository {

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    fun setHeaders(headers: Map<String, String>): AUCHRepository

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    fun setRequestBody(requestBody: String): AUCHRepository

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    fun setParams(params: Map<String, String>): AUCHRepository

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    fun setSuccessResponse(successResponse: Class<*>): AUCHRepository

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    fun setErrorResponse(errorResponse: Class<*>): AUCHRepository

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     * @param useCaseListener use case listener
     * @param zoneType zone type
     */
    fun invokeWibeService(
        requestCode: String,
        useCaseListener: UseCaseListener,
        zoneType: ZoneTypeEnum = ZoneTypeEnum.PUBLIC
    )

}