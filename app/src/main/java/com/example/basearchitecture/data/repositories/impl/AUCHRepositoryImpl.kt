package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.data.repositories.AUCHRepository
import com.example.basearchitecture.data.repositories.listeners.ResponseListener
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener
import javax.inject.Inject

class AUCHRepositoryImpl @Inject constructor(val network: Network) : AUCHRepository, ResponseListener {

    /**
     * Use case listener
     */
    var mUseCaseListener: UseCaseListener? = null

    /**
     * Method to invoke repository service
     *
     * @param requestData request data
     * @param useCaseListener use case listener
     */
    override fun invokeAUCHService(requestData: RequestData, useCaseListener: UseCaseListener) {
        this.mUseCaseListener = useCaseListener
        network.init(requestData, this, ApiServiceEnum.AUCH)
        network.execute()
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