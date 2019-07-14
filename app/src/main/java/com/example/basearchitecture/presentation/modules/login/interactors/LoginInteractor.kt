package com.example.basearchitecture.presentation.modules.login.interactors

import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.domain.businesslogiccase.login.LoginUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.GenerateSessionIdUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.LoginSlodUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.LoginStatusUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.GetUserInfoUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.UpdateLoginDateUseCase
import com.example.basearchitecture.presentation.modules.login.contracts.LoginContracts
import com.example.basearchitecture.presentation.modules.login.presenters.listeners.LoginPresenterListener
import javax.inject.Inject

/**
 * LoginInteractor
 *
 * @param loginUseCase login use case instance
 * @param generateSessionIdUseCase generate session id use case instance
 * @param loginSlodUseCase login slod use case instance
 * @param loginStatusUseCase login status use case instance
 * @param getUserInfoUseCase get user info use case instance
 * @param updateLoginDateUseCase update login date use case instance
 */
class LoginInteractor @Inject constructor(
    val loginUseCase: LoginUseCase,
    val generateSessionIdUseCase: GenerateSessionIdUseCase,
    val loginSlodUseCase: LoginSlodUseCase,
    val loginStatusUseCase: LoginStatusUseCase,
    val getUserInfoUseCase: GetUserInfoUseCase,
    val updateLoginDateUseCase: UpdateLoginDateUseCase
) : LoginContracts.LoginInteractor {

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
     * Login presenter listener
     */
    private var mLoginPresenterListener: LoginPresenterListener? = null

    /**
     * Method to init interactor
     *
     * @param email email param
     * @param password password param
     * @param loginPresenterListener login presenter listener
     */
    override fun init(email: String, password: String, loginPresenterListener: LoginPresenterListener) {
        this.mEmail = email
        this.mPassword = password
        this.mLoginPresenterListener = loginPresenterListener
    }

    /**
     * Method to do login
     */
    override fun doLogin() {
        loginUseCase
            .onSuccess { generateSessionId() }
            .onErrorInactiveUser { mLoginPresenterListener!!.onErrorInactiveUser(it) }
            .onErrorNonExistUser { mLoginPresenterListener!!.onErrorNonExistUser(it) }
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(it) }
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
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(it) }
            .execute(mEmail!!)
    }

    /**
     * Method to login slod
     */
    override fun loginSlod() {
        loginSlodUseCase
            .onSuccess { loginStatus() }
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(it) }
            .execute(mEmail!!, mPassword!!)
    }

    /**
     * Method to login status
     */
    override fun loginStatus() {
        loginStatusUseCase
            .onSuccess { getUserInfo(mSessionId!!) }
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(AppError(null, "generic_response")) }
            .execute()
    }

    /**
     * Method to get user info
     *
     * @param sessionId session id param
     */
    override fun getUserInfo(sessionId: String) {
        getUserInfoUseCase
            .onSuccess {
                saveUserInfo(it.getUser()!!)
            }
            .onErrorResponse {
                mLoginPresenterListener!!.onRequestError(it)
            }
            .execute(sessionId)
    }

    /**
     * Method to save user info
     *
     * @param userInfo user info
     */
    override fun saveUserInfo(userInfo: User) {
        updateLoginDate()
    }

    /**
     * Method to update login date
     */
    override fun updateLoginDate() {
        updateLoginDateUseCase
            .onSuccess { mLoginPresenterListener!!.onSuccess() }
            .onErrorResponse { mLoginPresenterListener!!.onRequestError(it) }
            .execute(mEmail!!)
    }

}