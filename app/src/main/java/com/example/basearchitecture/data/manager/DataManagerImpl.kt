package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.repositories.BaseNetworkRepository
import com.example.basearchitecture.data.repositories.DataBaseRepository
import javax.inject.Inject

/**
 * DataManagerImpl
 */
class DataManagerImpl @Inject constructor(
    private val wibeRepository: BaseNetworkRepository,
    private val dataBaseRepository: DataBaseRepository
) : DataManager {

    /**
     * Method to get wibe repository
     *
     * @return wibe repository instance
     */
    override fun getWibeRepository(): BaseNetworkRepository = wibeRepository


}