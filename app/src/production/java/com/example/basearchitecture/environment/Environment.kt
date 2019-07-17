package com.example.basearchitecture.environment

import com.example.basearchitecture.ui.app.config.EnvironmentUrlEnum

/**
 * Environment
 *
 * Class to define environment application
 */
class Environment {

    companion object {

        /**
         * Environtment for wibe services
         */
        val WIBE_ENVIRONMENT = EnvironmentUrlEnum.PROD

        /**
         * Environtment for auch services
         */
        val AUCH_ENVIRONMENT = EnvironmentUrlEnum.PROD

    }

}