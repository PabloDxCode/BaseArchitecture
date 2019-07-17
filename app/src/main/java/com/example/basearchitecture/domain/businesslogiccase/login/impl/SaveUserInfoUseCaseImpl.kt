package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.models.User
import com.example.basearchitecture.data.models.error.IAppError
import com.example.basearchitecture.domain.businesslogiccase.login.SaveUserInfoUseCase
import javax.inject.Inject

/**
 * LoginUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class SaveUserInfoUseCaseImpl @Inject constructor(val dataManager: DataManager): SaveUserInfoUseCase {

    /**
     * Success method for save info
     */
    private var mSuccessInfoSaved: (() -> Unit?)? = null
    /**
     * Error response method
     */
    private var mErrorResponse: ((IAppError) -> Unit?)? = null

    /**
     * Success response
     *
     * @param successInfoSaved success method for save info
     *
     * @return this
     */
    override fun onSuccess(successInfoSaved: () -> Unit): SaveUserInfoUseCase {
        this.mSuccessInfoSaved = successInfoSaved
        return this
    }

    /**
     * Error response
     *
     * @param errorResponse common error response
     *
     * @return this
     */
    override fun onErrorResponse(errorResponse: (IAppError) -> Unit): SaveUserInfoUseCase {
        this.mErrorResponse = errorResponse
        return this
    }

    /**
     * Execute method
     *
     * @param userInfo user information
     */
    override fun execute(userInfo: User) {
        mSuccessInfoSaved!!.invoke()
    }

}