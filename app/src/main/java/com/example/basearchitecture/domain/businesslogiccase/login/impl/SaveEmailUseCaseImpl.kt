package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.domain.businesslogiccase.login.SaveEmailUseCase
import javax.inject.Inject

/**
 * SaveEmailUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class SaveEmailUseCaseImpl @Inject constructor(val dataManager: DataManager): SaveEmailUseCase {

    /**
     * Method to response when email is saved
     */
    private var mIsEmailSaved: (() -> Unit?)? = null

    /**
     * Success response
     *
     * @param isEmailSaved is email saved response method
     *
     * @return this
     */
    override fun onSuccess(isEmailSaved: () -> Unit): SaveEmailUseCase {
        this.mIsEmailSaved = isEmailSaved
        return this
    }

    /**
     * Execute method
     *
     * @param email email param
     */
    override fun execute(email: String) {
        dataManager.getPreferencesRepository()
            .init("login_pref")
            .onSuccess {mIsEmailSaved!!.invoke()}
            .saveString("email", email)
    }

}