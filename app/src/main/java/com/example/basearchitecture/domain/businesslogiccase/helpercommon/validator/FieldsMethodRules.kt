package com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator

import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType

object FieldsMethodRules {

    fun commonEmailRule(email: String): ValidationParams {
        return ValidationParams(ValidationArgs(email), ResponseErrorType.EMAIL, arrayListOf(ValidationRuleEnum.EMAIL))
    }

    fun commonPasswordRule(password: String): ValidationParams {
        return ValidationParams(ValidationArgs(password), ResponseErrorType.PASSWORD, arrayListOf(ValidationRuleEnum.PASSWORD))
    }

}