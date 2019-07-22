package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum

/**
 * BaseNetworkRepository
 */
interface BaseNetworkRepository {

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    fun setHeaders(headers: Map<String, String>): BaseNetworkRepository

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    fun setRequestBody(requestBody: String): BaseNetworkRepository

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    fun setParams(params: Map<String, String>): BaseNetworkRepository

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    fun setSuccessResponse(successResponse: Class<*>): BaseNetworkRepository

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    fun setErrorResponse(errorResponse: Class<*>): BaseNetworkRepository

    /**
     * Method to set zone type
     *
     * @param zoneType zone type
     *
     * @return this
     */
    fun setZoneType(zoneType: ZoneTypeEnum): BaseNetworkRepository

    /**
     * On success response
     *
     * @param response success method response and response code
     *
     * @return this
     */
    fun onSuccess(response: (Any, Int) -> Unit): BaseNetworkRepository

    /**
     * On error response
     *
     * @param error error method with custom error and response code
     *
     * @return this
     */
    fun onError(error: (Any, Int) -> Unit): BaseNetworkRepository

    /**
     * On server error response
     *
     * @param serverError error method with common error and response code
     *
     * @return this
     */
    fun onServerError(serverError: (IAppError, Int) -> Unit): BaseNetworkRepository

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     */
    fun invokeService(requestCode: String)

}