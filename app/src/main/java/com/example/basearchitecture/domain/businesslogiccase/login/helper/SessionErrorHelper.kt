package com.example.basearchitecture.domain.businesslogiccase.login.helper

/**
 * SessionErrorHelper
 */
class SessionErrorHelper {

    companion object {

        /**
         * Constant for error prefix
         */
        private val KNOWN_ERROR_PREFIX = "<!-- {\"ERROR\":\""
        /**
         * Constant for error sufix
         */
        private val KNOWN_ERROR_SUFIX = "\"} -->"
        /**
         * Constant for session flag prefix
         */
        private val KNOWN_SESSION_FLAG_PREFIX = "<!-- {\"session\":"
        /**
         * Constant for session flag sufix
         */
        private val KNOWN_SESSION_FLAG_SUFIX = "} -->"

        /**
         * Method to get object with response params
         *
         * @param response response service
         *
         * @return session response object
         */
        fun getSessionResponse(response: String): SessionResponse {
            if (response.contains(KNOWN_ERROR_PREFIX)) {
                val errorCodeStartIndex = response.indexOf(KNOWN_ERROR_PREFIX) + KNOWN_ERROR_PREFIX.length
                val errorCodeEndIndex = response.indexOf(KNOWN_ERROR_SUFIX, errorCodeStartIndex)
                val errorCode = response.substring(errorCodeStartIndex, errorCodeEndIndex)
                return SessionResponse(errorCode)
            } else {
                if (response.contains(KNOWN_SESSION_FLAG_PREFIX)) {
                    val sessionStartIndex =
                        response.indexOf(KNOWN_SESSION_FLAG_PREFIX) + KNOWN_SESSION_FLAG_PREFIX.length
                    val sessionEndIndex = response.indexOf(KNOWN_SESSION_FLAG_SUFIX, sessionStartIndex)
                    val sessionFlag = response.substring(sessionStartIndex, sessionEndIndex)
                    return SessionResponse(
                        null, sessionFlag.toBoolean()
                    )
                } else {
                    return SessionResponse(null, true)
                }
            }
        }

    }

}