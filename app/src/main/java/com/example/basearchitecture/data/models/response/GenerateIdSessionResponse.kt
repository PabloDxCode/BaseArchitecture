package com.example.basearchitecture.data.models.response

import com.example.basearchitecture.data.models.ErrorFormDto
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * GenerateIdSessionResponse
 *
 * extends to base object response
 */
class GenerateIdSessionResponse : ErrorFormDto() {

    /**
     * Wibe id session
     */
    @SerializedName("idSesionWibe")
    @Expose
    private var sessionId: String? = null

    /**
     * Method to get session id
     *
     * @return session id
     */
    fun getSessionId(): String? {
        return sessionId
    }

    /**
     * Method to set session id
     *
     * @param sessionId session id
     */
    fun setIdSesionWibe(sessionId: String) {
        this.sessionId = sessionId
    }

}