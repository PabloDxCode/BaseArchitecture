package com.example.basearchitecture.di.modules

import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.interfaces.INetwork
import dagger.Module
import dagger.Provides

/**
 * NetworkModule
 */
@Module
class NetworkModule {

    /**
     * Method to provide network
     *
     * @return INetwork
     */
    @Provides
    fun provideNetwork(): INetwork = Network()

}