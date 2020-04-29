package com.threeforcom.testexam.data.rest.network

import android.util.Log
import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

abstract class CallbackWrapper<Response : BaseResponse> : DisposableObserver<Response>() {

    override fun onNext(@NonNull response: Response) {
        if (response.code == 200) {
            onSuccess(response)
        } else {
            onError(response.code, response.message)
        }
    }

    override fun onError(@NonNull e: Throwable) {
        when (e) {
            is HttpException -> {
                val responseBody = e.response().errorBody()
                val errorCode = e.response().code()
                onError(errorCode, getErrorMessage(responseBody))

            }
            is SocketTimeoutException -> onError(ApiErrorType.SOCKET_TIMEOUT, "Network Timeout")
            is IOException -> onError(ApiErrorType.NETWORK_ERROR, "Network Error")
            else -> {
                Log.e("Error Parse" , e.message ?: "Unknown")
                onError(ApiErrorType.UNKNOWN_ERROR, "DataModel Parse Error" + e.message)
            }
        }
    }

    override fun onComplete() {}

    abstract fun onSuccess(response: Response)

    abstract fun onError(code: Int, message: String)

    private fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            jsonObject.getString("errors")
        } catch (e: Exception) {
            e.message ?: ""
        }

    }
}
