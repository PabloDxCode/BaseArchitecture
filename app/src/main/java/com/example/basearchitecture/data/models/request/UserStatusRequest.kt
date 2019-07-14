package com.example.basearchitecture.data.models.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * UserStatusRequest
 *
 * @param user user param
 */
data class UserStatusRequest(
    @SerializedName("usuario")
    @Expose
    val user: String
)