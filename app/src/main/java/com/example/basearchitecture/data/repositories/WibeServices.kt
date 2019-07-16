package com.example.basearchitecture.data.repositories

/**
 * WibeServices
 */
interface WibeServices {

    /**
     * Method to do login
     *
     * @param requestBody user status object
     */
    fun login(requestBody: String)

    /**
     * Method to generate session id
     *
     * @param requestBody user status object
     */
    fun generateSessionId(requestBody: String)

    /**
     * Method to do login slod
     *
     * @param params map params
     */
    fun loginSlod(params: HashMap<String, String>)

    /**
     * Method to do login status
     */
    fun loginStatus()

    /**
     * Method to get user information
     *
     * @param params map params
     */
    fun getUserInfo(params: HashMap<String, String>)

    /**
     * Method to update login date
     *
     * @param requestBody user status object
     */
    fun updateLoginDate(requestBody: String)

}