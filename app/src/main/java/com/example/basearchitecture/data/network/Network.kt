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
import com.example.basearchitecture.data.network.enums.HttpMethod
import com.example.basearchitecture.data.network.enums.MimeTypeEnum
import com.example.basearchitecture.data.network.models.NetworkParams
import com.example.basearchitecture.data.network.models.RequestData
import com.example.basearchitecture.data.repositories.listeners.ResponseListener
import com.example.basearchitecture.ui.app.config.EnvironmentUrlEnum
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import java.net.SocketTimeoutException
import java.util.logging.Logger
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

/**
 * Network
 *
 * Implement INetwork
 *
 * @param retrofitClient retrofit client instance
 * @param readFile  i read file instance
 */
class Network @Inject constructor(private val retrofitClient: RetrofitClient, private val readFile: IReadFile) :
    INetwork {

    /**
     * Instance of consumer on next
     */
    private var mOnNext: Consumer<Response<String>>? = null
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
     * Hash map to set generic post headers
     */
    private val mPostHeaders = HashMap<String, String>()
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

        mOnNext = Consumer { manageServerResponse(it) }
        mOnError = Consumer { manageErrorResponse(it) }
    }

    /**
     * Method to manage server response
     *
     * @param response server response
     */
    fun manageServerResponse(response: Response<String>) {
        if (response.body() == null) {
            mResponseListener!!.onErrorServer(AppError(null, "generic_response", response.message()), response.code())
        } else {
            manageSuccessResponse(response.body()!!, response.code())
        }
    }

    /**
     * Method to manage success response
     *
     * @param response success response
     * @param responseCode code of server response
     */
    private fun manageSuccessResponse(response: String, responseCode: Int) {
        try {
            if (mRequestData!!.getSuccessObjectResponse() === String::class.java) {
                mResponseListener!!.onSuccessResponse(response, responseCode)
            } else {
                val responseObject = Gson().fromJson(response, mRequestData!!.getSuccessObjectResponse())
                mResponseListener!!.onSuccessResponseObj(responseObject, responseCode)
            }
        } catch (e: Exception) {
            manageErrorObjectResponse(response, responseCode, e)
        }
    }

    /**
     * Method to manage error response or server error
     *
     * @param response success response
     * @param responseCode code of server response
     * @param e exception to parse success response
     */
    private fun manageErrorObjectResponse(response: String, responseCode: Int, e: Exception) {
        try {
            if (mRequestData!!.getErrorObjectResponse() == null) {
                mResponseListener!!.onErrorServer(AppError(null, "generic_response", e.message), responseCode)
            } else {
                if (mRequestData!!.getErrorObjectResponse() === String::class.java) {
                    mResponseListener!!.onErrorResponse(response, responseCode)
                } else {
                    val responseObject = Gson().fromJson(response, mRequestData!!.getErrorObjectResponse())
                    mResponseListener!!.onErrorResponse(responseObject, responseCode)
                }
            }
        } catch (e: Exception) {
            mResponseListener!!.onErrorServer(AppError(null, "generic_response", e.message), responseCode)
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
            manageSuccessResponse(response, HttpsURLConnection.HTTP_OK)
        } else {
            when (networkParams.getMethodType()) {
                HttpMethod.GET -> {
                    mResponseListener!!.doGet(networkParams.getBaseUrl()!!, networkParams.getEndPoint()!!)
                }
                HttpMethod.POST -> {
                    configPostRequest(networkParams.getBaseUrl()!!, networkParams.getEndPoint()!!)
                }
            }
        }
    }

    /**
     * Method to configure generic headers of post service
     *
     * @param url service url
     * @param endPoint end point service
     */
    private fun configPostRequest(url: String, endPoint: String) {
        when (mRequestData!!.getMimeType()) {
            MimeTypeEnum.APPLICATION_JSON -> {
                mPostHeaders[ConstantsService.HEADER_CONTENT_TYPE] = MimeTypeEnum.APPLICATION_JSON.mimeType
            }
            MimeTypeEnum.FORM_URL_ENCODED -> {
                mPostHeaders[ConstantsService.HEADER_CONTENT_TYPE] = MimeTypeEnum.FORM_URL_ENCODED.mimeType
            }
            else -> { /* Empty else*/
            }
        }
        mResponseListener!!.doPost(url, endPoint)
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
        mPostHeaders.putAll(headers)
        mApiInterface!!.post(mPostHeaders, url, body)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mOnNext!!, mOnError!!)
    }

}