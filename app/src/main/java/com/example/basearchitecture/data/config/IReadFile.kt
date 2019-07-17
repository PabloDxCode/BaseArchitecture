package com.example.basearchitecture.data.config

import com.example.basearchitecture.data.network.models.NetworkParams
import com.example.basearchitecture.data.network.enums.ApiServiceEnum

/**
 * IReadFile
 */
interface IReadFile {

    /**
     * Method to get request params
     *
     * @param key key service
     * @param apiService api service enum
     *
     * @return network params model
     */
    fun getRequestParams(key: String, apiService: ApiServiceEnum): NetworkParams?

}