package com.example.basearchitecture.data.network.enums

/**
 * MimeTypeEnum
 *
 * @param mimeType mime type of service
 */
enum class MimeTypeEnum(val mimeType: String) {

    /**
     * String for application JSON content-type
     */
    APPLICATION_JSON("application/json"),
    /**
     * String for application x-www-form content-type
     */
    FORM_URL_ENCODED("application/x-www-form-urlencoded"),
    /**
     * String for application PDF content-type
     */
    APPLICATION_PDF("application/pdf");

}