package com.example.basearchitecture.data.network.config

import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import retrofit2.Retrofit

/**
 * RetrofitClient
 */
interface RetrofitClient {

    /**
     * Method to init class
     *
     * @param apiService api service
     */
    fun init(apiService: ApiServiceEnum)

    /**
     * Method to get retrofit client
     *
     * @return retrofit client
     */
    fun getRetrofitClient(): Retrofit

}