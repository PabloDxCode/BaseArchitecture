package com.example.basearchitecture.data.network

/**
 * ConstantsService
 */
class ConstantsService {

    companion object {

        /**
         * Constant for base url
         */
        const val BASE_URL = "https://wibe.com"
        /**
         * Constant for network tag
         */
        const val NETWORK_TAG = "Network"
        /**
         * Constant for content type
         */
        const val HEADER_CONTENT_TYPE = "Content-Type"
        /**
         * Constant for application json
         */
        const val HEADER_APPLICATION = "application/json"

        //------------- Services code
        /**
         * Constant for login service code
         */
        const val LOGIN_SERVICE_CODE = "Login"
        /**
         * Constant for generate session id service code
         */
        const val GENERATE_SESSION_ID_SERVICE_CODE = "GenerateSessionId"
        /**
         * Constant for login slod service code
         */
        const val LOGIN_SLOD_SERVICE_CODE = "DFServlet"
        /**
         * Constant for login status service code
         */
        const val LOGIN_STATUS_SERVICE_CODE = "LoginStatus"
        /**
         * Constant for get user info service code
         */
        const val GET_USER_INFO_SERVICE_CODE = "GetUserInformation"
        /**
         * Constant for update login date service code
         */
        const val UPDATE_LOGIN_DATE_SERVICE_CODE = "UpdateLoginDate"

    }

}