package com.example.basearchitecture.data.config

/**
 * IPreferences
 */
interface IPreferences {

    /**
     * Method to init preferences
     *
     * @param prerefenceName preference name
     */
    fun init(prerefenceName: String)

    /**
     * Method to save string in preferences
     *
     * @param key key value
     * @param value value to save
     */
    fun saveString(key: String, value: String)

    /**
     * Method to get string from preferences
     *
     * @param key key to search value in preferences
     */
    fun getString(key: String): String?

    /**
     * Method to save int in preferences
     *
     * @param key key value
     * @param value value to save
     */
    fun saveInt(key: String, value: Int)

    /**
     * Method to get int from preferences
     *
     * @param key key to search value in preferences
     */
    fun getInt(key: String): Int

    /**
     * Method to save boolean in preferences
     *
     * @param key key value
     * @param value value to save
     */
    fun saveBoolean(key: String, value: Boolean)

    /**
     * Method to get boolean from preferences
     *
     * @param key key to search value in preferences
     */
    fun getBoolean(key: String): Boolean

}