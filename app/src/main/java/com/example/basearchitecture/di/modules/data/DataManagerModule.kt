package com.example.basearchitecture.di.modules.data

import com.example.basearchitecture.data.manager.DataManager
import com.example.basearchitecture.data.manager.DataManagerImpl
import com.example.basearchitecture.data.repositories.BaseRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
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
     *
     * @return DataManager
     */
    @Provides
    fun provideDataManager(@Named("wibeRepository") wibeRepository: BaseRepository, dataBaseRepository: DataBaseRepository): DataManager =
        DataManagerImpl(wibeRepository, dataBaseRepository)

}