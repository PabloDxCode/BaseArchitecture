package com.example.basearchitecture.data.network.helper

import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.environment.Environment
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.ui.app.config.EnvironmentUrlEnum
import java.lang.StringBuilder

/**
 * BuildUrlHelper
 */
class BuildUrlHelper {

    /**
     * Constant for public endpoint of ip services
     */
    private val PUBLIC_END_POINT_URL = "/mwib_mult_web_mwibpublicwebapp_02"
    /**
     * Constant for public endpoint of production services
     */
    private val PUBLIC_QUOTE_END_POINT_URL = "/cotizadores"
    /**
     * Base url
     */
    private var mBaseUrl: String? = null
    /**
     * End point
     */
    private var mEndPoint: String? = null

    /**
     * Method to set base url
     *
     * @param baseUrl base url
     *
     * @return this
     */
    fun setBaseUrl(baseUrl: String): BuildUrlHelper {
        this.mBaseUrl = baseUrl
        return this
    }

    /**
     * Method to set endpoint
     *
     * @param endPoint end point
     *
     * @return this
     */
    fun setEndPoint(endPoint: String): BuildUrlHelper {
        this.mEndPoint = endPoint
        return this
    }

    /**
     * Method to set zone type
     *
     * @param zoneTypeEnum zone type enum
     * @param apiService api service enum
     *
     * @return this
     */
    fun setZoneType(zoneTypeEnum: ZoneTypeEnum, apiService: ApiServiceEnum): BuildUrlHelper {
        if (Environment.WIBE_ENVIRONMENT == EnvironmentUrlEnum.DEV && apiService == ApiServiceEnum.WIBE) {
            var newEndPoint = ""
            if (zoneTypeEnum == ZoneTypeEnum.PUBLIC) {
                newEndPoint = mEndPoint!!.replace(PUBLIC_QUOTE_END_POINT_URL, PUBLIC_END_POINT_URL)
            }
            mEndPoint = newEndPoint
        }
        return this
    }

    /**
     * Method to set map params
     *
     * @param mapParams map params
     *
     * @return this
     */
    fun setMapParams(mapParams: Map<String, String>?): BuildUrlHelper {
        if (mapParams != null && !mapParams.isEmpty()) {
            val newEndPoint = StringBuilder(mEndPoint!! + "?")
            for (item in mapParams.entries) {
                newEndPoint.append("${item.key}=${item.value}&")
            }
            newEndPoint.deleteCharAt(newEndPoint.length - 1)
            mEndPoint = newEndPoint.toString()
        }
        return this
    }

    /**
     * Method to build and get final url
     *
     * @return final url
     */
    fun build(): String {
        return mBaseUrl + mEndPoint
    }

}