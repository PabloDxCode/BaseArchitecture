package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.models.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.data.network.helper.BuildUrlHelper
import javax.inject.Inject

/**
 * WibeRepositoryImpl
 */
class WibeRepositoryImpl @Inject constructor(val network: Network) : BaseNetworkRepositoryImpl() {

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     */
    override fun invokeService(requestCode: String) {
        val requestData = RequestData()
            .setRequestCode(requestCode)
            .setSuccessObjectResponse(mSuccessObjectResponse!!)
            .setErrorObjectResponse(mErrorObjectResponse)

        network.init(requestData, this, ApiServiceEnum.WIBE)
        network.execute()
    }

    /**
     * Method to invoke get service
     *
     * @param url service url
     * @param endPoint end point service
     */
    override fun doGet(url: String, endPoint: String) {
        val newUrl =
            BuildUrlHelper().setBaseUrl(url).setEndPoint(endPoint).setZoneType(mZoneType).setMapParams(mParams!!)
                .build()
        network.get(mHeaders!!, newUrl)
    }

    /**
     * Method to invoke post service
     *
     * @param url service url
     * @param endPoint end point service
     */
    override fun doPost(url: String, endPoint: String) {
        val newUrl =
            BuildUrlHelper().setBaseUrl(url).setEndPoint(endPoint).setZoneType(mZoneType).setMapParams(mParams!!)
                .build()
        network.post(mHeaders!!, newUrl, mRequestBody!!)
    }

}