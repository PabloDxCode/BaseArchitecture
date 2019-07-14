package com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator

/**
 * ValidationArgs
 *
 * @param param param to evaluate
 * @param minLength min length
 * @param maxLength max length
 * @param otherParam other param to evaluate
 */
data class ValidationArgs(
    val param: Any,
    val minLength: Int = -1,
    val maxLength: Int = -1,
    val otherParam: Any? = null
)