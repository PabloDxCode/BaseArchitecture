package com.example.basearchitecture.domain.businesslogiccase.enums

/**
 * ResponseErrorType
 */
enum class ResponseErrorType {

    GENERIC_RESPONSE,
    SESSION,
    NON_EXIST,
    ACTIVATE,
    INACTIVO,
    EAI0000,
    EAI0001,
    EAI0002,
    EAI0003,
    EAI0004,
    EAI0005,
    EAI0006,
    EAI0007,
    EAI0008,
    EAI0009,
    EAI00010,
    EAI00011,
    EAI00012,
    EAI00013,
    EAI00014,
    EAI00015,
    EAI00016,
    EAI00017,
    EAI00018,
    EAI00019,
    EAI00020,
    EAI00021,
    EAI00022,
    EAI00023,
    EAI00024,
    EAI00025,
    EAI00026,
    EAI00027,
    EAI00028,
    EAI00029,
    EAI00030,
    EAI00031,
    EAI00032,
    EAI00033,
    EMAIL,
    PASSWORD;

    companion object {

        /**
         * Method to filter error response to enum value
         *
         * @param error error param
         *
         * @return enum value converted to string
         */
        fun filterError(error: String): String? {
            for (item in ResponseErrorType.values()) {
                if (error.contains(item.toString())) {
                    return item.toString().toLowerCase()
                }
            }
            return null
        }

    }

}