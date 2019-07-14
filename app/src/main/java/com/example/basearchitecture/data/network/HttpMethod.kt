package com.example.basearchitecture.data.network

/**
 * HttpMethod
 */
enum class HttpMethod {

    GET,
    POST;

    companion object {

        /**
         * Method to find method selected
         *
         * @param method string method
         *
         * @return HttpMethod
         */
        fun findMethodSelected(method: String): HttpMethod {
            for (item in HttpMethod.values()) {
                if (item.toString() == method) {
                    return item
                }
            }
            return GET
        }

    }

}