package com.example.basearchitecture.di.modules.ui

import com.example.basearchitecture.ui.modules.components.ModalRequestPermissions
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
     * Method to build splash controller for inject
     *
     * @return SplashController
     */
    @ContributesAndroidInjector
    abstract fun buildSplashController() : SplashController

    /**
     * Method to build modal request permissions for inject
     *
     * @return ModalRequestPermissions
     */
    @ContributesAndroidInjector
    abstract fun buildModalRequestPermissions() : ModalRequestPermissions

    /**
     * Method to build login controller for inject
     *
     * @return LoginController
     */
    @ContributesAndroidInjector
    abstract fun buildLoginController() : LoginController

    /**
     * Method to build main controller for inject
     *
     * @return MainController
     */
    @ContributesAndroidInjector
    abstract fun buildMainController() : MainController

}