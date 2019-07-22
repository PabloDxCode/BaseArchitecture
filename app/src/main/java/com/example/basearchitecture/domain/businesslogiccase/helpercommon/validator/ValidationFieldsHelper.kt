package com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * ValidationFieldsHelper
 */
class ValidationFieldsHelper {

    /**
     * Method to validate fields
     *
     * @param args arguments to validate
     * @param validationRules error types
     *
     * @return state of validation
     */
    fun validateFields(args: ValidationArgs, validationRules: ArrayList<ValidationRuleEnum>): Boolean {
        var isValidField = true
        for (item in validationRules) {
            if (!validateErrorType(args, item)) {
                isValidField = false
                break
            }
        }
        return isValidField
    }

    /**
     * Method to validate error type
     *
     * @param args arguments to validate
     * @param errorTypes error types
     *
     * @return boolean
     */
    private fun validateErrorType(args: ValidationArgs, validationRule: ValidationRuleEnum): Boolean {
        when (validationRule) {
            ValidationRuleEnum.NOT_NULL -> {
                return ValidationMethods.isValidNullOrEmpty(args.param.toString())
            }
            ValidationRuleEnum.EMAIL -> {
                return ValidationMethods.isValidateEmailAddress(args.param.toString())
            }
            ValidationRuleEnum.PASSWORD -> {
                return ValidationMethods.isValidPassword(args.param.toString())
            }
            ValidationRuleEnum.MAX_LENGTH -> {
                return ValidationMethods.isValidMinLength(args.param.toString(), args.minLength)
            }
            ValidationRuleEnum.MIN_LENGTH -> {
                return ValidationMethods.isValidMaxLength(args.param.toString(), args.maxLength)
            }
            ValidationRuleEnum.COMPARE -> {
                return ValidationMethods.compareStrings(args.param.toString(), args.otherParam.toString())
            }
        }
    }

    /**
     * ValidationMethods
     */
    class ValidationMethods {

        companion object {

            /**
             * Pattern object
             */
            private var pattern: Pattern? = null
            /**
             * Matcher object
             */
            private var matcher: Matcher? = null
            /**
             * Email regex value
             */
            private val EMAIL_REGEX =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
            /**
             * Password regex value
             */
            private val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$"

            /**
             * Method to know if value is null or empty
             *
             * @param value value
             *
             * @return boolean
             */
            fun isValidNullOrEmpty(value: String?): Boolean {
                return value != null && value.isNotEmpty()
            }

            /**
             * Method to evaluate max length
             *
             * @param value value
             * @param maxLength max length
             *
             * @return boolean
             */
            fun isValidMaxLength(value: String, maxLength: Int): Boolean {
                return value.length <= maxLength
            }

            /**
             * Method to evaluate min length
             *
             * @param value value
             * @param minLength min length
             *
             * @return boolean
             */
            fun isValidMinLength(value: String, minLength: Int): Boolean {
                return value.length >= minLength
            }

            /**
             * Method to evaluate email address
             *
             * @param email email to evaluate
             *
             * @return boolean
             */
            fun isValidateEmailAddress(email: String): Boolean {
                pattern = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE)
                matcher = pattern!!.matcher(email)
                return matcher!!.matches()
            }

            /**
             * Method to evaluate password
             *
             * @param password password to evaluate
             *
             * @return boolean
             */
            fun isValidPassword(password: String): Boolean {
                pattern = Pattern.compile(PASSWORD_REGEX, Pattern.CASE_INSENSITIVE)
                matcher = pattern!!.matcher(password)
                return matcher!!.matches()
            }

            /**
             * Method to compare to values
             *
             * @param firstValue first value
             * @param secondValue second value
             *
             * @return boolean
             */
            fun compareStrings(firstValue: Any, secondValue: Any): Boolean {
                return firstValue == secondValue
            }
        }

    }

}