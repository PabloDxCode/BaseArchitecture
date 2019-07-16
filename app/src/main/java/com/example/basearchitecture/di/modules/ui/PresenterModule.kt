package com.example.basearchitecture.di.modules.ui

import android.content.Context
import com.example.basearchitecture.ui.modules.login.contracts.LoginContracts
import com.example.basearchitecture.ui.modules.login.presenters.LoginPresenter
import dagger.Module
import dagger.Provides

/**
 * PresenterModule
 */
@Module
class PresenterModule {

    /**
     * Method to provide login presenter
     *
     * @param loginInteractor login interactor instance
     * @param context context instance
     *
     * @return LoginContracts.LoginPresenter
     */
    @Provides
    fun provideLoginPresenter(loginInteractor: LoginContracts.LoginInteractor, context: Context)
            : LoginContracts.LoginPresenter = LoginPresenter(loginInteractor, context)

}