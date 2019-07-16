package com.example.basearchitecture.di.component

import android.app.Application
import com.example.basearchitecture.di.modules.data.DataManagerModule
import com.example.basearchitecture.di.modules.data.DatabaseModule
import com.example.basearchitecture.di.modules.data.RepositoryModule
import com.example.basearchitecture.di.modules.domain.UseCasesModule
import com.example.basearchitecture.di.modules.ui.*
import com.example.basearchitecture.ui.app.BaseArchitectureApp
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
        RepositoryModule::class,
        DatabaseModule::class
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