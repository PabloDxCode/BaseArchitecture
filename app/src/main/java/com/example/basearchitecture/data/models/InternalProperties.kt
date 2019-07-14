package com.example.basearchitecture.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * InternalProperties
 */
class InternalProperties {

    /**
     * Internal code value
     */
    @SerializedName("codigoInterno")
    @Expose
    private var internalCode: String? = null
    /**
     * Description value
     */
    @SerializedName("descripcion")
    @Expose
    private var description: String? = null

    /**
     * Method to get internal code value
     *
     * @return internal code value
     */
    fun getInternalCode(): String? {
        return internalCode
    }

    /**
     * Method to set internal code value
     *
     * @param internalCode internal code value
     */
    fun setInternalCode(internalCode: String) {
        this.internalCode = internalCode
    }

    /**
     * Method to get description value
     *
     * @return description value
     */
    fun getDescription(): String? {
        return description
    }

    /**
     * Method to set description value
     *
     * @param description description value
     */
    fun setDescription(description: String) {
        this.description = description
    }

}