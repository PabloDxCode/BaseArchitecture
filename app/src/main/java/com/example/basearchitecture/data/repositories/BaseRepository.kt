package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum

/**
 * BaseRepository
 */
interface BaseRepository {

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    fun setHeaders(headers: Map<String, String>): BaseRepository

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    fun setRequestBody(requestBody: String): BaseRepository

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    fun setParams(params: Map<String, String>): BaseRepository

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    fun setSuccessResponse(successResponse: Class<*>): BaseRepository

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    fun setErrorResponse(errorResponse: Class<*>): BaseRepository

    /**
     * Method to set zone type
     *
     * @param zoneType zone type
     *
     * @return this
     */
    fun setZoneType(zoneType: ZoneTypeEnum): BaseRepository

    /**
     * Method to set response listeners
     *
     * @param response success method response
     * @param error error method response
     * @param serverError server error method response
     *
     * @return this
     */
    fun setResponseListeners(response: ((Any) -> Unit?)?, error: ((Any) -> Unit?)?, serverError: ((ICommonError) -> Unit?)?): BaseRepository

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     */
    fun invokeWibeService(requestCode: String)

}