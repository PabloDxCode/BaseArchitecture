package com.example.basearchitecture.data.network.models

import com.example.basearchitecture.data.network.enums.HttpMethod

/**
 * NetworkParams
 */
class NetworkParams {

    /**
     * Base url value
     */
    private var baseUrl: String? = null
    /**
     * End point value
     */
    private var endPoint: String? = null
    /**
     * Method type value
     */
    private var type: String? = null
    /**
     * Response service value
     */
    private var response: String? = null

    /**
     * Method to get base url
     *
     * @return base url
     */
    fun getBaseUrl(): String? {
        return this.baseUrl
    }

    /**
     * Method to set base url
     *
     * @param baseUrl base url
     */
    fun setBaseUrl(baseUrl: String?) {
        this.baseUrl = baseUrl
    }

    /**
     * Method to get endpoint
     *
     * @return endpoint
     */
    fun getEndPoint(): String? {
        return this.endPoint
    }

    /**
     * Method to set endpoint
     *
     * @param endPoint endpoint
     */
    fun setEndPoint(endPoint: String?) {
        this.endPoint = endPoint
    }

    /**
     * Method to get method type
     *
     * @return HttpMethod
     */
    fun getMethodType(): HttpMethod {
        return HttpMethod.findMethodSelected(this.type!!)
    }

    /**
     * Method to set method type
     *
     * @param type method type
     */
    fun setMethodType(type: String?) {
        this.type = type
    }

    /**
     * Method to set response service
     *
     * @param response response service
     */
    fun setResponse(response: String?) {
        this.response = response
    }

    /**
     * Method to get response service
     *
     * @return response service
     */
    fun getResponse(): String? {
        return response
    }

}