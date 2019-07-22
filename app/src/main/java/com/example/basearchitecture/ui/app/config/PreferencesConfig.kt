package com.example.basearchitecture.ui.app.config

import android.content.Context
import android.content.SharedPreferences
import com.example.basearchitecture.data.config.IPreferences

/**
 * PreferencesConfig
 *
 * @param context context instance
 */
class PreferencesConfig(val context: Context) : IPreferences {

    /**
     * Preferences object
     */
    private var mPreferences: SharedPreferences? = null

    /**
     * Method to init preferences
     *
     * @param prerefenceName preference name
     */
    override fun init(prerefenceName: String) {
        mPreferences = context.getSharedPreferences(prerefenceName, Context.MODE_PRIVATE)
    }

    /**
     * Method to save string in preferences
     *
     * @param key key value
     * @param value value to save
     */
    override fun saveString(key: String, value: String) {
        try {
            val editor = mPreferences!!.edit()
            editor.putString(key, value)
            editor.apply()
        } catch (e: Exception) {
            //Empty catch
        }
    }

    /**
     * Method to get string from preferences
     *
     * @param key key to search value in preferences
     */
    override fun getString(key: String): String? {
        try {
            return mPreferences!!.getString(key, "")
        } catch (e: Exception) {
            return ""
        }
    }

    /**
     * Method to save int in preferences
     *
     * @param key key value
     * @param value value to save
     */
    override fun saveInt(key: String, value: Int) {
        try {
            val editor = mPreferences!!.edit()
            editor.putInt(key, value)
            editor.apply()
        } catch (e: Exception) {
            //Empty catch
        }
    }

    /**
     * Method to get int from preferences
     *
     * @param key key to search value in preferences
     */
    override fun getInt(key: String): Int {
        try {
            return mPreferences!!.getInt(key, 0)
        } catch (e: Exception) {
            return 0
        }
    }

    /**
     * Method to save boolean in preferences
     *
     * @param key key value
     * @param value value to save
     */
    override fun saveBoolean(key: String, value: Boolean) {
        try {
            val editor = mPreferences!!.edit()
            editor.putBoolean(key, value)
            editor.apply()
        } catch (e: Exception) {
            //Empty catch
        }
    }

    /**
     * Method to get boolean from preferences
     *
     * @param key key to search value in preferences
     */
    override fun getBoolean(key: String): Boolean {
        try {
            return mPreferences!!.getBoolean(key, false)
        } catch (e: Exception) {
            return false
        }
    }

}