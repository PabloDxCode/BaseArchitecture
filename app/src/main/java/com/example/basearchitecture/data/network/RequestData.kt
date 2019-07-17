package com.example.basearchitecture.data.network

/**
 * RequestData
 */
class RequestData {

    /**
     * Request code
     */
    private var requestCode: String? = null
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
        this.requestCode = ""
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