package com.example.basearchitecture.di.modules.domain

import com.example.basearchitecture.domain.businesslogiccase.common.FieldsValidationUseCase
import com.example.basearchitecture.domain.businesslogiccase.login.*
import com.example.basearchitecture.domain.businesslogiccase.login.factory.ILoginFactory
import com.example.basearchitecture.domain.businesslogiccase.login.factory.LoginFactory
import dagger.Module
import dagger.Provides

/**
 * DomainFactoriesModule
 */
@Module
class DomainFactoriesModule {

    /**
     * Method to provide login factory module
     *
     * @param loginUseCase login use case instance
     * @param generateSessionIdUseCase generate session id use case instance
     * @param loginSlodUseCase login slod use case instance
     * @param loginStatusUseCase login status use case instance
     * @param getUserInfoUseCase get user info use case instance
     * @param updateLoginDateUseCase update login date use case instance
     * @param saveUserInfoUseCase save user info use case instance
     * @param saveEmailUseCase save email use case instance
     * @param getEmailUseCase get email use case instance
     *
     * @return ILoginFactory
     */
    @Provides
    fun provideLoginFactoryModule(fieldsValidationUseCase: FieldsValidationUseCase, loginUseCase: LoginUseCase,
                                  generateSessionIdUseCase: GenerateSessionIdUseCase, loginSlodUseCase: LoginSlodUseCase,
                                  loginStatusUseCase: LoginStatusUseCase, getUserInfoUseCase: GetUserInfoUseCase,
                                  updateLoginDateUseCase: UpdateLoginDateUseCase, saveUserInfoUseCase: SaveUserInfoUseCase,
                                  saveEmailUseCase: SaveEmailUseCase, getEmailUseCase: GetEmailUseCase
    ): ILoginFactory = LoginFactory(fieldsValidationUseCase, loginUseCase, generateSessionIdUseCase, loginSlodUseCase,
        loginStatusUseCase, getUserInfoUseCase, updateLoginDateUseCase, saveUserInfoUseCase, saveEmailUseCase, getEmailUseCase)

}