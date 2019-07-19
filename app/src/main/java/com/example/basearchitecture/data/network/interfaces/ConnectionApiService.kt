package com.example.basearchitecture.data.network.interfaces

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Url
import retrofit2.http.Body

/**
 * ConnectionApiService
 */
interface ConnectionApiService {

    /**
     * Method to launch GET service
     *
     * @param headers map of headers
     * @param url service url
     *
     * @return observable
     */
    @GET
    fun get(@HeaderMap headers: Map<String, String>, @Url url: String): Observable<Response<String>>

    /**
     * Method to launch POST service
     *
     * @param headers map of headers
     * @param url service url
     * @param params body of service
     *
     * @return observable
     */
    @POST
    fun post(@HeaderMap headers: Map<String, String>, @Url url: String, @Body params: String): Observable<Response<String>>

}