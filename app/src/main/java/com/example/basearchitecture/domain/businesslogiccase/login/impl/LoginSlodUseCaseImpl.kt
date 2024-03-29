package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.domain.businesslogiccase.enums.ResponseErrorType
import com.example.basearchitecture.domain.businesslogiccase.login.LoginSlodUseCase
import javax.inject.Inject

/**
 * LoginSlodUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class LoginSlodUseCaseImpl @Inject constructor(val dataManager: DataManager) : LoginSlodUseCase {

    /**
     * Success session started method
     */
    private var mIsSessionStarted: (() -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

    /**
     * Success response
     *
     * @param isSessionStarted is session started response method
     *
     * @return this
     */
    override fun onSuccess(isSessionStarted: () -> Unit): LoginSlodUseCase {
        this.mIsSessionStarted = isSessionStarted
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): LoginSlodUseCase {
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
        val params = HashMap<String, String>()
        params["eai_user"] = "$email-w"
        params["idWibe"] = ""
        params["eai_password"] = password
        params["origen"] = "wibe"
        params["eai_URLDestino"] =
                "https://qa.wibe.com.mx/mwib_mult_web_mwibprivatewebapp_01/api/loginStatus&idioma=CAS"

        dataManager.getWibeRepository()
            .setParams(params)
            .onSuccess { response, _ -> manageResponse(response.toString()) }
            .onError { _, _ ->
                mErrorResponse!!.invoke(AppError(null, ResponseErrorType.SESSION.toString()))
            }
            .onServerError { iAppError, _ ->
                mErrorResponse!!.invoke(iAppError)
            }
            .invokeService(ConstantsService.LOGIN_SLOD_SERVICE_CODE)
    }

    /**
     * Method to manage success response
     *
     * @return response service
     */
    private fun manageResponse(response: String) {
        val codeError = ResponseErrorType.filterError(response)
        if(codeError == null){
            mIsSessionStarted!!.invoke()
        } else {
            mErrorResponse!!.invoke(AppError(null, codeError))
        }
    }

}