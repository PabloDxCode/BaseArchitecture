package com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator

import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType

/**
 * ValidationParams
 *
 * @param args arguments to validate
 * @param messageError enum message error
 * @param validationRule array of rules to validate field
 */
class ValidationParams(
    val args: ValidationArgs,
    val messageError: ResponseErrorType,
    var validationRule: ArrayList<ValidationRuleEnum>
)