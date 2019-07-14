package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * ErrorFormDto
 */
open class ErrorFormDto {

    /**
     * Result result operation
     */
    @SerializedName("operacionResultado")
    @Expose
    private var resultOperation: String? = null
    /**
     * Message operation list response
     */
    @SerializedName("operacionMensaje")
    @Expose
    private var messageOperation: List<String>? = null
    /**
     * Browser url
     */
    @SerializedName("browserUrl")
    @Expose
    private var browserUrl: String? = null
    /**
     * Extra message operation
     */
    @SerializedName("operacionMensajeExtra")
    @Expose
    private var extraMessageOperation: String? = null

    /**
     * Method to get result operation
     *
     * @return result operation
     */
    fun getResultOperation(): String? {
        return resultOperation
    }

    /**
     * Method to set result operation
     *
     * @param resultOperation result operation
     */
    fun setResultOperation(resultOperation: String) {
        this.resultOperation = resultOperation
    }

    /**
     * Method to get message operation list
     *
     * @return message operation list
     */
    fun getMessageOperation(): List<String>? {
        return messageOperation
    }

    /**
     * Method to get first message of list
     *
     * @return first message
     */
    fun getFirstMessageOfList(): String {
        if (messageOperation == null || messageOperation!!.isEmpty()) {
            return ""
        } else {
            return messageOperation!![0]
        }
    }

    /**
     * Method to se message operation list
     *
     * @param messageOperation message operation list
     */
    fun setMessageOperation(messageOperation: List<String>) {
        this.messageOperation = messageOperation
    }

    /**
     * Method to get browser url
     *
     * @return browser url
     */
    fun getBrowserUrl(): String? {
        return browserUrl
    }

    /**
     * Method to set browser url
     *
     * @param browserUrl browser url
     */
    fun setBrowserUrl(browserUrl: String) {
        this.browserUrl = browserUrl
    }

    /**
     * Method to get extra message operation
     *
     * @return extra message operation
     */
    fun getExtraMessageOperation(): String? {
        return extraMessageOperation
    }

    /**
     * Method to set extra message operation
     *
     * @param extraMessageOperation extra message operation
     */
    fun setExtraMessageOperation(extraMessageOperation: String) {
        this.extraMessageOperation = extraMessageOperation
    }

}