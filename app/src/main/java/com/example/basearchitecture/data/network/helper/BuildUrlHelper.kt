package com.example.basearchitecture.data.network.helper

import com.example.basearchitecture.data.config.ConfigApp
import com.example.basearchitecture.data.network.enums.ZoneTypeEnum
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
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
    fun setZoneType(zoneTypeEnum: ZoneTypeEnum): BuildUrlHelper {
        if (ConfigApp.ourInstance.getEnvironmentSelected().isDevelop(ApiServiceEnum.WIBE)) {
            if (zoneTypeEnum == ZoneTypeEnum.PUBLIC) {
                mEndPoint = mEndPoint!!.replace(PUBLIC_QUOTE_END_POINT_URL, PUBLIC_END_POINT_URL)
            }
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
    fun setMapParams(mapParams: Map<String, String>?, isConcatParams: Boolean = true): BuildUrlHelper {
        if(isConcatParams){
            concatParams(mapParams)
        } else {
            addParams(mapParams)
        }
        return this
    }

    /**
     * Method to concat params to end point
     *
     * @param mapParams map params to concat in end point
     */
    private fun concatParams(mapParams: Map<String, String>?){
        if (mapParams != null && !mapParams.isEmpty()) {
            val newEndPoint = StringBuilder(mEndPoint!! + "?")
            for (item in mapParams.entries) {
                newEndPoint.append("${item.key}=${item.value}&")
            }
            newEndPoint.deleteCharAt(newEndPoint.length - 1)
            mEndPoint = newEndPoint.toString()
        }
    }

    /**
     * Method to add params to end point
     *
     * @param mapParams map params to add in end point
     */
    private fun addParams(mapParams: Map<String, String>?){
        if (mapParams != null && !mapParams.isEmpty()) {
            val newEndPoint = StringBuilder(mEndPoint!!)
            for (item in mapParams.entries) {
                newEndPoint.append("/${item.value}/${item.key}")
            }
            newEndPoint.deleteCharAt(newEndPoint.length - 1)
            mEndPoint = newEndPoint.toString()
        }
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