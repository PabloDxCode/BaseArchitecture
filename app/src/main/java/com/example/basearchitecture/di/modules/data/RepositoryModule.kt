package com.example.basearchitecture.di.modules.data

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.repositories.BaseRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
import com.example.basearchitecture.data.repositories.impl.AUCHRepositoryImpl
import com.example.basearchitecture.data.repositories.impl.DataBaseRepositoryImpl
import com.example.basearchitecture.data.repositories.impl.WibeRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * RepositoryModule
 */
@Module(includes = [NetworkModule::class])
class RepositoryModule {

    /**
     * Method to provide wibe repository
     *
     * @param network network instance
     *
     * @return WibeRepository
     */
    @Provides
    @Named("wibeRepository")
    fun provideWibeRepository(network: Network): BaseRepository = WibeRepositoryImpl(network)

    /**
     * Method to provide AUCH repository
     *
     * @param network network instance
     *
     * @return AUCHRepository
     */
    @Provides
    @Named("auchRepository")
    fun provideAUCHRepository(network: Network): BaseRepository = AUCHRepositoryImpl(network)

    /**
     * Method to provide database repository
     *
     * @return DataBaseRepository
     */
    @Provides
    fun provideDataBaseRepository(): DataBaseRepository = DataBaseRepositoryImpl()

}