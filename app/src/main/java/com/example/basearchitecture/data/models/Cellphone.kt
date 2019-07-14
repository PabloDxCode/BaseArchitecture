package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Cellphone
 */
class Cellphone {

    /**
     * Extension value
     */
    @SerializedName("extension")
    @Expose
    private var extension: String? = null
    /**
     * Phone number value
     */
    @SerializedName("numeroTelefono")
    @Expose
    private var phoneNumber: String? = null

    /**
     * Method to get extension value
     *
     * @return extension value
     */
    fun getExtension(): String? {
        return extension
    }

    /**
     * Method to set extension value
     *
     * @param extension extension value
     */
    fun setExtension(extension: String) {
        this.extension = extension
    }

    /**
     * Method to get phone number value
     *
     * @return phone number value
     */
    fun getPhoneNumber(): String? {
        return phoneNumber
    }

    /**
     * Method to set phone number value
     *
     * @param phoneNumber phone number value
     */
    fun setPhoneNumber(phoneNumber: String) {
        this.phoneNumber = phoneNumber
    }

}