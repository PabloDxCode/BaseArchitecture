package com.example.basearchitecture.data.network.interfaces

import com.example.basearchitecture.data.network.models.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.data.repositories.listeners.ResponseListener

/**
 * INetwork
 */
interface INetwork{

    /**
     * Method to init request
     *
     * @param requestData request data instance
     * @param responseListener response listener
     * @param apiService api service
     */
    fun init(requestData: RequestData, responseListener: ResponseListener, apiService: ApiServiceEnum)

    /**
     * Methdo to execute service
     */
    fun execute()

    /**
     * Method to launch GET service
     *
     * @param headers map of headers
     * @param url service url
     */
    fun get(headers: Map<String, String>, url: String)

    /**
     * Method to launch POST service
     *
     * @param headers map of headers
     * @param url service url
     * @param body body of service
     */
    fun post(headers: Map<String, String>, url: String, body: String)

}