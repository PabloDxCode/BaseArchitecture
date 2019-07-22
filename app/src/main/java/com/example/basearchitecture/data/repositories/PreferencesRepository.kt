package com.example.basearchitecture.data.repositories

/**
 * PreferencesRepository
 */
interface PreferencesRepository {

    /**
     * Method to init preferences repository
     *
     * @param prerefenceName preferences name
     *
     * @return this
     */
    fun init(prerefenceName: String): PreferencesRepository

    /**
     * Method to set success method
     *
     * @param onSuccess on success method to response preferences data
     *
     * @return this
     */
    fun onSuccess(onSuccess: (Any) -> Unit): PreferencesRepository

    /**
     * Method to save string value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    fun saveString(key: String, value: String)

    /**
     * Method to get string value
     *
     * @param key key for search value in preferences
     */
    fun getString(key: String)

    /**
     * Method to save int value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    fun saveInt(key: String, value: Int)

    /**
     * Method to get int value
     *
     * @param key key for search value in preferences
     */
    fun getInt(key: String)

    /**
     * Method to save boolean value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    fun saveBoolean(key: String, value: Boolean)

    /**
     * Method to get boolean value
     *
     * @param key key for search value in preferences
     */
    fun getBoolean(key: String)

}