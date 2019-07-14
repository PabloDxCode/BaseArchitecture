package com.example.basearchitecture.domain.businesslogiccase.login.helper

/**
 * SessionResponse
 *
 * @param errorCode error code
 * @param isSessionActive session active state
 */
data class SessionResponse(var errorCode: String? = null, var isSessionActive: Boolean = false)