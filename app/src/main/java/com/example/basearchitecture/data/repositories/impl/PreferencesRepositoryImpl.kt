package com.example.basearchitecture.data.repositories.impl

import com.example.basearchitecture.data.config.IPreferences
import com.example.basearchitecture.data.repositories.PreferencesRepository
import javax.inject.Inject

/**
 * PreferencesRepositoryImpl
 *
 * @param preferences preferences instance
 */
class PreferencesRepositoryImpl @Inject constructor(val preferences: IPreferences) : PreferencesRepository {

    /**
     * On success method to response preferences data
     */
    private var mOnSuccess: ((Any) -> Unit?)? = null

    /**
     * Method to init preferences repository
     *
     * @param prerefenceName preferences name
     *
     * @return this
     */
    override fun init(prerefenceName: String): PreferencesRepository {
        preferences.init(prerefenceName)
        return this
    }

    /**
     * Method to set success method
     *
     * @param onSuccess on success method to response preferences data
     *
     * @return this
     */
    override fun onSuccess(onSuccess: (Any) -> Unit): PreferencesRepository {
        this.mOnSuccess = onSuccess
        return this
    }

    /**
     * Method to save string value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    override fun saveString(key: String, value: String) {
        preferences.saveString(key, value)
        mOnSuccess!!.invoke(Any())
    }

    /**
     * Method to get string value
     *
     * @param key key for search value in preferences
     */
    override fun getString(key: String) {
        val value = preferences.getString(key)
        mOnSuccess!!.invoke(value as Any)
    }

    /**
     * Method to save int value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    override fun saveInt(key: String, value: Int) {
        preferences.saveInt(key, value)
        mOnSuccess!!.invoke(Any())
    }

    /**
     * Method to get int value
     *
     * @param key key for search value in preferences
     */
    override fun getInt(key: String) {
        val value = preferences.getInt(key)
        mOnSuccess!!.invoke(value as Any)
    }

    /**
     * Method to save boolean value
     *
     * @param key key for save in preferences
     * @param value value to save in preferences
     */
    override fun saveBoolean(key: String, value: Boolean) {
        preferences.saveBoolean(key, value)
        mOnSuccess!!.invoke(Any())
    }

    /**
     * Method to get boolean value
     *
     * @param key key for search value in preferences
     */
    override fun getBoolean(key: String) {
        val value = preferences.getBoolean(key)
        mOnSuccess!!.invoke(value as Any)
    }

}