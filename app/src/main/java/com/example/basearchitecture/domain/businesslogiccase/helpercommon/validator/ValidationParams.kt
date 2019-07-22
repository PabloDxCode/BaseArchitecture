package com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator

import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType

class ValidationParams(
    val args: ValidationArgs,
    val messageError: ResponseErrorType,
    var validationRule: ArrayList<ValidationRuleEnum>
)