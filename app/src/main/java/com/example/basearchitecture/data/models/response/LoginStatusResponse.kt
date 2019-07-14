package com.example.basearchitecture.data.models.response

/**
 * LoginStatusResponse
 */
class LoginStatusResponse {

    /**
     * Boolean to know session state
     */
    private var isLogged: Boolean = false

    /**
     * Method to know session state
     *
     * @return session state
     */
    fun isLogged(): Boolean {
        return isLogged
    }

    /**
     * Method to set session state
     *
     * @param session state
     */
    fun setLogged(logged: Boolean) {
        isLogged = logged
    }

}