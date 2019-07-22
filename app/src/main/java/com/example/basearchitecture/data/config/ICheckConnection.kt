package com.example.basearchitecture.data.config

/**
 * ICheckConnection
 */
interface ICheckConnection {

    /**
     * Method to know if wifi is connected
     *
     * @return boolean
     */
    fun isConnected(): Boolean

}