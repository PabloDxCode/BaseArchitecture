package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.repositories.BaseNetworkRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
import com.example.basearchitecture.data.repositories.PreferencesRepository
import javax.inject.Inject

/**
 * DataManagerImpl
 *
 * @param wibeRepository wibe repository instance
 * @param preferencesRepository preferences repository instance
 * @param dataBaseRepository database repository instance
 */
class DataManagerImpl @Inject constructor(
    private val wibeRepository: BaseNetworkRepository,
    private val preferencesRepository: PreferencesRepository,
    private val dataBaseRepository: DataBaseRepository
) : DataManager {

    /**
     * Method to get wibe repository
     *
     * @return wibe repository instance
     */
    override fun getWibeRepository(): BaseNetworkRepository = wibeRepository

    /**
     * Method to get preferences repository
     *
     * @return preferences repository instance
     */
    override fun getPreferencesRepository(): PreferencesRepository = preferencesRepository

}