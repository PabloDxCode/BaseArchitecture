package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.RequestData
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.data.repositories.WibeRepository
import com.example.basearchitecture.domain.businesslogiccase.login.listeners.UseCaseListener
import javax.inject.Inject

/**
 * WibeRepositoryImpl
 */
class WibeRepositoryImpl @Inject constructor(val network: Network) : BaseRepository(), WibeRepository {

    /**
     * Method to set map of headers
     *
     * @param headers map of headers
     *
     * @return this
     */
    override fun setHeaders(headers: Map<String, String>): WibeRepository {
        this.mHeaders = headers
        return this
    }

    /**
     * Method to set request body
     *
     * @param requestBody request body
     *
     * @return this
     */
    override fun setRequestBody(requestBody: String): WibeRepository {
        this.mRequestBody = requestBody
        return this
    }

    /**
     * Method to set map params
     *
     * @param params Map<String, String>?
     *
     * @return this
     */
    override fun setParams(params: Map<String, String>): WibeRepository {
        this.mParams = params
        return this
    }

    /**
     * Method to set success object response
     *
     * @param successResponse success object response
     *
     * @return this
     */
    override fun setSuccessResponse(successResponse: Class<*>): WibeRepository {
        this.mSuccessObjectResponse = successResponse
        return this
    }

    /**
     * Method to set error object response
     *
     * @param errorResponse error object response
     *
     * @return this
     */
    override fun setErrorResponse(errorResponse: Class<*>): WibeRepository {
        this.mErrorObjectResponse = errorResponse
        return this
    }

    /**
     * Method to invoke repository service
     *
     * @param requestCode request code
     * @param useCaseListener use case listener
     * @param zoneType zone type
     */
    override fun invokeWibeService(requestCode: String, useCaseListener: UseCaseListener, zoneType: ZoneTypeEnum) {
        this.mUseCaseListener = useCaseListener
        val requestData = RequestData()
            .setHeaders(mHeaders!!)
            .setRequestCode(requestCode)
            .setRequestBody(mRequestBody!!)
            .setParams(mParams!!)
            .setZoneType(zoneType)
            .setSuccessObjectResponse(mSuccessObjectResponse!!)
            .setErrorObjectResponse(mErrorObjectResponse!!)

        network.init(requestData, this, ApiServiceEnum.WIBE)
        network.execute()
    }

}