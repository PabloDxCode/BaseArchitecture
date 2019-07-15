package com.example.basearchitecture.di.modules

import com.example.basearchitecture.data.repositories.WibeRepository
import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.manager.DataManagerImpl
import com.example.basearchitecture.data.repositories.DataBaseRepository
import dagger.Module
import dagger.Provides

/**
 * DataManagerModule
 */
@Module
class DataManagerModule {

    /**
     * Method to provide data manager
     *
     * @param wibeRepository wibe repository instance
     *
     * @return DataManager
     */
    @Provides
    fun provideDataManager(wibeRepository: WibeRepository, dataBaseRepository: DataBaseRepository): DataManager =
        DataManagerImpl(wibeRepository, dataBaseRepository)

}