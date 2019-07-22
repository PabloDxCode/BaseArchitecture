package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.repositories.BaseNetworkRepository
import com.example.basearchitecture.data.repositories.PreferencesRepository

/**
 * DataManager
 */
interface DataManager {

    /**
     * Method to get wibe repository
     *
     * @return wibe repository instance
     */
    fun getWibeRepository(): BaseNetworkRepository

    /**
     * Method to get preferences repository
     *
     * @return preferences repository instance
     */
    fun getPreferencesRepository(): PreferencesRepository

}