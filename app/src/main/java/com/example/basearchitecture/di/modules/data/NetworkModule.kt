package com.example.basearchitecture.di.modules.data

import com.example.basearchitecture.BuildConfig
import com.example.basearchitecture.data.config.IReadFile
import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.config.RetrofitClient
import com.example.basearchitecture.data.network.config.RetrofitClientImpl
import com.example.basearchitecture.data.network.interfaces.INetwork
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * NetworkModule
 */
@Module
class NetworkModule {

    /**
     * Tag for request log
     */
    private val TAG_REQUEST = "RequestClient"
    /**
     * Read time out request
     */
    private val READ_TIME_OUT: Long = 60
    /**
     * Connection time out request
     */
    private val CONECTION_TIME_OUT: Long = 60

    /**
     * Method to init interceptor
     *
     * @return http logging interceptor
     */
    @Provides
    fun loggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Timber.tag(TAG_REQUEST).d(message)
        })
    }

    /**
     * Method to provide okhttp client
     *
     * @param loggingInterceptor logging interceptor instance
     *
     * @return okhttp client
     */
    @Provides
    fun okHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONECTION_TIME_OUT, TimeUnit.SECONDS)
    }

    /**
     * Method to provide retrofit client
     *
     * @param okHttpBuilder ok http client builder
     *
     * @return retrifit client
     */
    @Provides
    fun provideRetrofitClient(okHttpBuilder: OkHttpClient.Builder): RetrofitClient =
        RetrofitClientImpl(okHttpBuilder)

    /**
     * Method to provide network
     *
     * @param retrofitClient retrofit client instance
     * @param readFile  i read file instance
     *
     * @return INetwork
     */
    @Provides
    fun provideNetwork(retrofitClient: RetrofitClient, readFile: IReadFile): INetwork =
        Network(retrofitClient, readFile)

}