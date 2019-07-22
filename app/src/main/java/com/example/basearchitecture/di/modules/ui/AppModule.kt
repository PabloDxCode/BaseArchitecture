package com.example.basearchitecture.di.modules.ui

import android.app.Application
import android.content.Context
import com.example.basearchitecture.data.config.IPreferences
import com.example.basearchitecture.data.config.IReadFile
import com.example.basearchitecture.ui.app.config.PreferencesConfig
import com.example.basearchitecture.ui.app.config.ReadFileConfig
import dagger.Module
import dagger.Provides

/**
 * AppModule
 */
@Module
class AppModule {

    /**
     * Method to provide app context
     *
     * @param app application instance
     *
     * @return context
     */
    @Provides
    fun provideContext(app: Application): Context = app

    /**
     * Method to provide read file config
     *
     * @param context context instance
     *
     * @return i read file
     */
    @Provides
    fun provideIReadFile(context: Context): IReadFile = ReadFileConfig(context)

    /**
     * Method to provide preferences config
     *
     * @param context context instance
     *
     * @return i preferences
     */
    @Provides
    fun provideIPreferences(context: Context): IPreferences = PreferencesConfig(context)

}