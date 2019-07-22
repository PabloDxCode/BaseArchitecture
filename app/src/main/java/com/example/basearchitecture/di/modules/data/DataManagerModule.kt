package com.example.basearchitecture.di.modules.data

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.manager.DataManagerImpl
import com.example.basearchitecture.data.repositories.BaseNetworkRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
import com.example.basearchitecture.data.repositories.PreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * DataManagerModule
 */
@Module
class DataManagerModule {

    /**
     * Method to provide data manager
     *
     * @param wibeRepository wibe repository instance
     * @param preferencesRepository preferences repository instance
     * @param dataBaseRepository database repository instance
     *
     * @return DataManager
     */
    @Provides
    fun provideDataManager(
        @Named("wibeRepository") wibeRepository: BaseNetworkRepository,
        preferencesRepository: PreferencesRepository,
        dataBaseRepository: DataBaseRepository
    ): DataManager =
        DataManagerImpl(wibeRepository, preferencesRepository, dataBaseRepository)

}