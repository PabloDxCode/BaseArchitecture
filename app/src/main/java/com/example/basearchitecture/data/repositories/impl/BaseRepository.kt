package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.repositories.listeners.ResponseListener
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener

/**
 * BaseRepository
 */
abstract class BaseRepository: ResponseListener {

    /**
     * Use case listener
     */
    protected var mUseCaseListener: UseCaseListener? = null
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
     * Init method
     */
    init {
        this.mHeaders = HashMap()
        this.mRequestBody = ""
        this.mParams = HashMap()
        this.mSuccessObjectResponse = String::class.java
        this.mErrorObjectResponse = String::class.java
    }

    /**
     * Method that is executed when the answer is correct
     *
     * @param response Response object type
     */
    override fun onSuccessResponse(response: String) {
        mUseCaseListener!!.onSuccess(response)
    }

    /**
     * Method that is executed when the answer is correct
     *
     * @param response generic object response
     */
    override fun onSuccessResponseObj(response: Any) {
        mUseCaseListener!!.onSuccess(response)
    }

    /**
     * Method that is executed when the request fails
     *
     * @param error response ERROR
     */
    override fun onErrorResponse(error: Any) {
        mUseCaseListener!!.onError(error)
    }

    /**
     * Method that is executed when the request fails
     *
     * @param error generic error response
     */
    override fun onErrorServer(error: ICommonError) {
        mUseCaseListener!!.onErrorServer(error)
    }

}