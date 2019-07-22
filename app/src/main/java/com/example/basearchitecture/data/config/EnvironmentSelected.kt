package com.example.basearchitecture.data.config

import com.example.basearchitecture.data.network.enums.ApiServiceEnum

/**
 * EnvironmentSelected
 */
interface EnvironmentSelected {

    /**
     * Method to validate if api is in simulation
     *
     * @param apiService api service
     */
    fun isSimulation(apiService: ApiServiceEnum): Boolean

    /**
     * Method to validate if api is in develop
     *
     * @param apiService api service
     */
    fun isDevelop(apiService: ApiServiceEnum): Boolean

}