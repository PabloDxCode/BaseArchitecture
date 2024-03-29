package com.example.basearchitecture.ui.app

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.basearchitecture.di.component.AppInjector
import com.example.basearchitecture.ui.app.config.ReadFileConfig
import com.example.basearchitecture.data.config.ConfigApp
import com.example.basearchitecture.di.component.DaggerApplicationComponent
import com.example.basearchitecture.ui.app.config.EnvironmentSelectedConfig
import com.example.basearchitecture.ui.app.config.WifiConfig
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * BaseArchitectureApp
 */
class BaseArchitectureApp : MultiDexApplication(), HasActivityInjector {

    /**
     * Activity injector instance
     */
    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    /**
     * attachBaseContext
     *
     * @param base context instance
     */
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

    /**
     * onCreate
     */
    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
        AppInjector.init(this)

        ConfigApp.ourInstance
            .setCheckConnection(WifiConfig(this))
            .setEnvironmentSelected(EnvironmentSelectedConfig())

        Timber.plant(Timber.DebugTree())

        instance = this
    }

    companion object {

        /**
         * Application object
         */
        var instance: BaseArchitectureApp? = null

    }

    /**
     * activityInjector
     *
     * @return AndroidInjector<Activity>
     */
    override fun activityInjector(): AndroidInjector<Activity> = mActivityInjector

}