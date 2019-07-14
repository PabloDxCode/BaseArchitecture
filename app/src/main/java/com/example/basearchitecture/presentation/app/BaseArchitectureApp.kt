package com.example.basearchitecture.presentation.app

import android.app.Activity
import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.basearchitecture.di.AppInjector
import com.example.basearchitecture.di.DaggerApplicationComponent
import com.example.basearchitecture.presentation.app.config.ReadFileImpl
import com.example.basearchitecture.data.config.ConfigApp
import com.example.basearchitecture.presentation.app.config.WifiUtils
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
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
            .setReadFileConfig(ReadFileImpl(this))
            .setCheckConnection(WifiUtils(this))

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