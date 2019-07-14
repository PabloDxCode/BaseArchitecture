package com.example.basearchitecture.data.config

/**
 * ConfigApp
 */
class ConfigApp {

    /**
     * File config interface
     */
    private var mIReadFile: IReadFile? = null
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
     * Method to set read file config
     *
     * @param iReadFile read file config
     * @return this
     */
    fun setReadFileConfig(iReadFile: IReadFile): ConfigApp {
        this.mIReadFile = iReadFile
        return this
    }

    /**
     * Method to get read file config
     *
     * @return read file config interface
     */
    fun getReadFileConfig(): IReadFile {
        return this.mIReadFile!!
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