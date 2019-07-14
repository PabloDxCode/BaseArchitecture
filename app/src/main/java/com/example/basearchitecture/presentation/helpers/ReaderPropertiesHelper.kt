package com.example.basearchitecture.presentation.helpers

import android.content.Context
import android.content.res.AssetManager
import com.example.basearchitecture.data.network.NetworkParams
import com.example.basearchitecture.environment.Environment
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import java.io.IOException
import java.util.Properties

/**
 * ReaderPropertiesHelper
 *
 * @param context context instance
 */
class ReaderPropertiesHelper(val context: Context) {

    /**
     * Constant for end point service
     */
    private val END_POINT_SERVICE: String = "endPoint."
    /**
     * Constant for method service
     */
    private val METHOD_SERVICE: String = "type."
    /**
     * Constant for response service
     */
    private val RESPONSE_SERVICE: String = "response."
    /**
     * Constant for properties name api service
     */
    private val PROPERTIES_NAME_API_SERVICE: String = "EnviromentSource.properties"
    /**
     * Constant for wibe properties name
     */
    private val PROPERTIES_NAME_WIBE: String = "WibeSource.properties"
    /**
     * Constant for auch properties name
     */
    private val PROPERTIES_NAME_AUCH: String = "AUCHSource.properties"
    /**
     * Constant for api url
     */
    private val API_URL: String = "api.service"

    /**
     * Method to get request properties
     *
     * @param key key service
     * @param apiService api service
     *
     * @return network params
     */
    fun getRequestProperties(key: String, apiService: ApiServiceEnum): NetworkParams? {
        return when (apiService) {
            ApiServiceEnum.WIBE -> {
                getRequestService(key, PROPERTIES_NAME_WIBE, Environment.WIBE_ENVIRONMENT.key)
            }
            ApiServiceEnum.AUCH -> {
                getRequestService(key, PROPERTIES_NAME_AUCH, Environment.AUCH_ENVIRONMENT.key)
            }
        }
    }

    /**
     * Method to get request service params
     *
     * @param key key service
     * @param fileName filename
     * @param fileUrlName file url name
     */
    private fun getRequestService(key: String, fileName: String, fileUrlName: String): NetworkParams? {
        val properties = configProperties(fileName)
        if (properties == null) {
            return null
        } else {
            val endPoint = properties.getProperty("$END_POINT_SERVICE$key")
            val methodType = properties.getProperty("$METHOD_SERVICE$key")
            val responseService = properties.getProperty("$RESPONSE_SERVICE$key")
            val api = properties.getProperty(API_URL)

            val propertiesUrl = configProperties(PROPERTIES_NAME_API_SERVICE)
            if (propertiesUrl == null) {
                return null
            } else {
                val baseUrl = propertiesUrl.getProperty("$api.$fileUrlName")

                val networkParams =  NetworkParams()
                networkParams.setBaseUrl(baseUrl)
                networkParams.setEndPoint(endPoint)
                networkParams.setMethodType(methodType)
                networkParams.setResponse(responseService)

                return networkParams
            }
        }
    }

    /**
     * Method to config properties
     *
     * @param fileName filename
     *
     * @return properties
     */
    private fun configProperties(fileName: String): Properties? {
        try {
            val properties = Properties()
            val assetManager: AssetManager = context.applicationContext.assets
            val inputStream = assetManager.open(fileName)
            properties.load(inputStream)
            return properties
        } catch (e: IOException) {
            return null
        }
    }

}