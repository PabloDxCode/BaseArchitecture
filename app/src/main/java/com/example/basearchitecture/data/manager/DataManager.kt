package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.repositories.BaseNetworkRepository

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

}