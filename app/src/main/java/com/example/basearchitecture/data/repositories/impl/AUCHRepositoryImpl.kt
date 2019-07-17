package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import javax.inject.Inject

/**
 * AUCHRepositoryImpl
 */
class AUCHRepositoryImpl @Inject constructor(val network: Network) : BaseNetworkRepositoryImpl() {

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     */
    override fun invokeService(requestCode: String) {
        val requestData = RequestData()
            .setRequestCode(requestCode)
            .setSuccessObjectResponse(mSuccessObjectResponse!!)
            .setErrorObjectResponse(mErrorObjectResponse!!)

        network.init(requestData, this, ApiServiceEnum.AUCH)
        network.execute()
    }

    /**
     * Method to invoke get service
     *
     * @param url service url
     * @param endPoint end point service
     */
    override fun doGet(url: String, endPoint: String) {
    }

    /**
     * Method to invoke post service
     *
     * @param url service url
     * @param endPoint end point service
     */
    override fun doPost(url: String, endPoint: String) {
    }

}