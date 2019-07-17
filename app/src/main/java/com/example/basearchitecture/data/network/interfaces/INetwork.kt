package com.example.basearchitecture.data.network.interfaces

import com.example.basearchitecture.data.network.RequestData
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

    fun get(headers: Map<String, String>, url: String)

    fun post(headers: Map<String, String>, url: String, body: String)

}