package com.example.basearchitecture.ui.app.config

import com.example.basearchitecture.data.config.EnvironmentSelected
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.environment.Environment

/**
 * EnvironmentSelectedConfig
 */
class EnvironmentSelectedConfig : EnvironmentSelected {

    /**
     * Method to validate if api is in simulation
     *
     * @param apiService api service
     */
    override fun isSimulation(apiService: ApiServiceEnum): Boolean {
        return validateEnvironmentSelected(apiService, EnvironmentUrlEnum.SIMULATION)
    }

    /**
     * Method to validate if api is in develop
     *
     * @param apiService api service
     */
    override fun isDevelop(apiService: ApiServiceEnum): Boolean {
        return validateEnvironmentSelected(apiService, EnvironmentUrlEnum.DEV)
    }

    /**
     * Method to validate environment selected by custom params
     *
     * @param apiService api service selcted
     * @param environmentUrl environment url selected
     */
    private fun validateEnvironmentSelected(apiService: ApiServiceEnum, environmentUrl: EnvironmentUrlEnum): Boolean {
        return when (apiService) {
            ApiServiceEnum.WIBE -> {
                Environment.WIBE_ENVIRONMENT == environmentUrl
            }
            ApiServiceEnum.AUCH -> {
                Environment.AUCH_ENVIRONMENT == environmentUrl
            }
        }
    }

}