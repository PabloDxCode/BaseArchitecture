package com.example.basearchitecture.data.config

interface ICheckConnection {

    /**
     * Method to save String preference
     *
     * @return boolean
     */
    fun isWifiActive(): Boolean

    /**
     * Method to know if wifi is connected
     *
     * @return boolean
     */
    fun isConnected(): Boolean

}