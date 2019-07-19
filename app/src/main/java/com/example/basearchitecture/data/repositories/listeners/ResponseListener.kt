package com.example.basearchitecture.data.repositories.listeners

import com.example.basearchitecture.data.models.error.IAppError

/**
 * ResponseListener
 */
interface ResponseListener {

    /**
     * Method that is executed when the answer is correct
     *
     * @param response Response object type
     */
    fun onSuccessResponse(response: String)

    /**
     * Method that is executed when the answer is correct
     *
     * @param response generic object response
     */
    fun onSuccessResponseObj(response: Any)

    /**
     * Method that is executed when the request fails
     *
     * @param error response ERROR
     * @param responseCode code of server response
     */
    fun onErrorResponse(error: Any, responseCode: Int = 0)

    /**
     * Method that is executed when the request fails
     *
     * @param error generic error response
     * @param responseCode code of server response
     */
    fun onErrorServer(error: IAppError, responseCode: Int = 0)

    /**
     * Method to invoke get service
     *
     * @param url service url
     * @param endPoint end point service
     */
    fun doGet(url: String, endPoint: String)

    /**
     * Method to invoke post service
     *
     * @param url service url
     * @param endPoint end point service
     */
    fun doPost(url: String, endPoint: String)

}