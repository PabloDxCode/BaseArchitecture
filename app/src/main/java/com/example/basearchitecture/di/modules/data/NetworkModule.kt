package com.example.basearchitecture.di.modules.data

import android.content.Context
import com.example.basearchitecture.BuildConfig
import com.example.basearchitecture.data.config.IReadFile
import com.example.basearchitecture.data.network.Network
import com.example.basearchitecture.data.network.config.RetrofitClient
import com.example.basearchitecture.data.network.config.RetrofitClientImpl
import com.example.basearchitecture.data.network.interfaces.INetwork
import com.example.basearchitecture.ui.app.config.ReadFileConfig
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.io.File
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
     * Method to provide cache file
     *
     * @param context context instance
     * @return cache file
     */
    @Provides
    fun cacheFile(context: Context): File {
        return File(context.cacheDir, "okhttp_cache")
    }

    /**
     * Method to provide cache
     *
     * @param cacheFile cache file
     * @return cache instance
     */
    @Provides
    fun cache(cacheFile: File): Cache {
        return Cache(cacheFile, 10 * 1024 * 1024)
    }

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
     * @param cache cache instance
     * @return okhttp client
     */
    @Provides
    fun okHttpClient(cache: Cache, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient.Builder {
        loggingInterceptor.level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(CONECTION_TIME_OUT, TimeUnit.SECONDS)
            .cache(cache)
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
     * Method to provide read file config
     *
     * @param context context instance
     *
     * @return i read file
     */
    @Provides
    fun provideIReadFile(context: Context): IReadFile = ReadFileConfig(context)

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