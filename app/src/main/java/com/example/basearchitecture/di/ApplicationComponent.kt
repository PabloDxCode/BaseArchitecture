package com.example.basearchitecture.di

import android.app.Application
import com.example.basearchitecture.di.modules.*
import com.example.basearchitecture.presentation.app.BaseArchitectureApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * ApplicationComponent
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ControllerBuildersModule::class,
        FragmentBuildersModule::class,
        PresenterModule::class,
        InteractorsModule::class,
        UseCasesModule::class,
        DataManagerModule::class,
        RepositoryModule::class
    ]
)
interface ApplicationComponent {

    /**
     * Method to inject application
     *
     * @param app app instance
     */
    fun inject(app: BaseArchitectureApp)

    /**
     * Builder interface
     */
    @Component.Builder
    interface Builder {

        /**
         * application
         *
         * @return builder
         */
        @BindsInstance
        fun application(app: Application): Builder

        /**
         * build
         *
         * @return ApplicationComponent
         */
        fun build(): ApplicationComponent

    }

}