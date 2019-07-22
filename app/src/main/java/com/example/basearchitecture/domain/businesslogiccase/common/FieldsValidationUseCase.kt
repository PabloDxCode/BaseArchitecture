package com.example.basearchitecture.domain.businesslogiccase.common

import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.ValidationParams

interface FieldsValidationUseCase {

    /**
     * Method to set internal method to manage alternative flows
     *
     * @param isValidateField internal method to manage response
     *
     * @return this
     */
    fun isValidateField(isValidateField: (ArrayList<ResponseErrorType>) -> Unit): FieldsValidationUseCase

    fun execute(validationParams: ArrayList<ValidationParams>)

}