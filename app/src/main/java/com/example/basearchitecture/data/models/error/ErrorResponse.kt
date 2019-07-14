package com.example.basearchitecture.data.models.error

import com.example.basearchitecture.data.models.ErrorFormDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * ErrorResponse
 */
class ErrorResponse : ICommonError {

    /**
     * Error form dto
     */
    @SerializedName("errorFormDto")
    @Expose
    private var errorFormDto: ErrorFormDto? = null

    /**
     * Method to get error form dto
     *
     * @return error form dto
     */
    fun getErrorFormDto(): ErrorFormDto? {
        return errorFormDto
    }

    /**
     * Method to set error form dto
     *
     * @param errorFormDto error form dto
     */
    fun setErrorFormDto(errorFormDto: ErrorFormDto) {
        this.errorFormDto = errorFormDto
    }

}