package com.example.basearchitecture.domain.businesslogiccase.common.impl

import com.example.basearchitecture.domain.businesslogiccase.common.FieldsValidationUseCase
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.*
import javax.inject.Inject

class FieldsValidationUseCaseImpl @Inject constructor() : FieldsValidationUseCase{

    private var mIsValidateField: ((ArrayList<ResponseErrorType>) -> Unit?)? = null

    private var validationErrorArray = arrayListOf<ResponseErrorType>()

    override fun isValidateField(isValidateField: (ArrayList<ResponseErrorType>) -> Unit): FieldsValidationUseCase {
        this.mIsValidateField = isValidateField
        validationErrorArray.clear()
        return this
    }

    override fun execute(validationParams: ArrayList<ValidationParams>) {
        for(items in validationParams){
            validateField(items.args, items.messageError, items.validationRule)
        }
        mIsValidateField!!.invoke(validationErrorArray)
    }

    private fun validateField(args: ValidationArgs, messageError: ResponseErrorType, validationRule: ArrayList<ValidationRuleEnum>){
        val validateFieldsUtils = ValidationFieldsHelper()
        val isValidField = validateFieldsUtils.validateFields(args,  validationRule)
        if(!isValidField){
            validationErrorArray.add(messageError)
        }
    }

}