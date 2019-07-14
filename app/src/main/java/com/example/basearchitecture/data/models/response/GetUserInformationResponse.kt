package com.example.basearchitecture.data.models.response

import com.example.basearchitecture.data.models.ErrorFormDto
import com.example.basearchitecture.data.models.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * GetUserInformationResponse
 *
 * extends to base object response
 */
class GetUserInformationResponse : ErrorFormDto() {

    /**
     * User object
     */
    @SerializedName("usuario")
    @Expose
    private var user: User? = null

    /**
     * Method to get user object
     *
     * @return user object
     */
    fun getUser(): User? {
        return user
    }

    /**
     * Method to set user object
     *
     * @param user user object
     */
    fun setUser(user: User) {
        this.user = user
    }
}