package com.example.basearchitecture.di.modules

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.repositories.AUCHRepository
import com.example.basearchitecture.data.repositories.WibeRepository
import com.example.basearchitecture.data.repositories.impl.AUCHRepositoryImpl
import com.example.basearchitecture.data.repositories.impl.WibeRepositoryImpl
import dagger.Module
import dagger.Provides

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
    fun provideWibeRepository(network: Network): WibeRepository = WibeRepositoryImpl(network)

    /**
     * Method to provide AUCH repository
     *
     * @param network network instance
     *
     * @return AUCHRepository
     */
    @Provides
    fun provideAUCHRepository(network: Network): AUCHRepository = AUCHRepositoryImpl(network)

}