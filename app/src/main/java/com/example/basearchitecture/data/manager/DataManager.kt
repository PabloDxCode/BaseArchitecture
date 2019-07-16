package com.example.basearchitecture.data.manager

import com.example.basearchitecture.data.models.error.ICommonError
import com.example.basearchitecture.data.repositories.WibeServices

/**
 * DataManager
 */
interface DataManager : WibeServices {

    /**
     * On success response
     *
     * @param response success method response
     *
     * @return this
     */
    fun onSuccess(response: (Any) -> Unit): DataManager

    /**
     * On error response
     *
     * @param response error method response
     *
     * @return this
     */
    fun onError(error: (Any) -> Unit): DataManager

    /**
     * On server error response
     *
     * @param serverError server error method response
     *
     * @return this
     */
    fun onServerError(serverError: (ICommonError) -> Unit): DataManager

}