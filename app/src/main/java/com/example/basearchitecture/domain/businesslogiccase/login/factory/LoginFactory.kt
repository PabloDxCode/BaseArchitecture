package com.example.basearchitecture.domain.businesslogiccase.login.factory

import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.domain.businesslogiccase.common.FieldsValidationUseCase
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.FieldsMethodRules
import com.example.basearchitecture.domain.businesslogiccase.helpercommon.validator.ValidationParams
import com.example.basearchitecture.domain.businesslogiccase.login.*
import javax.inject.Inject

/**
 * LoginFactory
 *
 * @param fieldsValidationUseCase fields validation use case instance
 * @param loginUseCase login use case instance
 * @param generateSessionIdUseCase generate session id use case instance
 * @param loginSlodUseCase login slod use case instance
 * @param loginStatusUseCase login status use case instance
 * @param getUserInfoUseCase get user info use case instance
 * @param updateLoginDateUseCase update login date use case instance
 * @param saveEmailUseCase save email use case instance
 * @param getEmailUseCase get email use case instance
 */
class LoginFactory @Inject constructor(
    val fieldsValidationUseCase: FieldsValidationUseCase,
    val loginUseCase: LoginUseCase,
    val generateSessionIdUseCase: GenerateSessionIdUseCase,
    val loginSlodUseCase: LoginSlodUseCase,
    val loginStatusUseCase: LoginStatusUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val updateLoginDateUseCase: UpdateLoginDateUseCase,
    val saveUserInfoUseCase: SaveUserInfoUseCase,
    val saveEmailUseCase: SaveEmailUseCase,
    val getEmailUseCase: GetEmailUseCase
) : ILoginFactory {

    /**
     * Email value
     */
    private var mEmail: String? = null
    /**
     * Password value
     */
    private var mPassword: String? = null
    /**
     * Session id value
     */
    private var mSessionId: String? = null
    /**
     * Success getting email method
     */
    private var mSuccessGetEmail: ((String) -> Unit?)? = null
    /**
     * Validation fields method
     */
    private var mIsValidFields: (((ArrayList<ResponseErrorType>)) -> Unit?)? = null
    /**
     * Success login method
     */
    private var mSuccessLogin: (() -> Unit?)? = null
    /**
     * Error pending status user method
     */
    private var mErrorPendingStatusUser: (() -> Unit?)? = null
    /**
     * Error inactive user method
     */
    private var mErrorInactiveUser: (() -> Unit?)? = null
    /**
     * Error non-exist user method
     */
    private var mErrorNonExistUser: ((IAppError) -> Unit?)? = null
    /**
     * Error locked user method
     */
    private var mErrorLockedUser: ((IAppError) -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

    /**
     * Method to init interactor
     *
     * @param email email param
     * @param password password param
     *
     * @return this
     */
    override fun init(email: String, password: String): ILoginFactory {
        this.mEmail = email
        this.mPassword = password
        return this
    }

    /**
     * Method to manage fields validation
     *
     * @param isValidFields method to response fields validation
     *
     * @return this
     */
    override fun isValidFields(isValidFields: (ArrayList<ResponseErrorType>) -> Unit): ILoginFactory {
        this.mIsValidFields = isValidFields
        return this
    }

    /**
     * Success response for email saved
     *
     * @param successGetEmail success getting email method
     *
     * @return this
     */
    override fun onSuccessGettingEmail(successGetEmail: (String) -> Unit): ILoginFactory {
        this.mSuccessGetEmail = successGetEmail
        return this
    }

    /**
     * Success response for login service
     *
     * @param successLogin success login method
     *
     * @return this
     */
    override fun onSuccess(successLogin: () -> Unit): ILoginFactory {
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
    override fun onErrorPendingStatusUser(errorPendingStatusUser: () -> Unit): ILoginFactory {
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
    override fun onErrorInactiveUser(errorInactiveUser: () -> Unit): ILoginFactory {
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
    override fun onErrorNonExistUser(errorNonExistUser: (IAppError) -> Unit): ILoginFactory {
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
    override fun onErrorLockedUser(errorLockedUser: (IAppError) -> Unit): ILoginFactory {
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
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): ILoginFactory {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Method to validate fields
     */
    override fun validateFields() {
        val validatorsList = arrayListOf<ValidationParams>()
        validatorsList.add(FieldsMethodRules.commonEmailRule(mEmail!!))
        validatorsList.add(FieldsMethodRules.commonPasswordRule(mPassword!!))

        fieldsValidationUseCase
            .isValidateField {
                if (it.isEmpty()) {
                    doLogin()
                } else {
                    mIsValidFields!!.invoke(it)
                }
            }
            .execute(validatorsList)
    }

    /**
     * Method to do login
     */
    override fun doLogin() {
        loginUseCase
            .onSuccess { generateSessionId() }
            .onErrorInactiveUser { mErrorInactiveUser!!.invoke() }
            .onErrorNonExistUser { mErrorNonExistUser!!.invoke(it) }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(mEmail!!, mPassword!!)
    }

    /**
     * Method to generate session id
     */
    override fun generateSessionId() {
        generateSessionIdUseCase
            .onSuccess {
                mSessionId = it
                loginSlod()
            }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(mEmail!!)
    }

    /**
     * Method to login slod
     */
    override fun loginSlod() {
        loginSlodUseCase
            .onSuccess { loginStatus() }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(mEmail!!, mPassword!!)
    }

    /**
     * Method to login status
     */
    override fun loginStatus() {
        loginStatusUseCase
            .onSuccess { getUserInfo(mSessionId!!) }
            .onErrorResponse { mErrorResponse!!.invoke(AppError(null, "generic_response")) }
            .execute()
    }

    /**
     * Method to get user info
     *
     * @param sessionId session id param
     */
    override fun getUserInfo(sessionId: String) {
        getUserInfoUseCase
            .onSuccess { saveUserInfo(it.getUser()!!) }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(sessionId)
    }

    /**
     * Method to save user info
     *
     * @param userInfo user info
     */
    override fun saveUserInfo(userInfo: User) {
        saveUserInfoUseCase.onSuccess { updateLoginDate() }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(userInfo)
    }

    /**
     * Method to update login date
     */
    override fun updateLoginDate() {
        updateLoginDateUseCase
            .onSuccess { saveEmail() }
            .onErrorResponse { mErrorResponse!!.invoke(it) }
            .execute(mEmail!!)
    }

    /**
     * Method to save email in preferences
     */
    override fun saveEmail() {
        saveEmailUseCase
            .onSuccess { mSuccessLogin!!.invoke() }
            .execute(mEmail!!)
    }

    /**
     * Method to get email from preferences
     */
    override fun getEmail() {
        getEmailUseCase
            .onSuccess { mSuccessGetEmail!!.invoke(it)}
            .execute()
    }

}