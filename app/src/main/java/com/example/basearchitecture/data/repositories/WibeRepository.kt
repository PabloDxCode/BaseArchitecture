package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener

/**
 * WibeRepository
 */
interface WibeRepository {

    /**
     * Method to invoke repository service
     *
     * @param requestData request data
     * @param useCaseListener use case listener
     */
    fun invokeWibeService(requestData: RequestData, useCaseListener: UseCaseListener)

}