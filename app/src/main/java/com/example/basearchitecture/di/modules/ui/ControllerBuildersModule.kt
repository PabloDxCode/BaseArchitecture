package com.example.basearchitecture.di.modules.ui

import com.example.basearchitecture.ui.modules.login.controller.LoginController
import com.example.basearchitecture.ui.modules.main.controllers.MainController
import com.example.basearchitecture.ui.modules.splash.controller.SplashController
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * ControllerBuildersModule
 */
@Module
abstract class ControllerBuildersModule {

    /**
     * Method to build splash controller to inject
     */
    @ContributesAndroidInjector
    abstract fun buildSplashController() : SplashController

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