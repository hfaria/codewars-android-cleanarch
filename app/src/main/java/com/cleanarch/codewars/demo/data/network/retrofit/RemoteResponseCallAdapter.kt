package com.cleanarch.codewars.demo.data.network.retrofit

import com.cleanarch.codewars.demo.data.*
import com.cleanarch.codewars.demo.data.network.response.*
import retrofit2.*
import java.lang.reflect.Type

/**
 *  A Retrofit adapter to map Retrofit Response objects into Output objects.
 */
class RemoteResponseCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, Output<R>> {

    override fun responseType() = responseType

    override fun adapt(call: Call<R>): Output<R> {
        return try {
            val retrofitResponse = call.execute()
            mapIntoOutput<R>(retrofitResponse)
        } catch (throwable: Throwable) {
            ThrowableOutput(throwable)
        }
    }

    private fun <R> mapIntoOutput(response: Response<R>): Output<R> {
        val code = response.code()
        return if (response.isSuccessful) {
            val body = response.body()
            if (body == null || code == 204) {
                EmptyOutput()
            } else {
                SuccessOutput(body)
            }
        } else {
            return if (code == 404) {
                NotFoundOutput()
            } else {
                val msg = response.errorBody()?.string()
                val errorMsg = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                ErrorOutput(errorMsg)
            }
        }
    }
}

