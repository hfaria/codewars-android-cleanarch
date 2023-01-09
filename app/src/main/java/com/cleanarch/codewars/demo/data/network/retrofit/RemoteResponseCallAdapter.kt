package com.cleanarch.codewars.demo.data.network.retrofit

import com.cleanarch.codewars.demo.data.network.response.*
import retrofit2.*
import java.lang.reflect.Type

/**
 *  A Retrofit adapter to map Retrofit Response objects into NetworkResponse objects.
 */
class RemoteResponseCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, NetworkResponse<R>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): NetworkResponse<R> {
        return try {
            val retrofitResponse = call.execute()
            mapIntoNetworkResponse<R>(retrofitResponse)
        } catch (throwable: Throwable) {
            NetworkExceptionResponse(-1, throwable.message)
        }
    }

    private fun <R> mapIntoNetworkResponse(response: Response<R>): NetworkResponse<R> {
        val code = response.code()
        return if (response.isSuccessful) {
            val body = response.body()
            if (body == null || code == 204) {
                NetworkEmptyResponse(code)
            } else {
                NetworkSuccessResponse(code, body)
            }
        } else {
            return if (code == 404) {
                NetworkNotFoundResponse(code)
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                NetworkErrorResponse(code, errorMsg)
            }
        }
    }
}

