package com.example.basearchitecture.di.modules

import com.example.basearchitecture.presentation.modules.login.controllers.LoginController
import com.example.basearchitecture.presentation.modules.main.controllers.MainController
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ControllerBuildersModule
 */
@Module
abstract class ControllerBuildersModule {

    /**
     * Method to build login controller to inject
     */
    @ContributesAndroidInjector
    abstract fun buildLoginController() : LoginController

    /**
     * Method to build main controller to inject
     */
    @ContributesAndroidInjector
    abstract fun buildMainController() : MainController

}