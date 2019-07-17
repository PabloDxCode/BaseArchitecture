package com.example.basearchitecture.data.network

import android.annotation.SuppressLint
import com.example.basearchitecture.data.config.ConfigApp
import com.example.basearchitecture.data.config.IReadFile
import com.example.basearchitecture.data.models.error.AppError
import com.example.basearchitecture.data.network.config.RetrofitClient
import com.example.basearchitecture.data.network.interfaces.ConnectionApiService
import com.example.basearchitecture.data.network.interfaces.INetwork
import com.example.basearchitecture.environment.Environment
import com.example.basearchitecture.data.network.enums.ApiServiceEnum
import com.example.basearchitecture.data.repositories.listeners.ResponseListener
import com.example.basearchitecture.ui.app.config.EnvironmentUrlEnum
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.util.logging.Logger
import javax.inject.Inject

/**
 * Network
 *
 * Implement INetwork
 *
 * @param retrofitClient retrofit client instance
 * @param readFile  i read file instance
 */
class Network @Inject constructor(val retrofitClient: RetrofitClient, val readFile: IReadFile) : INetwork {

    /**
     * Instance of consumer on next
     */
    private var mOnNext: Consumer<String>? = null
    /**
     * Instance of consumer on ERROR
     */
    private var mOnError: Consumer<Throwable>? = null
    /**
     * Instance of apiInterface
     */
    private var mApiInterface: ConnectionApiService? = null
    /**
     * Retrofit instance
     */
    private var mRetrofit: Retrofit? = null
    /**
     * Request data object
     */
    private var mRequestData: RequestData? = null
    /**
     * Response listener object
     */
    private var mResponseListener: ResponseListener? = null
    /**
     * Api service enum
     */
    private var mApiService: ApiServiceEnum? = null
    /**
     * Logger instance
     */
    private val mLogger = Logger.getLogger(ConstantsService.NETWORK_TAG)

    /**
     * Method to init request
     *
     * @param requestData request data instance
     * @param responseListener response listener
     * @param apiService api service
     */
    override fun init(requestData: RequestData, responseListener: ResponseListener, apiService: ApiServiceEnum) {
        this.mRequestData = requestData
        this.mResponseListener = responseListener
        this.mApiService = apiService
        retrofitClient.init(apiService)
        mRetrofit = retrofitClient.getRetrofitClient()

        mOnNext = Consumer { manageSuccessResponse(it) }
        mOnError = Consumer { manageErrorResponse(it) }
    }

    /**
     * Method to manage success response
     *
     * @param response success response
     */
    fun manageSuccessResponse(response: String) {
        try {
            if (mRequestData!!.getSuccessObjectResponse() === String::class.java) {
                mResponseListener!!.onSuccessResponse(response)
            } else {
                val responseObject = Gson().fromJson(response, mRequestData!!.getSuccessObjectResponse())
                mResponseListener!!.onSuccessResponseObj(responseObject)
            }
        } catch (e: Exception) {
            try {
                if (mRequestData!!.getErrorObjectResponse() === String::class.java) {
                    mResponseListener!!.onErrorResponse(response)
                } else {
                    val responseObject = Gson().fromJson(response, mRequestData!!.getErrorObjectResponse())
                    mResponseListener!!.onErrorResponse(responseObject)
                }
            } catch (e: Exception) {
                mResponseListener!!.onErrorServer(AppError(null, "generic_response", e.message))
            }
        }
    }

    /**
     * Method to manage error response
     *
     * @param throwable exception response
     */
    fun manageErrorResponse(throwable: Throwable) {
        if (throwable is SocketTimeoutException) {
            mResponseListener!!.onErrorServer(AppError(null, "time_out", throwable.message))
        } else {
            mResponseListener!!.onErrorServer(AppError(null, "generic_response", throwable.message))
        }
    }

    /**
     * Methdo to execute service
     */
    override fun execute() {
        try {
            if (ConfigApp.ourInstance.getCheckConnection().isConnected()) {
                mApiInterface = mRetrofit!!.create(ConnectionApiService::class.java)
                val networkParams = readFile.getRequestParams(mRequestData!!.getRequestCode()!!, mApiService!!)
                configureRequest(networkParams!!)
            } else {
                mResponseListener!!.onErrorServer(AppError(null, "wifi"))
            }
        } catch (e: Exception) {
            mResponseListener!!.onErrorServer(AppError(null, "generic_error", e.message))
        }
    }

    /**
     * Method to configure request
     *
     * @param networkParams network params
     */
    private fun configureRequest(networkParams: NetworkParams) {
        if (isSimulationRequest()) {
            val response = networkParams.getResponse()!!
            mLogger.info(response)
            manageSuccessResponse(response)
        } else {
            when (networkParams.getMethodType()) {
                HttpMethod.GET -> { mResponseListener!!.doGet(networkParams.getBaseUrl()!!, networkParams.getEndPoint()!!) }
                HttpMethod.POST -> { mResponseListener!!.doPost(networkParams.getBaseUrl()!!, networkParams.getEndPoint()!!) }
            }
        }
    }

    /**
     * Method to validate if simulation is active
     *
     * @return boolean
     */
    private fun isSimulationRequest(): Boolean =
        (mApiService!! == ApiServiceEnum.WIBE && Environment.WIBE_ENVIRONMENT == EnvironmentUrlEnum.SIMULATION)
                || (mApiService!! == ApiServiceEnum.AUCH && Environment.AUCH_ENVIRONMENT == EnvironmentUrlEnum.SIMULATION)

    /**
     * Method to launch GET service
     *
     * @param headers map of headers
     * @param url service url
     */
    @SuppressLint("CheckResult")
    override fun get(headers: Map<String, String>, url: String) {
        mApiInterface!!.get(headers, url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mOnNext!!, mOnError!!)
    }

    /**
     * Method to launch POST service
     *
     * @param headers map of headers
     * @param url service url
     * @param body body of service
     */
    @SuppressLint("CheckResult")
    override fun post(headers: Map<String, String>, url: String, body: String) {
        mApiInterface!!.post(headers, url, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mOnNext!!, mOnError!!)
    }

}