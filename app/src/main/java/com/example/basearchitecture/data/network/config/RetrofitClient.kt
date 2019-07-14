package com.example.basearchitecture.data.network.config

import com.example.basearchitecture.BuildConfig
import com.example.basearchitecture.data.network.ConstantsService
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieManager
import java.util.concurrent.TimeUnit
import java.util.logging.Logger

/**
 * RetrofitClient
 *
 * @param apiService api service enum
 */
class RetrofitClient(val apiService: ApiServiceEnum) {

    companion object {
        /**
         * Cookie manager instance
         */
        private val sCookieHandler = CookieManager()
    }

    /**
     * Method to get logger instance
     *
     * @return HttpLoggingInterceptor.Logger
     */
    fun getLogger(): HttpLoggingInterceptor.Logger {
        return HttpLoggingInterceptor.Logger {
            val log = Logger.getLogger(ConstantsService.NETWORK_TAG)
            log.info(it)
        }
    }

    /**
     * Method to get logging interceptor instance
     *
     * @return HttpLoggingInterceptor
     */
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(getLogger())
    }

    /**
     * Method to get okHttp client instance
     *
     * @param loggingInterceptor logging interceptor instance
     *
     * @return OkHttpClient
     */
    fun getOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        if(apiService == ApiServiceEnum.WIBE){
            okHttpBuilder.cookieJar(JavaNetCookieJar(sCookieHandler))
        }

        return okHttpBuilder.build()
    }

    /**
     * Method to get retrofit instance
     *
     * @param okHttpClient okHttp client instance
     *
     * @return retrofit instance
     */
    fun getRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ConstantsService.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    /**
     * Method to get gson builder
     *
     * @return GsonBuilder
     */
    fun getGsonBuilder(): GsonBuilder {
        return GsonBuilder()
            .serializeNulls()
            .setLenient()
    }

}