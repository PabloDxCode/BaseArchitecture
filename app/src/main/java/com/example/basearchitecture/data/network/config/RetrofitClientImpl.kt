package com.example.basearchitecture.data.network.config

import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import javax.inject.Inject

/**
 * RetrofitClientImpl
 *
 * @param apiService api service enum
 */
class RetrofitClientImpl @Inject constructor(private val okHttpBuilder: OkHttpClient.Builder): RetrofitClient {

    companion object {
        /**
         * Cookie manager instance
         */
        private val sCookieHandler = CookieManager()
    }

    /**
     * Api service enum
     */
    private var mApiService: ApiServiceEnum? = null

    /**
     * Method to init class
     *
     * @param apiService api service
     */
    override fun init(apiService: ApiServiceEnum) {
        this.mApiService = apiService
    }

    /**
     * Method to get retrofit client
     *
     * @return retrofit client
     */
    override fun getRetrofitClient(): Retrofit {
        if (mApiService == ApiServiceEnum.WIBE) {
            okHttpBuilder.cookieJar(JavaNetCookieJar(sCookieHandler))
        }
        val okHttp = okHttpBuilder.build()

        return Retrofit.Builder()
            .baseUrl(ConstantsService.BASE_URL)
            .client(okHttp)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}