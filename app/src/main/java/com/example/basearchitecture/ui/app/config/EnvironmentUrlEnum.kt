package com.example.basearchitecture.ui.app.config

import com.example.basearchitecture.BuildConfig

/**
 * EnvironmentUrlEnum
 *
 * @param key environment key
 */
enum class EnvironmentUrlEnum(val key:String) {

    PROD("PROD"),
    QA("QA"),
    SIMULATION("SIMULATION"),
    DEV("DEV");

    companion object {

        /**
         * Method to get environment key
         */
        fun getEnvironmentKey(): String{
            val environment = BuildConfig.FLAVOR_environment
            return when(environment){
                "PROD" -> { PROD.key }
                "QA" -> { QA.key }
                "SIMULATION" -> { SIMULATION.key }
                "DEV" -> { DEV.key }
                else -> {
                    SIMULATION.key
                }
            }
        }

    }

}