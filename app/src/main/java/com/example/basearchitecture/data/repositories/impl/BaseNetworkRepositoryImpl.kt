package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.data.repositories.BaseNetworkRepository
import com.example.basearchitecture.data.repositories.listeners.ResponseListener

/**
 * BaseNetworkRepositoryImpl
 */
abstract class BaseNetworkRepositoryImpl: BaseNetworkRepository, ResponseListener {

    /**
     * Map of headers
     */
    protected var mHeaders: Map<String, String>? = null
    /**
     * Request body
     */
    protected var mRequestBody: String? = null
    /**
     * Map of request params
     */
    protected var mParams: Map<String, String>? = null
    /**
     * Success object response
     */
    protected var mSuccessObjectResponse: Class<*>? = null
    /**
     * Error object response
     */
    protected var mErrorObjectResponse: Class<*>? = null
    /**
     * Zone type
     */
    protected var mZoneType: ZoneTypeEnum = ZoneTypeEnum.PUBLIC
    /**
     * Success method response
     */
    protected var mResponse: ((Any) -> Unit?)? = null
    /**
     * Error method response
     */
    protected var mError: ((Any, Int) -> Unit?)? = null
    /**
     * Server error method response
     */
    protected var mServerError: ((IAppError, Int) -> Unit?)? = null

    /**
     * Init method
     */
    init {
        this.mHeaders = HashMap()
        this.mRequestBody = ""
        this.mParams = HashMap()
        this.mSuccessObjectResponse = String::class.java
    }

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    override fun setHeaders(headers: Map<String, String>): BaseNetworkRepository {
        this.mHeaders = headers
        return this
    }

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    override fun setRequestBody(requestBody: String): BaseNetworkRepository {
        this.mRequestBody = requestBody
        return this
    }

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    override fun setParams(params: Map<String, String>): BaseNetworkRepository {
        this.mParams = params
        return this
    }

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    override fun setSuccessResponse(successResponse: Class<*>): BaseNetworkRepository {
        this.mSuccessObjectResponse = successResponse
        return this
    }

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    override fun setErrorResponse(errorResponse: Class<*>): BaseNetworkRepository {
        this.mErrorObjectResponse = errorResponse
        return this
    }

    /**
     * Method to set zone type
     *
     * @param zoneType zone type
     *
     * @return this
     */
    override fun setZoneType(zoneType: ZoneTypeEnum): BaseNetworkRepository {
        this.mZoneType = zoneType
        return this
    }

    /**
     * On success response
     *
     * @param response success method response
     *
     * @return this
     */
    override fun onSuccess(response: (Any) -> Unit): BaseNetworkRepository {
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
    override fun onError(error: (Any, Int) -> Unit): BaseNetworkRepository {
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
    override fun onServerError(serverError: (IAppError, Int) -> Unit): BaseNetworkRepository {
        this.mServerError = serverError
        return this
    }

    /**
     * Method that is executed when the answer is correct
     *
     * @param response Response object type
     */
    override fun onSuccessResponse(response: String) {
        if(this.mResponse != null){
            this.mResponse!!.invoke(response)
        }
    }

    /**
     * Method that is executed when the answer is correct
     *
     * @param response generic object response
     */
    override fun onSuccessResponseObj(response: Any) {
        if(this.mResponse != null){
            this.mResponse!!.invoke(response)
        }
    }

    /**
     * Method that is executed when the request fails
     *
     * @param error response ERROR
     * @param responseCode code of server response
     */
    override fun onErrorResponse(error: Any, responseCode: Int) {
        if(this.mError != null){
            this.mError!!.invoke(error, responseCode)
        }
    }

    /**
     * Method that is executed when the request fails
     *
     * @param error generic error response
     * @param responseCode code of server response
     */
    override fun onErrorServer(error: IAppError, responseCode: Int) {
        if(this.mServerError != null){
            this.mServerError!!.invoke(error, responseCode)
        }
    }

}