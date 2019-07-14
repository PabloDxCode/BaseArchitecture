package com.example.basearchitecture.di.modules

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.domain.businesslogiccase.login.*
import com.example.basearchitecture.domain.businesslogiccase.login.impl.*
import dagger.Module
import dagger.Provides

/**
 * UseCasesModule
 */
@Module
class UseCasesModule {

    /**
     * Method to provide login use case
     *
     * @param dataManager data manager instance
     *
     * @return LoginUseCase
     */
    @Provides
    fun provideLoginUseCaseImpl(dataManager: DataManager): LoginUseCase = LoginUseCaseImpl(dataManager)

    /**
     * Method to provide generate session id use case
     *
     * @param dataManager data manager instance
     *
     * @return GenerateSessionIdUseCase
     */
    @Provides
    fun provideGenerateSessionIdUseCaseImpl(dataManager: DataManager): GenerateSessionIdUseCase =
        GenerateSessionIdUseCaseImpl(dataManager)

    /**
     * Method to provide login slod use case
     *
     * @param dataManager data manager instance
     *
     * @return LoginSlodUseCase
     */
    @Provides
    fun provideLoginSlodUseCaseImpl(dataManager: DataManager): LoginSlodUseCase = LoginSlodUseCaseImpl(dataManager)

    /**
     * Method to provide login status use case
     *
     * @param dataManager data manager instance
     *
     * @return LoginStatusUseCase
     */
    @Provides
    fun provideLoginStatusUseCaseImpl(dataManager: DataManager): LoginStatusUseCase =
        LoginStatusUseCaseImpl(dataManager)

    /**
     * Method to provide get user info use case
     *
     * @param dataManager data manager instance
     *
     * @return GetUserInfoUseCase
     */
    @Provides
    fun provideGetUserInfoUseCaseImpl(dataManager: DataManager): GetUserInfoUseCase =
        GetUserInfoUseCaseImpl(dataManager)

    /**
     * Method to provide update login date use case
     *
     * @param dataManager data manager instance
     *
     * @return UpdateLoginDateUseCase
     */
    @Provides
    fun provideUpdateLoginDateUseCaseImpl(dataManager: DataManager): UpdateLoginDateUseCase =
        UpdateLoginDateUseCaseImpl(dataManager)

}