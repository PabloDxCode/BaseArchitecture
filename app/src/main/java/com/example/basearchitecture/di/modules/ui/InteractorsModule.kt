package com.example.basearchitecture.di.modules.ui

import com.example.basearchitecture.domain.businesslogiccase.login.*
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
     * @param loginUseCase login use case instance
     * @param generateSessionIdUseCase generate session id use case instance
     * @param loginSlodUseCase login slod use case instance
     * @param loginStatusUseCase login status use case instance
     * @param getUserInfoUseCase get user info use case instance
     * @param updateLoginDateUseCase update login date use case instance
     * @param saveUserInfoUseCase save user info use case instance
     *
     * @return LoginContracts.LoginInteractor
     */
    @Provides
    fun provideLoginInteractor(loginUseCase: LoginUseCase, generateSessionIdUseCase: GenerateSessionIdUseCase, loginSlodUseCase: LoginSlodUseCase,
                               loginStatusUseCase: LoginStatusUseCase, getUserInfoUseCase: GetUserInfoUseCase, updateLoginDateUseCase: UpdateLoginDateUseCase,
                               saveUserInfoUseCase: SaveUserInfoUseCase
    ): LoginContracts.LoginInteractor =
        LoginInteractor(loginUseCase, generateSessionIdUseCase, loginSlodUseCase, loginStatusUseCase,
            getUserInfoUseCase, updateLoginDateUseCase, saveUserInfoUseCase)

}