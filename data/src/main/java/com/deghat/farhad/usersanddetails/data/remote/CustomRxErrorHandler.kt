package com.deghat.farhad.usersanddetails.data.remote

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException

internal class CustomRxErrorHandler<T> :
    Function<Throwable, ObservableSource<out T?>> {
    @Throws(Exception::class)
    override fun apply(throwable: Throwable): ObservableSource<out T?> {
        if (throwable is HttpException && !throwable.response().isSuccessful) {
            return Observable.error(Exception(getErrorMessage(throwable.response().errorBody())))
        }
        return Observable.error(throwable)
    }

    private fun getErrorMessage(responseBody: ResponseBody?): String {
        responseBody?.let { body ->
            return try {
                val jsonObject = JSONObject(body.string())
                jsonObject.getString("error")
            } catch (e: Exception) {
                e.message?: "unknown"
            }
        }
        return "unknown"
    }
}