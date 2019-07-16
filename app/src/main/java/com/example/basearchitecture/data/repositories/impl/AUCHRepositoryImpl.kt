package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import javax.inject.Inject

/**
 * AUCHRepositoryImpl
 */
class AUCHRepositoryImpl @Inject constructor(val network: Network) : BaseRepositoryImpl() {

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     */
    override fun invokeWibeService(requestCode: String) {
        val requestData = RequestData()
            .setHeaders(mHeaders!!)
            .setRequestCode(requestCode)
            .setRequestBody(mRequestBody!!)
            .setParams(mParams!!)
            .setZoneType(mZoneType)
            .setSuccessObjectResponse(mSuccessObjectResponse!!)
            .setErrorObjectResponse(mErrorObjectResponse!!)

        network.init(requestData, this, ApiServiceEnum.AUCH)
        network.execute()
    }

}