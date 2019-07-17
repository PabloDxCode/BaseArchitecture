package com.example.basearchitecture.data.models.error

/**
 * AppError
 *
 * @param title error title
 * @param message error message
 * @param messageDebug message to show in debug console
 */
data class AppError(val title: String?, val message: String?, val messageDebug: String? = null) : IAppError