package com.example.basearchitecture.data.repositories

import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener

interface AUCHRepository {

    /**
     * Method to invoke repository service
     *
     * @param requestData request data
     * @param useCaseListener use case listener
     */
    fun invokeAUCHService(requestData: RequestData, useCaseListener: UseCaseListener)

}