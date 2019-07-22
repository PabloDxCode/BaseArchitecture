package com.example.basearchitecture.data.config

/**
 * ConfigApp
 */
class ConfigApp {

    /**
     * Connection config interface
     */
    private var mICheckConnection: ICheckConnection? = null
    /**
     * Environment selected to know state
     */
    private var mEnvironmentSelected: EnvironmentSelected? = null

    companion object {
        /**
         * Config instance
         */
        val ourInstance = ConfigApp()
    }

    /**
     * Method to set check connection config
     *
     * @param checkConnection check connection config
     *
     * @return this
     */
    fun setCheckConnection(checkConnection: ICheckConnection): ConfigApp {
        this.mICheckConnection = checkConnection
        return this
    }

    /**
     * Method to get check connection config
     *
     * @return read check connection config interface
     */
    fun getCheckConnection(): ICheckConnection {
        return this.mICheckConnection!!
    }

    /**
     * Method to set environment selected config
     *
     * @param environmentSelected environment selected to know state
     *
     * @return this
     */
    fun setEnvironmentSelected(environmentSelected: EnvironmentSelected): ConfigApp {
        this.mEnvironmentSelected = environmentSelected
        return this
    }

    /**
     * Method to get environment selected config
     *
     * @return EnvironmentSelected
     */
    fun getEnvironmentSelected(): EnvironmentSelected {
        return this.mEnvironmentSelected!!
    }

}