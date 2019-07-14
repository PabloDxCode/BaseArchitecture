package com.example.basearchitecture.data.utils

import com.example.basearchitecture.data.network.ConstantsService
import java.util.HashMap

/**
 * Utils
 */
class Utils {

    companion object {

        /**
         * method that returns the necessary header for requesting
         *
         * @return headers
         */
        fun getHeaders(): Map<String, String> {
            val headers = HashMap<String, String>()
            headers[ConstantsService.HEADER_CONTENT_TYPE] = ConstantsService.HEADER_APPLICATION
            return headers
        }

    }

}