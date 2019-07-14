package com.example.basearchitecture.data.models.response

import com.example.basearchitecture.data.models.ErrorFormDto
import com.example.basearchitecture.data.models.UserStatusEnum
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * UserStatusResponse
 *
 * extends to base object response
 */
class UserStatusResponse: ErrorFormDto() {

    /**
     * Status type
     */
    @SerializedName("tipoEstatus")
    @Expose
    private var statusType: String? = null

    /**
     * Method to get status type
     *
     * @return user status enum
     */
    fun getUserStatus(): UserStatusEnum? {
        return UserStatusEnum.getStatus(statusType!!)
    }

    /**
     * Method to set status type
     *
     * @param statusType status type
     */
    fun setStatusType(statusType: String) {
        this.statusType = statusType
    }

}