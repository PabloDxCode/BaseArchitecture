package com.example.basearchitecture.domain.businesslogiccase.login.impl

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.domain.businesslogiccase.login.GetEmailUseCase
import javax.inject.Inject

/**
 * GetEmailUseCaseImpl
 *
 * @param dataManager data manager instance
 */
class GetEmailUseCaseImpl @Inject constructor(val dataManager: DataManager): GetEmailUseCase {

    /**
     * Method to response email saved
     */
    private var mEmailSaved: ((String) -> Unit?)? = null

    /**
     * Success response
     *
     * @param emailSaved param method to get email saved
     *
     * @return this
     */
    override fun onSuccess(emailSaved: (String) -> Unit): GetEmailUseCase {
        this.mEmailSaved = emailSaved
        return  this
    }

    /**
     * Execute method
     */
    override fun execute() {
        dataManager.getPreferencesRepository()
            .init("login_pref")
            .onSuccess {mEmailSaved!!.invoke(it.toString())}
            .getString("email")
    }

}