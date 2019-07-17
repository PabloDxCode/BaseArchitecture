package com.example.basearchitecture.di.modules.ui

import com.example.basearchitecture.domain.businesslogiccase.login.factory.ILoginFactory
import com.example.basearchitecture.ui.modules.login.contracts.LoginContracts
import com.example.basearchitecture.ui.modules.login.interactors.LoginInteractor
import dagger.Module
import dagger.Provides

/**
 * InteractorsModule
 */
@Module
class InteractorsModule {

    /**
     * Method to provide login interactor
     *
     * @param loginFactory login factory instance
     *
     * @return LoginContracts.LoginInteractor
     */
    @Provides
    fun provideLoginInteractor(loginFactory: ILoginFactory): LoginContracts.LoginInteractor =
        LoginInteractor(loginFactory)

}