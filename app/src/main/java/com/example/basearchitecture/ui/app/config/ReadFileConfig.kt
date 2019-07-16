package com.example.basearchitecture.ui.app.config

import android.content.Context
import com.example.basearchitecture.data.network.NetworkParams
import com.example.basearchitecture.data.config.IReadFile
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.ui.helpers.ReaderPropertiesHelper

/**
 * ReadFileConfig
 *
 * @param context context instance
 */
class ReadFileConfig(val context: Context) : IReadFile {

    /**
     * Method to get request params
     *
     * @param key key service
     * @param apiService api service enum
     *
     * @return network params model
     */
    override fun getRequestParams(key: String, apiService: ApiServiceEnum): NetworkParams? {
        val readerPropertiesHelper = ReaderPropertiesHelper(context)
        return readerPropertiesHelper.getRequestProperties(key, apiService)
    }

}