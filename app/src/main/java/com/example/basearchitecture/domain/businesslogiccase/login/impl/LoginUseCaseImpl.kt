package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.UserStatusEnum
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.ErrorResponse
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.models.request.UserStatusRequest
import com.example.basearchitecture.data.models.response.UserStatusResponse
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
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
     * Error pending status user method
     */
    private var mErrorPendingStatusUser: (() -> Unit?)? = null
    /**
     * Error non-exist user method
     */
    private var mErrorNonExistUser: ((IAppError) -> Unit?)? = null
    /**
     * Error inactive user method
     */
    private var mErrorInactiveUser: (() -> Unit?)? = null
    /**
     * Error locked user method
     */
    private var mErrorLockedUser: ((IAppError) -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

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
     * Error response for pending status user
     *
     * @param errorPendingStatusUser pending status user with common error param
     *
     * @return this
     */
    override fun onErrorPendingStatusUser(errorPendingStatusUser: () -> Unit): LoginUseCase {
        this.mErrorPendingStatusUser = errorPendingStatusUser
        return this
    }

    /**
     * Error response for inactive user
     *
     * @param errorInactiveUser unit method for inactive user error
     *
     * @return this
     */
    override fun onErrorInactiveUser(errorInactiveUser: () -> Unit): LoginUseCase {
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
    override fun onErrorNonExistUser(errorNonExistUser: (IAppError) -> Unit): LoginUseCase {
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
    override fun onErrorLockedUser(errorLockedUser: (IAppError) -> Unit): LoginUseCase {
        this.mErrorLockedUser = errorLockedUser
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): LoginUseCase {
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

        dataManager.getWibeRepository()
            .setRequestBody(requestBody)
            .setSuccessResponse(UserStatusResponse::class.java)
            .setErrorResponse(ErrorResponse::class.java)
            .onSuccess { validateLoginResponse(it as UserStatusResponse) }
            .onError {
                val errorResponse = it as ErrorResponse
                mErrorResponse!!.invoke(AppError(null, errorResponse.getErrorFormDto()!!.getFirstMessageOfList(), null))
            }
            .onServerError { mErrorResponse!!.invoke(it) }
            .invokeService(ConstantsService.LOGIN_SERVICE_CODE)
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
                mErrorPendingStatusUser!!.invoke()
            }
            UserStatusEnum.INEXISTENTE -> {
                mErrorNonExistUser!!.invoke(AppError(null, ResponseErrorType.NON_EXIST.toString()))
            }
            UserStatusEnum.INACTIVO ->{
                mErrorInactiveUser!!.invoke()
            }
            UserStatusEnum.BLOQUEADO ->{
                mErrorLockedUser!!.invoke(AppError(null, ResponseErrorType.EAI0008.toString()))
            }
            else -> {
                mErrorResponse!!.invoke(AppError(null, ResponseErrorType.GENERIC_RESPONSE.toString()))
            }
        }
    }

}