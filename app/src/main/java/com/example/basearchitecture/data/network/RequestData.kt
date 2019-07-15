package com.example.basearchitecture.data.network

import com.example.basearchitecture.data.network.enums.ZoneTypeEnum

/**
 * RequestData
 */
class RequestData {

    /**
     * Map of headers
     */
    private var headers: Map<String, String>? = null
    /**
     * Request code
     */
    private var requestCode: String? = null
    /**
     * Request body
     */
    private var requestBody: String? = null
    /**
     * Map of request params
     */
    private var params: Map<String, String>? = null
    /**
     * Zone type enum
     */
    private var zoneType: ZoneTypeEnum? = null
    /**
     * Success object response
     */
    private var successObjectResponse: Class<*>? = null
    /**
     * Error object response
     */
    private var errorObjectResponse: Class<*>? = null

    /**
     * Init method
     */
    init {
        this.headers = HashMap()
        this.requestCode = ""
        this.zoneType = ZoneTypeEnum.PUBLIC
    }

    /**
     * Method to get headers
     *
     * @return Map<String, String>?
     */
    fun getHeaders(): Map<String, String>? {
        return headers
    }

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    fun setHeaders(headers: Map<String, String>): RequestData {
        this.headers = headers
        return this
    }

    /**
     * Method to get request code
     *
     * @return request code
     */
    fun getRequestCode(): String? {
        return requestCode
    }

    /**
     * Method to set request code
     *
     * @param requestCode request code
     *
     * @return this
     */
    fun setRequestCode(requestCode: String): RequestData {
        this.requestCode = requestCode
        return this
    }

    /**
     * Method to get request body
     *
     * @return request body
     */
    fun getRequestBody(): String? {
        return requestBody
    }

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    fun setRequestBody(requestBody: String): RequestData {
        this.requestBody = requestBody
        return this
    }

    /**
     * Method to get map params
     *
     * @return Map<String, String>?
     */
    fun getParams(): Map<String, String>? {
        return params
    }

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    fun setParams(params: Map<String, String>): RequestData {
        this.params = params
        return this
    }

    /**
     * Method to get zone type
     *
     * @return zone type enum
     */
    fun getZoneType(): ZoneTypeEnum? {
        return zoneType
    }

    /**
     * Method to set zone type enum
     *
     * @param zoneType zone type enum
     *
     * @return this
     */
    fun setZoneType(zoneType: ZoneTypeEnum): RequestData {
        this.zoneType = zoneType
        return this
    }

    /**
     * Method to get success object response
     *
     * @return success object response
     */
    fun getSuccessObjectResponse(): Class<*>? {
        return successObjectResponse
    }

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    fun setSuccessObjectResponse(successResponse: Class<*>): RequestData {
        this.successObjectResponse = successResponse
        return this
    }

    /**
     * Method to get error object response
     *
     * @return error object response
     */
    fun getErrorObjectResponse(): Class<*>? {
        return errorObjectResponse
    }

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    fun setErrorObjectResponse(errorResponse: Class<*>): RequestData {
        this.errorObjectResponse = errorResponse
        return this
    }

}