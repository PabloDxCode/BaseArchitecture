package com.example.basearchitecture.data.config

/**
 * ConfigApp
 */
class ConfigApp {

    /**
     * Connection config interface
     */
    private var mICheckConnection: ICheckConnection? = null

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
     * @return this
     */
    fun setCheckConnection(checkConnection: ICheckConnection) {
        this.mICheckConnection = checkConnection
    }

    /**
     * Method to get check connection config
     *
     * @return read check connection config interface
     */
    fun getCheckConnection(): ICheckConnection {
        return this.mICheckConnection!!
    }

}