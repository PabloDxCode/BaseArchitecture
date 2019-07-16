package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.UserStatusEnum
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.data.models.response.UserStatusResponse
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.ErrorType
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.ValidFieldsHelper
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.ValidationArgs
import com.example.basearchitecture.domain.businesslogiccase.login.LoginUseCase
import com.google.gson.Gson
import javax.inject.Inject

/**
 * LoginUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class LoginUseCaseImpl @Inject constructor(val dataManager: DataManager) : LoginUseCase {

    /**
     * Email value
     */
    private var mEmail: String? = null
    /**
     * Password value
     */
    private var mPassword: String? = null
    /**
     * Success login method
     */
    private var mSuccessLogin: (() -> Unit?)? = null
    /**
     * Error inactive user method
     */
    private var mErrorInactiveUser: ((ICommonError) -> Unit?)? = null
    /**
     * Error non-exist user method
     */
    private var mErrorNonExistUser: ((ICommonError) -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((ICommonError) -> Unit?)? = null

    /**
     * Success response for login service
     *
     * @param successLogin success login method
     *
     * @return this
     */
    override fun onSuccess(successLogin: () -> Unit): LoginUseCase {
        this.mSuccessLogin = successLogin
        return this
    }

    /**
     * Error response for inactive user
     *
     * @param errorInactiveUser inactive user with common error param
     *
     * @return this
     */
    override fun onErrorInactiveUser(errorInactiveUser: (ICommonError) -> Unit): LoginUseCase {
        this.mErrorInactiveUser = errorInactiveUser
        return this
    }

    /**
     * Error response for non exist user
     *
     * @param errorNonExistUser non exist user with common error param
     *
     * @return this
     */
    override fun onErrorNonExistUser(errorNonExistUser: (ICommonError) -> Unit): LoginUseCase {
        this.mErrorNonExistUser = errorNonExistUser
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (ICommonError) -> Unit): LoginUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param email email param
     * @param password password param
     */
    override fun execute(email: String, password: String) {
        this.mEmail = email
        this.mPassword = password
        if (validateFields(email, password)) {
            doLogin()
        } else {
            mErrorResponse!!.invoke(AppError(null, "form", null))
        }
    }

    /**
     * Method to validate fields
     *
     * @param email email param
     * @param password password param
     *
     * @return boolean
     */
    fun validateFields(email: String, password: String): Boolean {
        val validateFieldsUtils = ValidFieldsHelper()
        val isValidEmail = validateFieldsUtils.validateFields(ValidationArgs(email), ErrorType.EMAIL)
        val isValidPassword = validateFieldsUtils.validateFields(ValidationArgs(password), ErrorType.NOT_NULL)
        return isValidEmail && isValidPassword
    }

    /**
     * Method to do login
     */
    fun doLogin() {
        val requestBody = Gson().toJson(UserStatusRequest(mEmail!!))

        dataManager
            .onSuccess { validateLoginResponse(it as UserStatusResponse) }
            .onError {
                val errorResponse = it as ErrorResponse
                mErrorResponse!!.invoke(AppError(null, errorResponse.getErrorFormDto()!!.getFirstMessageOfList(), null))
            }
            .onServerError { mErrorResponse!!.invoke(it) }
            .login(requestBody)
    }

    /**
     * Method to validate login response
     *
     * @param userStatusResponse user status
     */
    private fun validateLoginResponse(userStatusResponse: UserStatusResponse) {
        when (userStatusResponse.getUserStatus()) {
            UserStatusEnum.ACTIVO -> {
                mSuccessLogin!!.invoke()
            }
            UserStatusEnum.PENDIENTE_ACTIVACION -> {
                mErrorInactiveUser!!.invoke(AppError(null, "pending_status"))
            }
            UserStatusEnum.INEXISTENTE -> {
                mErrorNonExistUser!!.invoke(AppError(null, "nonexistent_status"))
            }
            else -> {
                mErrorResponse!!.invoke(AppError(null, "generic_response"))
            }
        }
    }

}