package com.example.basearchitecture.data.network.models

import com.example.basearchitecture.data.network.enums.MimeTypeEnum

/**
 * RequestData
 *
 * Object to model request params of network
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
     * Mime type application
     */
    private var mimeTypeEnum: MimeTypeEnum = MimeTypeEnum.APPLICATION_JSON

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
    fun setErrorObjectResponse(errorResponse: Class<*>?): RequestData {
        this.errorObjectResponse = errorResponse
        return this
    }

    /**
     * Method to set mime type for post service
     *
     * @param mimeTypeEnum mime type for post service
     */
    fun setMimeType(mimeTypeEnum: MimeTypeEnum): RequestData {
        this.mimeTypeEnum = mimeTypeEnum
        return this
    }

    /**
     * Method to get mime type for post service
     *
     * @return mime type for post service
     */
    fun getMimeType(): MimeTypeEnum {
        return this.mimeTypeEnum
    }

}